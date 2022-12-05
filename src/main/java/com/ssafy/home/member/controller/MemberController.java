package com.ssafy.home.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String ERROR = "error";

    @Autowired
    MemberService memberService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        logger.info("Member Logout 호출 ");
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 로그아웃 성공
            Member m = (Member) session.getAttribute("member");
            logger.info("MEMBER 정보 {}", m);
            session.invalidate();
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        } else {
            // 로그아웃 실패 => 해커가 의도적으로 세션을 지우고 시도 or 세션 만료
            logger.warn("Member Logout 해킹시도!! - {}", request.getRemoteAddr());
            return new ResponseEntity<>("해킹 시도 IP 수집중!!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody Member m, HttpServletRequest request) {
        logger.info("Member login 호출 - {}", m);
        if (m.getId() == null || m.getPw() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            Member member = memberService.login(m);
            if (member != null) {
                // 로그인 성공
                member.setPw("00000000");
                HttpSession session = request.getSession();
                session.setAttribute("member", member);
                return new ResponseEntity<>(member, HttpStatus.OK);
            }
            // 로그인 실패
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (SQLException e) {
            //실패
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.warn("Exception -  {} ", e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Member m) {
        logger.info("Member Register 호출 - {}", m);
        try {
            int idx = memberService.register(m);
            if (idx > 0) {
                // 회원가입 성공
                return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
            }
            //회원가입 실패
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // 에러
            logger.warn("Exception -  {} ", e.getMessage());
            return new ResponseEntity<>(ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<String> idCheck(@RequestParam("id") String id) {
        logger.info("Member ID 중복 체크 호출 - {}", id);
        try {
            int cnt = memberService.idCheck(id);
            if (cnt == 0) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
            return new ResponseEntity<>(FAIL, HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.warn("Exception -  {} ", e.getMessage());
        }
        return new ResponseEntity<>(ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/username")
    public ResponseEntity<String> usernameCheck(@RequestParam("username") String username, HttpServletRequest request) {
        logger.info("Member username 중복 체크 호출 - {}", username);
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member sessionM = (Member) session.getAttribute("member");
            if (sessionM.getUsername().equals(username)) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
        }
        try {
            int cnt = memberService.usernameCheck(username);
            if (cnt == 0) {
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
            return new ResponseEntity<>(FAIL, HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.warn("Exception -  {} ", e.getMessage());
        }
        return new ResponseEntity<>(ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Map<String, String> map, HttpServletRequest request) {
        logger.info("Member Update 호출");
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member m = (Member) session.getAttribute("member");
            logger.info("Member Update Member - {}", map);

            String id = map.get("id");
            String pw = map.get("pw");
            String username = map.get("username");
            String email = map.get("email");
            if (pw.trim().equals("")) {
                map.put("pw", null);
            }
//            System.out.println(pw == null);

            try {
                if (m != null && m.getId().equals(id)) {
                    try {
                        int i = memberService.update(map);
                        if (i > 0) {
                            session.setAttribute("member", new Member(id, username, email));
                            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                        }
                    } catch (Exception e) {
                        logger.warn("Exception -  {} ", e.getMessage());
                        return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
                    }
                }

                return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                logger.warn("Exception -  {} ", e.getMessage());
                return new ResponseEntity<>(ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        //세션 만료
        return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(HttpServletRequest request) {
        logger.info("Member Delete 호출");
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member m = (Member) session.getAttribute("member");
            if (m != null) {
                String id = m.getId();
                try {
                    int i = memberService.delete(id);
                    if (i > 0) {
                        session.invalidate();
                        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                    }

                } catch (Exception e) {
                    logger.warn("Exception -  {} , id : { }", e.getMessage());
                }
            }
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
    }

    @PostMapping("/pwconfirm")
    public ResponseEntity<String> PwCheck(@RequestBody Member m, HttpServletRequest request) {
        logger.info("Member PwCheck  호출 ");
        if (m.getPw() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member sessionM = (Member) session.getAttribute("member");
            if (sessionM != null) {
                m.setId(sessionM.getId());
                try {
                    Member member = memberService.login(m);
                    if (member != null) {
                        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
                    }
                } catch (SQLException e) {
                    return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
                } catch (Exception e) {
                    logger.warn("Exception -  {} , id : { }", e.getMessage());
                    return new ResponseEntity<>(ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            //잘못된 입력
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        logger.warn("세션만료");
        //세션 만료
        return new ResponseEntity<>(FAIL, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/check")
    public ResponseEntity<String> sessionCheck(HttpServletRequest request) {
        logger.info("Session Check - 호출 ");
        HttpSession session = request.getSession(false);
        if (session == null) {//세션 만료
            return new ResponseEntity<>(FAIL, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);

    }
}
