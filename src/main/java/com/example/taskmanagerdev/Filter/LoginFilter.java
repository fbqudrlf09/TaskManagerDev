package com.example.taskmanagerdev.Filter;

import com.example.taskmanagerdev.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/members/signup", "/members/login", "/members/logout"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            // 로그인 확인을 위해 세션에서 값 확인
            if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 사용가능합니다");
            }
        }
        chain.doFilter(request, response);
    }

    private static boolean isMatchLoginId(HttpServletRequest request) {
        return "GET".equals(request.getMethod());
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
