package com.test.todoList.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/users/login")
    public String login() {
        return "users/login";
    }
    @GetMapping("/users/signup")
    public String signup() {
        return "users/signup";
    }

}
