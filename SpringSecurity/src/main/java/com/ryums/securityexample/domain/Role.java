package com.ryums.securityexample.domain;


public enum Role {
    ADMIN("사용자");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
