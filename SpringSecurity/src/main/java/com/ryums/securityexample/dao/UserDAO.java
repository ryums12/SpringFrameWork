package com.ryums.securityexample.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDAO {

    @Autowired
    private SqlSession sqlSession;

    public void createMember(Map<String,Object> param) {
        sqlSession.insert("user.insertMember", param);
    }

    public Map<String,Object> selectMember(String id) {
        return sqlSession.selectOne("user.selectMember", id);
    }

    public int checkIdDup(Map<String, Object> param) {
        return sqlSession.selectOne("user.checkIdDup", param);
    }
}
