package com.ryums.securityexample.domain;

import com.ryums.securityexample.entity.UserEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String id;
    private char grade;

    private String name;
    private String email;
    private String picture;

    public SessionUser(UserEntity user) {
        this.id = user.getId();
        this.grade = user.getGrade();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}