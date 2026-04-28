package com.cho.business.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /* User */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, length = 20)
    @Pattern(regexp = "^01[0-9]-?\\d{3,4}-?\\d{4}$|^0\\d{1,2}-?\\d{3,4}-?\\d{4}$")
    private String phoneNumber;

    @Column(nullable = false)
    private String password;


    /* 병역 및 신상 정보 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MilitaryStatus militaryStatus;


    /* 조직 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;


    /* 직무 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobTitle jobTitle; // 직무 (백엔드 개발자, 디자이너)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobLevel jobLevel; // 직급 (인턴, 주니어, 시니어)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position; // 직책 (대리, 과장, 팀장)


    /* 경력 */
    @Column(nullable = false)
    private LocalDate hireDate; // 입사일

    @Column(nullable = true)
    private LocalDate resignationDate; // 퇴사일

    @Transient // DB 저장 안함, 계산으로만 사용
    private String yearsOfService; // 근속 년수 (N년 N월)


    /* 정부지원사업 관련 */
    @Column(nullable = false)
    @Builder.Default
    private Boolean isSpecialistResearcher = false; // 전문연구인력 여부

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private SpecialistResearcher specialistResearcherInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private ResearcherStatus researcherStatus;


    /* 내부직원 여부 확인 메서드 */
    // 참여 조직 확인
    public boolean isInternalToNotice(BusinessNotice notice) {
        return notice.getParticipatingOrganizations()
            .contains(this.organization);
    }

    // 주도 조직 확인
    public boolean isCreatorOrganizationMember(BusinessNotice notice) {
        return this.organization.equals(
            notice.getCreatedBy().getOrganization()
        );
    }

    // 조직 소속 확인
    public boolean belongsToOrganization(Organization org) {
        return this.organization.equals(org);
    }


    /* 권한 & 상태 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private UserRole role = UserRole.STAFF;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isActive = true; // 활성 여부


    /* 관리 정보 */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    /* 연관 관계 - 작성자 삭제 시 사업공고 / 일정은 유지되고 관계만 끊김 */
    @OneToMany(
        mappedBy = "createdBy",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE}, // 수정, 생성만
        orphanRemoval = false,   // 작성자 삭제해도 남음
        fetch = FetchType.LAZY   // 가끔 조회
    )
    @Builder.Default
    private List<BusinessNotice> createdNotices = new ArrayList<>();

    @OneToMany(
        mappedBy = "user",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        orphanRemoval = false,
        fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Schedule> schedules = new ArrayList<>();


    /* 라이프 사이클 콜백 */
    // INSERT 시 실행 (시간 + 근속년수)
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        this.yearsOfService = calculateYearsOfService();
    }

    // UPDATE 시 실행 (수정 시간 + 근속년수)
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        this.yearsOfService = calculateYearsOfService();
    }

    // 조회 시 실행 (근속년수 계산)
    @PostLoad
    public void refreshYearsOfService() {
        this.yearsOfService = calculateYearsOfService();
    }


    /* Util Method */
    // 근속년수
    public String calculateYearsOfService() {
        if (hireDate == null) {
            return "0년 0월";
        }

        LocalDate today = LocalDate.now();
        int years = today.getYear() - hireDate.getYear();
        int months = today.getMonthValue() - hireDate.getMonthValue();

        if (months < 0) {
            years--;
            months += 12;
        }

        return String.format("%d년 %d월", years, months);
    }

    // 퇴직 여부
    public boolean isResigned() {
        return resignationDate != null;
    }

    // 현직 여부
    public boolean isCurrentEmployee() {
        return resignationDate == null;
    }

    // 비활성화
    public void deactivate() {
        this.isActive = false;
    }

    // 활성화
    public void activate() {
        this.isActive = true;
    }

    // 전문연구인력으로 등록
    public void registerAsSpecialistResearcher(SpecialistResearcher info) {
        this.isSpecialistResearcher = true;
        this.specialistResearcherInfo = info;
        info.setUser(this);
    }

}
