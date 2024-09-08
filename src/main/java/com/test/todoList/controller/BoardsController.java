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

import java.time.LocalDate;
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
        response.put("deadline", detailBoard.getDeadline());
        log.info("detailBoard={}", detailBoard);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/updateToday")
    public ResponseEntity<Map<String,Object>> updateToday(@RequestBody Map<String,Object> map) {
        Map<String,Object> response = new HashMap<>();
        log.info("map = {}",map);
        String addExp = (String) map.get("addExp");
        String boardId = (String) map.get("boardId");
        String content = (String) map.get("content");
        String deadline = (String) map.get("deadline");
        int update = service.updateBoards(map);
        log.info("update={}", update);
        if(update <= 0) {
            response.put("error","없는 정보입니다.");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR); // 500 서버오류
        }else {
            response.put("message", "수정 성공");
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 ok
        }
    }
    @PostMapping("/insertToday")
    public ResponseEntity<Map<String,Object>> insertToday(@RequestBody Map<String,Object> map) {
        Map<String,Object> response = new HashMap<>();
        BoardsDTO boardsDTO = new BoardsDTO();
        if((String) map.get("content") == null) {
            response.put("error","제목을 넣어주세요.");
            return  new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        }else {
            boardsDTO.setContent((String) map.get("content"));
        }
        if((String) map.get("deadline") == null) {
            response.put("error","마감일 넣어주세요.");
            return  new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        }else {
            boardsDTO.setDeadline(LocalDate.parse((String) map.get("deadline")));
        }
        if((Integer) map.get("userId") == null) {
            boardsDTO.setUserId(0);
        }else {
            boardsDTO.setUserId((Integer) map.get("userId"));
        }
        boardsDTO.setAddExp((String) map.get("addExp"));
        int insert = service.insertBoards(boardsDTO);
        if(insert <= 0) {
            response.put("message","추가실패");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            response.put("message","추가성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
