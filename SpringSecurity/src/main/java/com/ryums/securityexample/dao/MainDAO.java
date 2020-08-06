package com.ryums.securityexample.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainDAO {

    @Autowired
    private SqlSession sqlSession;

    /** write dao method */
}
