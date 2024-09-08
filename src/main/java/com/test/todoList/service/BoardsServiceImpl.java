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
    public List<BoardsDTO> boardsList() {
        return dao.BoardsList();
    }

    @Override
    public BoardsDTO detailBoards(String boardId) {
        return dao.detailBoards(boardId);
    }

    @Override
    public int updateBoards(Map<String, Object> map) {
        return dao.updateBoards(map);
    }
}
