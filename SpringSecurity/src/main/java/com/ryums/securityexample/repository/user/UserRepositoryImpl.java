package com.ryums.securityexample.repository.user;

import com.ryums.securityexample.entity.UserEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/*
 * 커스텀 쿼리 메소드 구현
 * */
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(UserEntity.class);
    }

    /* Write jpa query method*/
}
