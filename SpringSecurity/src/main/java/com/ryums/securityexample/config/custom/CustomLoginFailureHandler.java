package com.ryums.securityexample.config.custom;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        // Member Not Found
        if(exception instanceof InternalAuthenticationServiceException) {
            request.setAttribute("msg", "존재하지 않는 아이디입니다.\n아이디를 확인 해주십시오.");
        } else if(exception instanceof BadCredentialsException) { // Bad credentials
            request.setAttribute("msg", "잘못된 비밀번호입니다.\n비밀번호를 확인 해주십시오.");
        } else {
            request.setAttribute("msg", "알 수 없는 오류가 발생했습니다.\n관리자에게 문의 해주십시오.");
        }

        request.getRequestDispatcher("/login").forward(request, response);
    }
}
