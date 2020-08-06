package com.ryums.securityexample.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserMember extends User {

    private String id;
    private String pwd;
    private String role_name;

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRole_name() {
        return role_name;
    }

    public UserMember(String id, String pwd, String role_name, Collection<? extends GrantedAuthority> authorities) {
        super(id, pwd, authorities);

        this.id = id;
        this.pwd = pwd;
        this.role_name = role_name;
    }
}
