package com.cho.business.entity;

public enum JobLevel {
    INTERN("인턴", 0),
    JUNIOR("주니어", 1),
    SENIOR("시니어", 2),
    MANAGER("팀장/관리자", 3),
    DIRECTOR("이사", 4),
    EXECUTIVE("임원", 5);

    private final String displayName;
    private final int level;

    JobLevel(String displayName, int level) {
        this.displayName = displayName;
        this.level = level;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLevel() {
        return level;
    }

    public boolean isHigherThan(JobLevel other) {
        return this.level > other.level;
    }
}
