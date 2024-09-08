package com.test.todoList.controller;

import com.test.todoList.dto.BoardsDTO;
import com.test.todoList.service.BoardsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BoardsController {
    private final BoardsService service;

    @PostMapping("/detailToday")
    public ResponseEntity<Map<String,Object>> today(@RequestBody Map<String, Object> map) {
        String boardId = (String) map.get("boardId");
        BoardsDTO detailBoard = service.detailBoards(boardId);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "상세페이지에 들어왔습니다.");
        response.put("content", detailBoard.getContent());
        response.put("addExp", detailBoard.getAddExp());
        response.put("deadline", detailBoard.getDeadLine());
        log.info("detailBoard={}", detailBoard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/updateToday")
    public ResponseEntity<Map<String,Object>> updateToday(@RequestBody Map<String,Object> map) {
        Map<String,Object> response = new HashMap<>();
        log.info("map = {}",map);
            String content = (String) map.get("content");
            String addExp = (String) map.get("addExp");
            String deadline = (String) map.get("deadline");
        String boardId = (String) map.get("boardId");
        int update = service.updateBoards(map);
        log.info("update={}", update);
        if(update <= 0) {
            response.put("error","없는 정보입니다.");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            response.put("message", "수정 성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
