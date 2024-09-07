package com.test.todoList.service;

import com.test.todoList.dao.UsersDAO;
import com.test.todoList.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersDAO dao;
    @Override
    public int insertUsers(UsersDTO usersDTO) {
            return dao.insertUsers(usersDTO);
    }

    @Override
    public UsersDTO selectUsers(String email) {
        return dao.selectUsers(email);
    }

    @Override
    public int checkEmail(String email) {
        return dao.checkEmail(email);
    }
}
