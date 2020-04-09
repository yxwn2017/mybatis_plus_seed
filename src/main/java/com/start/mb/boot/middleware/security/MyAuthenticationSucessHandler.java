package com.start.mb.boot.middleware.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wll
 * @desc MyAuthenticationSucessHandler 成功处理逻辑
 * @link
 * @date 2020/4/8 5:08 下午
 */
@Component
public class MyAuthenticationSucessHandler  implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(mapper.writeValueAsString(authentication));
    }
}
