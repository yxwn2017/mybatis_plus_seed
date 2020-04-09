package com.start.mb.boot.config;

import com.start.mb.boot.middleware.security.MyAuthenticationFailureHandler;
import com.start.mb.boot.middleware.security.MyAuthenticationSucessHandler;
import com.start.mb.boot.middleware.security.ValidateCodeFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final MyAuthenticationFailureHandler failureHandler;
    private final MyAuthenticationSucessHandler sucessHandler;
    private final ValidateCodeFilter validateCodeFilter;
    private final PersistentTokenRepository tokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable();

        // 配置验证码Filter
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置登录成功后的操作
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login") // 处理表单登录 URL
                .successHandler(sucessHandler).failureHandler(failureHandler);

        http.rememberMe()
                .tokenRepository(tokenRepository) // 配置 token 持久化仓库
                .tokenValiditySeconds(300) // remember 过期时间，单为秒
                .userDetailsService(userDetailsService()); // 处理自动登录逻辑

        // 登出授权
        http.logout().permitAll();

        // 授权配置
        http.authorizeRequests()
                /* 所有静态文件可以访问 */
                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                /* 所有 以/api 开头的 广告页面可以访问 */
                .antMatchers("/api/**", "/login.html").permitAll()
                .anyRequest().fullyAuthenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceccc() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String pwd = passwordEncoder().encode("1234");
        System.out.println(pwd);
        manager.createUser(User.withUsername("user").password(pwd).roles("USER").build());
        return manager;
    }
}