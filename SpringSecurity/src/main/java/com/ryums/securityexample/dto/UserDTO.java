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

    public UserEntity toEntity() {
        UserEntity builder = UserEntity.builder()
                .memberIdx(memberIdx)
                .id(id)
                .pwd(pwd)
                .build();

        return builder;
    }
}