package com.start.mb.boot.rest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wll
 * @version version
 * @title FeignTestRest
 * @desc
 * @date 2020/3/30
 */
@FeignClient(name = "service-provider")
public interface FeignTestRest {

    @GetMapping("api/echo/{str}")
    String echo(@PathVariable(name = "str") String str);

}
