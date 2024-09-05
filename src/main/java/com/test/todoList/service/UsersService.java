package com.test.todoList.service;

import com.test.todoList.dto.UsersDTO;

import java.util.Map;

public interface UsersService {
    // 회원가입
    public int insertUsers(UsersDTO usersDTO);
    // 로그인
    public UsersDTO selectUsers(Map<String,Object> map);

}
