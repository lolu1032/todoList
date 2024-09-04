package com.test.todoList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }
    @GetMapping("/signup")
    public String signUp() {
        return "users/signUp";
    }
    @GetMapping("/today")
    public String today() {
        return "board/today";
    }
}
