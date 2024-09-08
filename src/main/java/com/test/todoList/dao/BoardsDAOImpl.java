package com.test.todoList.dao;

import com.test.todoList.dto.BoardsDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BoardsDAOImpl implements BoardsDAO{
    private final SqlSession sqlSession;

    @Override
    public List<BoardsDTO> BoardsList(int userId) {
        return sqlSession.selectList("com.test.todoList.boardsMapper.boardsList",userId);
    }

    @Override
    public BoardsDTO detailBoards(String boardId) {
        return sqlSession.selectOne("com.test.todoList.boardsMapper.detailBoards",boardId);
    }

    @Override
    public int updateBoards(Map<String, Object> map) {
        return sqlSession.update("com.test.todoList.boardsMapper.updateBoards",map);
    }

    @Override
    public int insertBoards(BoardsDTO boardsDTO) {
        return sqlSession.insert("com.test.todoList.boardsMapper.insertBoards",boardsDTO);
    }

}
