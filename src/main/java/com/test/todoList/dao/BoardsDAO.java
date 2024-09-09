package com.test.todoList.dao;

import com.test.todoList.dto.BoardsDTO;

import java.util.List;
import java.util.Map;

public interface BoardsDAO {
    List<BoardsDTO> BoardsList(int userId);
    BoardsDTO detailBoards(String boardId);
    int updateBoards(Map<String,Object> map);
    int insertBoards(BoardsDTO boardsDTO);
    int updateUserId(int userId);
    int deleteBoard(String boardId);
    int sucessBoard(String boardId);
}
