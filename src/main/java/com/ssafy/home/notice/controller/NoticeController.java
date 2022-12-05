package com.ssafy.home.notice.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.member.dto.Member;
import com.ssafy.home.notice.dto.Notice;
import com.ssafy.home.notice.service.NoticeService;
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
@RequestMapping("/notice")
public class NoticeController {

    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ERROR = "error";

    @Autowired
    NoticeService noticeService;


    @GetMapping("/selectAll")
    public ResponseEntity<PageInfo<Notice>> selectAll(HttpServletRequest request) {
        logger.info("Notice SelectAll 호출");
        PageHelper.startPage(request);
        List<Notice> list = noticeService.selectAll();
//        System.out.println(list.size());
        return new ResponseEntity<>(PageInfo.of(list, 10), HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<String> writeNotice(@RequestBody Notice notice, HttpServletRequest request) {
        logger.info("Notice Write 호출 - {}", notice);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        if ((m.getId()).equals(notice.getUserId())) {
            notice.setUserName(m.getUsername());
            long i = noticeService.write(notice);
//            System.out.println(i);
            if (i > 0) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/view")
    public ResponseEntity<Notice> view(@RequestParam int articleNo) {
        logger.info("Notice View 호출 - {}", articleNo);
        Notice notice = noticeService.getView(articleNo);
        if (notice != null) {
            noticeService.updateHit(articleNo);
            return new ResponseEntity<>(notice, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int articleNo, HttpServletRequest request) {
        logger.info("deleteArticle - 호출 {}", articleNo);
        Notice b = noticeService.getView(articleNo);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
        if (m != null) {
            if (m.getId().equals(b.getUserId())) {
                int cnt = noticeService.delete(articleNo);
                if (cnt > 0) {
                    return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(FAIL,HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/update")
    public ResponseEntity<String> modifyNotice(@RequestBody Notice notice, HttpServletRequest request) throws
            Exception {
        logger.info("modifyNotice - 호출 {}", notice);
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        Member m = (Member) session.getAttribute("member");
//        System.out.println(m);

        if (m != null && m.getId().equals(notice.getUserId())) { // 세션의 id 값과 notice의 userId 값이 동일해야 함!!
            int cnt = noticeService.updateNotice(notice);
            if (cnt > 0) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);

    }

}
