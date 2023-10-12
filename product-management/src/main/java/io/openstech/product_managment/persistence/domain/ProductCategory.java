package io.openstech.product_managment.persistence.domain;

import javax.persistence.Entity;



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
