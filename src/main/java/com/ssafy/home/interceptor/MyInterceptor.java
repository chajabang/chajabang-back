package com.ssafy.home.interceptor;

import com.ssafy.home.member.dto.Member;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("여기는 preHandle입니다");
        HttpSession session = request.getSession(false);
        if(session != null) {
            Member m = (Member) session.getAttribute("member");
            if(m != null && m.getUsername() != null) {
                return true;
            }
        } else {//login 안되었을 때
            response.sendRedirect("/");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("여기는 postHandle입니다");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("여기는 afterCompletion");
    }
}
