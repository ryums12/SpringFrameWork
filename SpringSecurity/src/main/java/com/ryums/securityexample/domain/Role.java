package com.ryums.securityexample.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("운영자"), MANAGER("관리자"), USER("사용자");

    private String name;
}
