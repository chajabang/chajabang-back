package com.ssafy.home.comment.service;

import com.ssafy.home.comment.dto.Comment;
import com.ssafy.home.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getComments(int articleNo) {
        return commentMapper.getComments(articleNo);
    }

    public long write(Comment comment) {
        return commentMapper.write(comment);
    }

    public Comment getView(int id) {
        return commentMapper.getView(id);
    }

    public int delete(int id) {
        return commentMapper.delete(id);
    }

    public int updateComment(Comment comment) {
        return commentMapper.update(comment);
    }



}
