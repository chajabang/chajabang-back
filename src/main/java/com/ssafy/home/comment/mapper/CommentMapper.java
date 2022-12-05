package com.ssafy.home.comment.mapper;

import com.ssafy.home.comment.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> getComments(int articleNo);
	long  write(Comment board);

    Comment getView(int id);


    int delete(int id);

    int update(Comment comment);


}
