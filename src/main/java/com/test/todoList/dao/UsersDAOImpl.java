package com.test.todoList.dao;

import com.test.todoList.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UsersDAOImpl implements UsersDAO{

    private final SqlSession sqlSession;
    @Override
    public int insertUsers(UsersDTO usersDTO) {
        return sqlSession.insert("com.test.todoList.usersMapper.insertUsers",usersDTO);
    }

    @Override
    public UsersDTO selectUsers(Map<String, Object> map) {
        return sqlSession.selectOne("com.test.todoList.usersMapper.selectUsers",map);
    }

    @Override
    public int checkEmail(String email) {
        return sqlSession.selectOne("com.test.todoList.usersMapper.checkEmail",email);
    }
}
