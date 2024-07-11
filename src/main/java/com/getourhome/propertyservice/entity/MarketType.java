package com.getourhome.propertyservice.entity;

public enum MarketType {
    MONTHLY_RENT("월세"),
    JEONSE("전세"),
    SALE("매매");

    private final String displayName;

    MarketType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
