package com.cho.business.entity;

public enum Department {
    // ===== 경영진 =====
    CEO_OFFICE("대표실", DepartmentCategory.EXECUTIVE, null),

    // ===== 기술 부서 =====
    RND("R&D팀", DepartmentCategory.TECH, null),
    RND_BACKEND("백엔드팀", DepartmentCategory.TECH, RND),
    RND_FRONTEND("프론트엔드팀", DepartmentCategory.TECH, RND),
    RND_DEVOPS("DevOps팀", DepartmentCategory.TECH, RND),
    QA_TEAM("QA팀", DepartmentCategory.TECH, null),

    // ===== 비즈니스 부서 =====
    MARKETING("마케팅팀", DepartmentCategory.BUSINESS, null),
    MARKETING_GROWTH("그로스팀", DepartmentCategory.BUSINESS, MARKETING),
    MARKETING_BRAND("브랜드팀", DepartmentCategory.BUSINESS, MARKETING),

    SALES("영업팀", DepartmentCategory.BUSINESS, null),
    SALES_ENTERPRISE("엔터프라이즈 영업", DepartmentCategory.BUSINESS, SALES),
    SALES_SME("중소기업 영업", DepartmentCategory.BUSINESS, SALES),

    // ===== 지원 부서 =====
    FINANCE("재무팀", DepartmentCategory.SUPPORT, null),
    ACCOUNTING("회계팀", DepartmentCategory.SUPPORT, null),
    HR("HR팀", DepartmentCategory.SUPPORT, null),
    RECRUITING("채용팀", DepartmentCategory.SUPPORT, HR),
    CS("고객지원팀", DepartmentCategory.SUPPORT, null),

    // ===== 전략/운영 =====
    STRATEGY("전략팀", DepartmentCategory.STRATEGY, null),
    OPERATIONS("운영팀", DepartmentCategory.STRATEGY, null);

    private final String displayName;
    private final DepartmentCategory category;
    private final Department parentDepartment;

    Department(String displayName, DepartmentCategory category, Department parentDepartment) {
        this.displayName = displayName;
        this.category = category;
        this.parentDepartment = parentDepartment;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DepartmentCategory getCategory() {
        return category;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    /**
     * 기술 부서인가?
     */
    public boolean isTechDepartment() {
        return category == DepartmentCategory.TECH;
    }

    /**
     * 비즈니스 부서인가?
     */
    public boolean isBusinessDepartment() {
        return category == DepartmentCategory.BUSINESS;
    }

    /**
     * 지원 부서인가?
     */
    public boolean isSupportDepartment() {
        return category == DepartmentCategory.SUPPORT;
    }

    /**
     * 경영진 부서인가?
     */
    public boolean isExecutiveDepartment() {
        return category == DepartmentCategory.EXECUTIVE;
    }

    /**
     * 하위 부서인가? (소팀인가?)
     */
    public boolean isSubDepartment() {
        return parentDepartment != null;
    }

    /**
     * 상위 부서의 모든 부서원에게 영향을 주는가?
     */
    public boolean affectsParent() {
        return !isSubDepartment();
    }
}

/**
 * 부서 카테고리
 */
enum DepartmentCategory {
    EXECUTIVE("경영진"),
    TECH("기술"),
    BUSINESS("비즈니스"),
    SUPPORT("지원"),
    STRATEGY("전략/운영");

    private final String displayName;

    DepartmentCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}