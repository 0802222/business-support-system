package com.cho.business.entity;

public enum UserRole {
    ADMIN("관리자", "모든 권한"),
    MANAGER("담당자", "사업공고 관리, 일정 관리"),
    STAFF("직원", "할당된 작업 수행"),
    VIEWER("열람자", "읽기 전용");

    private final String displayName;
    private final String description;

    UserRole(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public boolean canEdit() {
        return this == ADMIN || this == MANAGER || this == STAFF;
    }

    public boolean canDelete() {
        return this == ADMIN;
    }
}
