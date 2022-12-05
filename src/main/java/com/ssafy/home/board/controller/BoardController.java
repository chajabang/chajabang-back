package com.ssafy.home.board.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.service.BoardService;
import com.ssafy.home.board.service.S3FileUploadService;
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
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ERROR = "error";

    @Autowired
    BoardService boardService;
//    @Autowired
//    S3FileUploadService s3FileUploadService;


    @GetMapping("/selectAll")
    public ResponseEntity<PageInfo<Board>> selectAll(HttpServletRequest request) {
        logger.info("Board SelectAll 호출");
        PageHelper.startPage(request);
        List<Board> list = boardService.selectAll();
//        System.out.println(list.size());
        return new ResponseEntity<>(PageInfo.of(list, 10), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> writeBoard(@RequestBody Board board, HttpServletRequest request) {
        logger.info("Board Write 호출 - {}", board);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        if ((m.getId()).equals(board.getUserId())) {
            board.setUserName(m.getUsername());
            long i = boardService.write(board);
//            System.out.println(i);
            if (i > 0) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/view")
    public ResponseEntity<Board> view(@RequestParam int articleNo) {
        logger.info("Board View 호출 - {}", articleNo);
        Board board = boardService.getView(articleNo);
        if (board != null) {
            boardService.updateHit(articleNo);
            return new ResponseEntity<>(board, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int articleNo, HttpServletRequest request) {
        logger.info("deleteArticle - 호출 {}", articleNo);
        Board b = boardService.getView(articleNo);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        if (m != null) {
            if (m.getId().equals(b.getUserId())) {
                int cnt = boardService.delete(articleNo);
                if (cnt > 0) {
                    return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(FAIL,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/update")
    public ResponseEntity<String> modifyBoard(@RequestBody Board board, HttpServletRequest request) throws
            Exception {
        logger.info("modifyBoard - 호출 {}", board);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
//        System.out.println(m);

        if (m != null && m.getId().equals(board.getUserId())) { // 세션의 id 값과 board의 userId 값이 동일해야 함!!
            int cnt = boardService.updateBoard(board);
            if (cnt > 0) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/checkLike")
    public ResponseEntity<String> checkLike(@RequestParam int articleNo, HttpServletRequest request) throws
            Exception {
        logger.info("checkLike - 호출 {}", articleNo);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        if (m != null) { // 세션의 id 값과 board의 userId 값이 동일해야 함!!
            Board b = boardService.getView(articleNo);
            if(b==null){
                return new ResponseEntity<>(FAIL, HttpStatus.NOT_FOUND);
            }
            int cnt = boardService.checkLike(m.getId(),articleNo);
            if (cnt > 0) {
                return new ResponseEntity<>("exist", HttpStatus.OK);
            }
            return new ResponseEntity<>("not exist",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/addLike")
    public ResponseEntity<String> addLike(@RequestParam int articleNo, HttpServletRequest request) throws
            Exception {
        logger.info("addLike - 호출 {}" , articleNo);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        Board b = boardService.getView(articleNo);
        if (m != null && b!=null) { // 세션의 id 값과 board의 userId 값이 동일해야 함!!
            int cnt = boardService.checkLike(m.getId(),articleNo);
            if(cnt==0){
                int result = boardService.addLike(m.getId(),articleNo);
                if (result > 0) {
                    return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/removeLike")
    public ResponseEntity<String> removeLike(@RequestParam int articleNo, HttpServletRequest request) throws
            Exception {
        logger.info("removeLike - 호출 {}" , articleNo);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        Board b = boardService.getView(articleNo);
//        System.out.println(b);
        if (m != null && b!=null) { // 세션의 id 값과 board의 userId 값이 동일해야 함!!
            int cnt = boardService.checkLike(m.getId(),articleNo);
            if(cnt>0){
                int result = boardService.removeLike(m.getId(),articleNo);
                if (result > 0) {
                    return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);

    }





//    @PostMapping("/upload")
//    public ResponseEntity<String> upload(@RequestParam("files")  MultipartFile multipartFile){
//        try {
//            s3FileUploadService.upload(multipartFile);
//            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//        } catch (IOException e) {
//            logger.info("WARNING");
//            return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
//        }
//
//    }
}
