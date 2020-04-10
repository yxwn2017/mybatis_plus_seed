package com.start.mb.boot.middleware.security.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author wll
 * @desc SmsCode 短信验证码
 * @link
 * @date 2020/4/10 9:42 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCode {
    private String code;
    private LocalDateTime expireTime;


    public SmsCode(String code, int i) {
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(i);
    }

    public boolean isExpire() {
        if(Objects.isNull(code)){
            return true;
        }
        if(LocalDateTime.now().isAfter(expireTime)){
            return true;
        }
        return false;
    }
}
