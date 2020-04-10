package com.start.mb.boot.middleware.security.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 其中supports方法指定了支持处理的Token类型为SmsAuthenticationToken，authenticate方法用于编写具体的身份认证逻辑。
 *
 * 在authenticate方法中，我们从SmsAuthenticationToken中取出了手机号信息，并调用了UserDetailService的loadUserByUsername方法。
 * 该方法在用户名密码类型的认证中，主要逻辑是通过用户名查询用户信息，如果存在该用户并且密码一致则认证成功
 * ；而在短信验证码认证的过程中，该方法需要通过手机号去查询用户，如果存在该用户则认证通过。
 * 认证通过后接着调用SmsAuthenticationToken的SmsAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities)构造函数构造一个认证通过的Token，包含了用户信息和用户权限。
 *
 * 你可能会问，为什么这一步没有进行短信验证码的校验呢？
 * 实际上短信验证码的校验是在SmsAuthenticationFilter之前完成的，即只有当短信验证码正确以后才开始走认证的流程。所以接下来我们需要定一个过滤器来校验短信验证码的正确性。
 *
 *
 * @author wll
 * @desc SmsAuthenticationProvider
 * @link
 * @date 2020/4/10 11:13 上午
 */
@Slf4j
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SMSAuthenticationToken authenticationToken = (SMSAuthenticationToken) authentication;

        //拿到手机号 去获取用户 然后装配
//        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        UserDetails userDetails = userDetailsService.loadUserByUsername("wll");

        if(userDetailsService==null){
            log.error("for error ,SmsAuthenticationProvider 未找到与该手机号对应的用户 ! ");
            throw new InternalAuthenticationServiceException("未找到与该手机号对应的用户");
        }

        SMSAuthenticationToken smsAuthenticationToken = new SMSAuthenticationToken(userDetails, userDetails.getAuthorities());

        smsAuthenticationToken.setDetails(userDetails);

        return smsAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SMSAuthenticationToken.class.isAssignableFrom(aClass);
    }

     UserDetailsService getUserDetailService() {
        return userDetailsService;
    }

    public void setUserDetailService(UserDetailsService userDetailService) {
        this.userDetailsService = userDetailService;
    }
}
