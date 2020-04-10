package com.start.mb.boot.middleware.security.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author wll
 * @desc SmsAuthenticationConfig
 * @link
 * @date 2020/4/10 2:23 下午
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SmsAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    private final UserDetailsService userDetailsServicel;

/**
 * 在流程中第一步需要配置SmsAuthenticationFilter，分别设置了AuthenticationManager、AuthenticationSuccessHandler和AuthenticationFailureHandler属性。
 * 这些属性都是来自SmsAuthenticationFilter继承的AbstractAuthenticationProcessingFilter类中。
 *
 * 第二步配置SmsAuthenticationProvider，这一步只需要将我们自个的UserDetailService注入进来即可。
 *
 * 最后调用HttpSecurity的authenticationProvider方法指定了AuthenticationProvider为SmsAuthenticationProvider，并将SmsAuthenticationFilter过滤器添加到了UsernamePasswordAuthenticationFilter之前。
 *
 * 到这里我们已经将短信验证码认证的各个组件组合起来了，最后一步需要做的是配置短信验证码校验过滤器，并且将短信验证码认证流程加入到Spring Security中。在BrowserSecurityConfig的configure方法中添加如下配置：
 */
    @Override
    public void configure(HttpSecurity builder) throws Exception {

        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);


        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailService(userDetailsServicel);

        builder.authenticationProvider(smsAuthenticationProvider)
                .addFilterBefore(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);




    }
}
