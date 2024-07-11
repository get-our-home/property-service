package com.getourhome.propertyservice.entity;

public enum PropertyType {
    VILLA_ROWHOUSE_MULTIPLEX("빌라_연립_다세대"),
    SINGLE_FAMILY_HOME("단독주택"),
    MULTI_FAMILY_HOME("다가구주택"),
    MIXED_USE_BUILDING("상가주택"),
    OTHER("기타"),
    OFFICETEL("오피스텔"),
    APARTMENT("아파트");

    private final String displayName;

    PropertyType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
