package com.cho.business.entity;

public enum JobTitle {
    // ===== 개발 관련 =====
    BACKEND_DEVELOPER("백엔드 개발자", JobCategory.DEVELOPMENT),
    FRONTEND_DEVELOPER("프론트엔드 개발자", JobCategory.DEVELOPMENT),
    FULLSTACK_DEVELOPER("풀스택 개발자", JobCategory.DEVELOPMENT),
    MOBILE_DEVELOPER("모바일 개발자", JobCategory.DEVELOPMENT),
    DEVOPS_ENGINEER("DevOps 엔지니어", JobCategory.DEVELOPMENT),

    // ===== QA/테스트 =====
    QA_ENGINEER("QA 엔지니어", JobCategory.QA),
    TEST_ENGINEER("테스트 엔지니어", JobCategory.QA),

    // ===== 디자인 =====
    UI_DESIGNER("UI 디자이너", JobCategory.DESIGN),
    UX_DESIGNER("UX 디자이너", JobCategory.DESIGN),
    GRAPHIC_DESIGNER("그래픽 디자이너", JobCategory.DESIGN),
    PRODUCT_DESIGNER("프로덕트 디자이너", JobCategory.DESIGN),

    // ===== 기획/PM =====
    PRODUCT_MANAGER("프로덕트 매니저", JobCategory.PLANNING),
    BUSINESS_ANALYST("비즈니스 분석가", JobCategory.PLANNING),
    PROJECT_MANAGER("프로젝트 매니저", JobCategory.PLANNING),
    PRODUCT_PLANNER("프로덕트 기획자", JobCategory.PLANNING),

    // ===== 마케팅 =====
    MARKETING_MANAGER("마케팅 매니저", JobCategory.MARKETING),
    GROWTH_MARKETER("그로스 마케터", JobCategory.MARKETING),
    PERFORMANCE_MARKETER("퍼포먼스 마케터", JobCategory.MARKETING),
    BRAND_MANAGER("브랜드 매니저", JobCategory.MARKETING),
    CONTENT_MARKETER("콘텐츠 마케터", JobCategory.MARKETING),

    // ===== 영업 =====
    SALES_MANAGER("영업 매니저", JobCategory.SALES),
    BUSINESS_DEVELOPMENT("비즈니스 개발", JobCategory.SALES),
    ACCOUNT_EXECUTIVE("계정 담당자", JobCategory.SALES),

    // ===== 재무/회계 =====
    CFO("최고재무책임자(CFO)", JobCategory.FINANCE),
    ACCOUNTING_MANAGER("회계 담당자", JobCategory.FINANCE),
    FINANCIAL_ANALYST("재무 분석가", JobCategory.FINANCE),
    TAX_SPECIALIST("세무 전문가", JobCategory.FINANCE),

    // ===== 전략/경영 =====
    CEO("대표이사(CEO)", JobCategory.STRATEGY),
    COO("최고운영책임자(COO)", JobCategory.STRATEGY),
    CTO("최고기술책임자(CTO)", JobCategory.STRATEGY),
    STRATEGY_DIRECTOR("전략 담당 이사", JobCategory.STRATEGY),

    // ===== HR/채용 =====
    HR_MANAGER("HR 매니저", JobCategory.HR),
    RECRUITER("채용담당자", JobCategory.HR),
    PEOPLE_OPERATIONS("피플 옵스", JobCategory.HR),

    // ===== 고객지원 =====
    CUSTOMER_SUCCESS("고객성공담당자", JobCategory.CS),
    CUSTOMER_SUPPORT("고객지원담당자", JobCategory.CS),

    // ===== 데이터 =====
    DATA_ANALYST("데이터 분석가", JobCategory.DATA),
    DATA_SCIENTIST("데이터 과학자", JobCategory.DATA),
    DATA_ENGINEER("데이터 엔지니어", JobCategory.DATA),

    // ===== 보안 =====
    SECURITY_ENGINEER("보안 엔지니어", JobCategory.SECURITY),
    INFOSEC_SPECIALIST("정보보안 전문가", JobCategory.SECURITY),

    // ===== 기타 =====
    RESEARCH_SCIENTIST("연구원", JobCategory.RESEARCH),
    TECHNICAL_WRITER("기술 문서 작성자", JobCategory.OTHER),
    OPERATION_MANAGER("운영 담당자", JobCategory.OTHER),
    INTERN("인턴", JobCategory.OTHER),
    OTHER("기타", JobCategory.OTHER);

    private final String displayName;
    private final JobCategory category;

    JobTitle(String displayName, JobCategory category) {
        this.displayName = displayName;
        this.category = category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public JobCategory getCategory() {
        return category;
    }

    /**
     * 기술 직무인지 확인 (개발, QA, 데이터, 보안 등)
     */
    public boolean isTechnicalRole() {
        return category == JobCategory.DEVELOPMENT
            || category == JobCategory.QA
            || category == JobCategory.DATA
            || category == JobCategory.SECURITY;
    }

    /**
     * 비기술 직무인지 확인 (마케팅, 영업, HR 등)
     */
    public boolean isNonTechnicalRole() {
        return category == JobCategory.MARKETING
            || category == JobCategory.SALES
            || category == JobCategory.HR
            || category == JobCategory.CS;
    }

    /**
     * 경영진 직무인지 확인
     */
    public boolean isExecutive() {
        return this == CEO
            || this == COO
            || this == CTO
            || this == CFO
            || this == STRATEGY_DIRECTOR;
    }

    /**
     * 관리자급 직무인지 확인
     */
    public boolean isManager() {
        return displayName.contains("매니저")
            || displayName.contains("이사")
            || displayName.contains("책임자");
    }
}

/**
 * 직무 카테고리
 */
enum JobCategory {
    DEVELOPMENT("개발"),
    QA("QA/테스트"),
    DESIGN("디자인"),
    PLANNING("기획/PM"),
    MARKETING("마케팅"),
    SALES("영업"),
    FINANCE("재무/회계"),
    STRATEGY("전략/경영"),
    HR("HR/채용"),
    CS("고객지원"),
    DATA("데이터"),
    SECURITY("보안"),
    RESEARCH("연구"),
    OTHER("기타");

    private final String displayName;

    JobCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
