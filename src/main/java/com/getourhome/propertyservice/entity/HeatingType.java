package com.getourhome.propertyservice.entity;

public enum HeatingType {
    NONE(0, "난방없음"), // 난방 없음
    CENTRAL(1, "중앙난방"), // 중앙 난방
    INDIVIDUAL(2, "개별난방"), // 개별 난방
    DISTRICT(3, "지역난방"); // 지역 난방

    private final int code;
    private final String displayName;

    HeatingType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
