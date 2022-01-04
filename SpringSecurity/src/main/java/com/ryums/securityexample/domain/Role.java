package com.ryums.securityexample.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ROLE_ADMIN("운영자"), ROLE_MANAGER("관리자"), ROLE_USER("사용자");

    private String name;
}
