package com.cho.business.entity;

public enum Position {
    // ===== 일반직 =====
    INTERN("인턴", 0, false),
    STAFF("사원", 1, false),
    SENIOR_STAFF("선임사원", 2, false),

    // ===== 관리직 =====
    TEAM_LEAD("팀장", 3, true),           // 팀/소팀 리더
    GROUP_MANAGER("그룹장", 4, true),     // 여러 팀 관리
    DIRECTOR("부장", 5, true),            // 부서장

    // ===== 임원 =====
    EXECUTIVE("임원", 6, true),
    C_LEVEL("C-레벨(CEO/CTO/CFO)", 7, true);

    private final String displayName;
    private final int level;              // 직급 수준 (0~7)
    private final boolean isManagerial;   // 관리자인가?

    Position(String displayName, int level, boolean isManagerial) {
        this.displayName = displayName;
        this.level = level;
        this.isManagerial = isManagerial;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLevel() {
        return level;
    }

    public boolean isManagerial() {
        return isManagerial;
    }

    /**
     * 관리자급인가?
     */
    public boolean isManager() {
        return isManagerial;
    }

    /**
     * 임원인가?
     */
    public boolean isExecutive() {
        return this == EXECUTIVE || this == C_LEVEL;
    }

    /**
     * 특정 직책보다 높은가?
     */
    public boolean isHigherThan(Position other) {
        return this.level > other.level;
    }

    /**
     * 특정 직책과 같거나 높은가?
     */
    public boolean isHigherOrEqual(Position other) {
        return this.level >= other.level;
    }
}
