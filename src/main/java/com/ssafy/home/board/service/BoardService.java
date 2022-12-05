package com.ssafy.home.board.service;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardMapper boardMapper;

    public List<Board> selectAll() {
        return boardMapper.selectAll();
    }

    public long write(Board board) {
        return boardMapper.write(board);
    }

    public Board getView(int articleNo) {
        return boardMapper.getView(articleNo);
    }

    public int updateHit(int articleNo) {
        return boardMapper.updateHit(articleNo);
    }

    public int delete(int articleNo) {
        return boardMapper.delete(articleNo);
    }

    public int updateBoard(Board board) {

        return boardMapper.update(board);

    }

    public int checkLike(String userId, int articleNo) {
        return boardMapper.checkLike(userId,articleNo);
    }

    public int addLike(String userId, int articleNo) {
        return boardMapper.addLike(userId,articleNo);
    }

    public int removeLike(String userId, int articleNo) {
        return boardMapper.removeLike(userId,articleNo);
    }
}
