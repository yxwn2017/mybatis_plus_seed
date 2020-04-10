package com.start.mb.boot.rest;

import com.start.mb.boot.middleware.security.sms.SmsCode;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wll
 * @desc EasyCaptcha
 * @link
 * @date 2020/4/8 6:47 下午
 */
@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EasyCaptchaRest extends BaseController {


    @GetMapping("captcha")
    @SneakyThrows
    public void captcha() {
        // 设置位数
        CaptchaUtil.out(5, request, response);
        // 设置宽、高、位数
        CaptchaUtil.out(130, 48, 5, request, response);

        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
        CaptchaUtil.out(gifCaptcha, request, response);
    }

    @GetMapping("sms")
    public void sms(@RequestParam(name = "mobile") String mobile) {

        String code = RandomStringUtils.randomNumeric(6);
        SmsCode smsCode = new SmsCode(code, 60);
        log.info("for info, 短信验证码服务 验证码 |{}|,有效时间为 60 秒", code);

    }
}
