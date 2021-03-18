package com.ryums.securityexample.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserMember extends User {

    private String id;
    private String pwd;
    private String role_name;

    public UserMember(String id, String pwd, String role_name, Collection<? extends GrantedAuthority> authorities) {
        super(id, pwd, authorities);

        this.id = id;
        this.pwd = pwd;
        this.role_name = role_name;
    }
}
