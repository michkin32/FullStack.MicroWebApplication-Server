package com.groupfour.chatapp.chatapp.models;

public enum Constants {
    SIGNING_KEY("devglan123r"),
    TOKEN("Bearer "),
    HEADER_STRING("Authorization"),
    AUTHORITIES_KEY("scopes"),
    AUTHORITES_KEY("");

    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = null;
    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
