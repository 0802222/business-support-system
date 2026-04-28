package com.cho.business.entity;

public enum ResearcherStatus {
    ACTIVE("활성", true),
    INACTIVE("비활성", false),
    EXPIRED("만료", false),
    SUSPENDED("정지", false);

    private final String displayName;
    private final boolean isActive;

    ResearcherStatus(String displayName, boolean isActive) {
        this.displayName = displayName;
        this.isActive = isActive;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isActive() {
        return isActive;
    }
}
