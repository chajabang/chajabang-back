package com.ssafy.home.comment.controller;

import com.ssafy.home.comment.dto.Comment;
import com.ssafy.home.comment.service.CommentService;
import com.ssafy.home.member.dto.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ERROR = "error";

    @Autowired
    CommentService commentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Comment>> selectAll(int articleNo, HttpServletRequest request) {
        logger.info("Comment GetAll 호출{}", articleNo);
        List<Comment> list = commentService.getComments(articleNo);
//        for (Comment c : list) {
//            System.out.println(c);
//        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<Comment> writeComment(@RequestBody Comment comment, HttpServletRequest request) {
        logger.info("Comment Write 호출 - {}", comment);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        if ((m.getId()).equals(comment.getUserId())) {
            comment.setUserName(m.getUsername());
            long i = commentService.write(comment);
//            System.out.println(i);
            if (i > 0) {
                return new ResponseEntity<>(comment, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/view")
    public ResponseEntity<Comment> view(@RequestParam int id) {
        logger.info("Comment View 호출 - {}", id);
        Comment comment = commentService.getView(id);
        if (comment != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id, HttpServletRequest request) {
        logger.info("delete Comment - 호출 {}", id);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Comment b = commentService.getView(id);
        Member m = (Member) session.getAttribute("member");
        if (m != null && b != null) {
            if (m.getId().equals(b.getUserId())) {
                int cnt = commentService.delete(id);
                if (cnt > 0) {
                    return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/update")
    public ResponseEntity<String> modifyComment(@RequestBody Comment comment, HttpServletRequest request) throws
            Exception {
        logger.info("modifyComment - 호출 {}", comment);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");

        if (m != null && m.getId().equals(comment.getUserId())) { // 세션의 id 값과 comment의 userId 값이 동일해야 함!!
            int cnt = commentService.updateComment(comment);
            if (cnt > 0) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);

    }


}
