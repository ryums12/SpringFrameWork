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
    private char grade;
    private String roleName;

    public UserMember(String id, String pwd, char grade, String roleName, Collection<? extends GrantedAuthority> authorities) {
        super(id, pwd, authorities);

        this.id = id;
        this.pwd = pwd;
        this.grade = grade;
        this.roleName = roleName;
    }
}
