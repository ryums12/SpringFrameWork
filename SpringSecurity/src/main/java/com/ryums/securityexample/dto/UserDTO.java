package com.ryums.securityexample.dto;

import com.ryums.securityexample.entity.UserEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

    private Long memberIdx;
    private String id;
    private String pwd;
    private char grade;

    private String name;
    private String email;
    private String picture;

    public UserEntity toEntity() {

        return UserEntity.builder()
//                .memberIdx(memberIdx)
//                .id(id)
//                .pwd(pwd)
//                .grade(grade)
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }
}
