package com.ryums.securityexample.entity;

import com.ryums.securityexample.domain.Role;
import lombok.*;

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
    private char grade;

    @Enumerated(EnumType.STRING)
    @Transient
    private Role role;

    @Transient
    private String name;

    @Transient
    private String email;

    @Transient
    private String picture;

    @Builder
    public UserEntity(Long memberIdx, String id, String pwd, String name, String email, String picture, char grade, Role role) {
        this.memberIdx = memberIdx;
        this.id = id;
        this.pwd = pwd;
        this.grade = grade;

        this.name = name;
        this.email = email;
        this.picture = picture;

        this.role = role;
    }
}
