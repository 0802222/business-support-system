package com.cho.business.entity;

public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    OTHER("기타");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    private String getDisplayName() {
        return displayName;
    }
}
