package io.openstech.admin_hub.persistence.domain;

public enum ProductCategory {

    TV("TV"),
    SMART_PHONE("Smart Phone"),
    HOUSE_APPLICANT("House Applicant"),
    COMPUTER("Computer");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
