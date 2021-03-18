package com.ryums.securityexample.repository.user;

import com.ryums.securityexample.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
*  jpa method 정의 인터페이스
* */
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    /* write just jpa method */
    UserEntity findById(String id);
    int countAllById(String id);
}
