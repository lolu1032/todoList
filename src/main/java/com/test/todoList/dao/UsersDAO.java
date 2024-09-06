package com.test.todoList.dao;

import com.test.todoList.dto.UsersDTO;

import java.util.Map;

public interface UsersDAO {
    // 회원가입
    int insertUsers(UsersDTO usersDTO);
    // 로그인
    UsersDTO selectUsers(Map<String, Object> map);
    // 이메일 중복 확인
    int checkEmail(String email);
}
