package com.test.todoList.controller;

import com.test.todoList.dto.BoardsDTO;
import com.test.todoList.service.BoardsService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ViewController {
    private final BoardsService service;
    @GetMapping("/users/login")
    public String login() {
        return "users/login";
    }
    @GetMapping("/users/signup")
    public String signup() {
        return "users/signup";
    }
    @GetMapping("/today")
    public String today(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        List<BoardsDTO> list;
        if(userId != null) {
            service.updateUserId(userId);
            list = service.boardsList(userId);
            log.info("userId={}",userId);
            model.addAttribute("list",list);
            return "board/today";
        }
        list = service.boardsList(0);
        model.addAttribute("list",list);
        return "board/today";
    }
}
