package com.test.todoList.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsersDTO {
    private String email; // 이메일
    private String password; // 비밀번호
    private String name; // 이름
}
