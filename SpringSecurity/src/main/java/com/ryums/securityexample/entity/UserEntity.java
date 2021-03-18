package com.ryums.securityexample.entity;

import com.ryums.securityexample.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "example_member")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx;
    private String id;
    private String pwd;

    @Builder
    public UserEntity(Long memberIdx, String id, String pwd) {
        this.memberIdx = memberIdx;
        this.id = id;
        this.pwd = pwd;
    }
}
