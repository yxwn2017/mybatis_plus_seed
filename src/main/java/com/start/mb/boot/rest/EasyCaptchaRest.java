package com.start.mb.boot.rest;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wll
 * @desc EasyCaptcha
 * @link
 * @date 2020/4/8 6:47 下午
 */
@RestController
@RequestMapping("/api/captcha")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EasyCaptchaRest extends BaseController {


    @GetMapping
    @SneakyThrows
    public void captcha(){
        // 设置位数
        CaptchaUtil.out(5, request, response);
        // 设置宽、高、位数
        CaptchaUtil.out(130, 48, 5, request, response);

        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130,48,4);
        CaptchaUtil.out(gifCaptcha, request, response);
    }

}
