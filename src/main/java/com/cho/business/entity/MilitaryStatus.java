package com.cho.business.entity;

public enum MilitaryStatus {
    // 남성 (병역 의무 있음)
    COMPLETED("복무 완료", true, true),
    EXEMPT_MALE("면제(남성)", true, true),
    PENDING("복무중", false, true),
    ALTERNATIVE("대체 복무", false, true),

    // 여성 (병역 의무 없음),
    FEMALE_NOT_APPLICABLE("여성(해당없음)", true, false),

    // 기타
    UNKNOWN("미확인", false, false),
    FOREIGNER("외국인", true, true);

    private final String displayName;
    private final boolean isResolved;
    private final boolean isMale;

    MilitaryStatus(String displayName, boolean isResolved, Boolean isMale) {
        this.displayName = displayName;
        this.isResolved = isResolved;
        this.isMale = isMale;
    }

    public String getDisplayName() {
        return displayName;
    }

    // 정부 신청 가능 여부 확인
    public boolean isEligibleForGovernmentSupport() {
        return this != UNKNOWN;
    }
}
