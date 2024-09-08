package com.test.todoList.dao;

import com.test.todoList.dto.BoardsDTO;

import java.util.List;
import java.util.Map;

public interface BoardsDAO {
    List<BoardsDTO> BoardsList();
    BoardsDTO detailBoards(String boardId);
    int updateBoards(Map<String,Object> map);
}
