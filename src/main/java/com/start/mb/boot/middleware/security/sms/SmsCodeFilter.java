package com.start.mb.boot.middleware.security.sms;

import cn.hutool.core.util.StrUtil;
import com.start.mb.boot.common.Exception.security.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wll
 * @desc SmsCodeFilter
 * @link
 * @date 2020/4/10 1:58 下午
 */
@Slf4j
@Component
public class SmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(StrUtil.equalsIgnoreCase("/login/mobile",request.getRequestURI()) && StrUtil.equalsIgnoreCase("POST",request.getMethod())){
            try {
                validateSmsCode(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                log.error("for error, 短信验证码 验证失败！");
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validateSmsCode(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        //手机号
        String mobile = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
        //验证码
        String smsCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");


//        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(servletWebRequest, FebsConstant.SESSION_KEY_SMS_CODE + mobile);
        //持久化的 sms code
        SmsCode code = new SmsCode("5678", 300);

        if (StrUtil.isBlank(mobile)) {
            throw new ValidateCodeException("验证码不能为空！");
        }
        if (code == null) {
            throw new ValidateCodeException("验证码不存在，请重新发送！");
        }

        if (code.isExpire()) {
            throw new ValidateCodeException("验证码已过期，请重新发送！");
        }
        if (!StrUtil.equalsIgnoreCase(code.getCode(), smsCode)) {
            throw new ValidateCodeException("验证码不正确！");
        }
        //remove 持久化 sms code
    }
}
