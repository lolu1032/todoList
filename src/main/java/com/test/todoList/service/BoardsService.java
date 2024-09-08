package com.test.todoList.service;

import com.test.todoList.dto.BoardsDTO;

import java.util.List;
import java.util.Map;

public interface BoardsService {
    List<BoardsDTO> boardsList();
    BoardsDTO detailBoards(String boardId);
    int updateBoards(Map<String,Object> map);
}
