package com.ssafy.home.board.mapper;

import com.ssafy.home.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> selectAll();
	long  write(Board board);

    Board getView(int articleNo);

    int updateHit(int articleNo);


    int delete(int articleNo);

    int update(Board board);

    int checkLike(String userId,int articleNo);
    int addLike(String userId,int articleNo);
    int removeLike(String userId,int articleNo);

}
