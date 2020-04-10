package com.start.mb.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author wll
 * @desc SecurityConfig
 * @link
 * @date 2020/4/10 2:48 下午
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsServiceccc() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String pwd = passwordEncoder().encode("1234");
        System.out.println(pwd);
        manager.createUser(User.withUsername("wll").password(pwd).roles("USER").build());
        return manager;
    }
}
