package com.test.todoList.service;

import com.test.todoList.dao.BoardsDAO;
import com.test.todoList.dto.BoardsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardsServiceImpl implements BoardsService{
    private final BoardsDAO dao;
    @Override
    public List<BoardsDTO> boardsList(int userId) {
        return dao.BoardsList(userId);
    }
    @Override
    public BoardsDTO detailBoards(String boardId) {
        return dao.detailBoards(boardId);
    }

    @Override
    public int updateBoards(Map<String, Object> map) {
        return dao.updateBoards(map);
    }

    @Override
    public int insertBoards(BoardsDTO boardsDTO) {
        return dao.insertBoards(boardsDTO);
    }

    @Override
    public int updateUserId(int userId) {
        return dao.updateUserId(userId);
    }

    @Override
    public int deleteBoard(String boardId) {
        return dao.deleteBoard(boardId);
    }

    @Override
    public int sucessBoard(String boardId) {
        return dao.sucessBoard(boardId);
    }
}
