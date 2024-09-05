package com.test.todoList.dao;

import com.test.todoList.dto.UsersDTO;

import java.util.Map;

public interface UsersDAO {
    // 회원가입
    public int insertUsers(UsersDTO usersDTO);
    // 로그인
    public UsersDTO selectUsers(Map<String, Object> map);
}
