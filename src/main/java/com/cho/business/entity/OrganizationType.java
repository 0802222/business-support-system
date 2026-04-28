package com.cho.business.entity;

public enum OrganizationType {
    STARTUP("스타트업", true),
    SME("중소기업", true),
    MEDIUM("중견기업", false),
    LARGE("대기업", false),
    GOVERNMENT("정부기관", false),
    RESEARCH_INSTITUTE("연구기관", true);

    private final String displayName;
    private final boolean isEligibleForSupport;

    OrganizationType(String displayName, boolean isEligibleForSupport) {
        this.displayName = displayName;
        this.isEligibleForSupport = isEligibleForSupport;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isEligibleForSupport() {
        return isEligibleForSupport;
    }
}
