package com.start.mb.boot.rest;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.mb.boot.model.entity.MsgKefuNews;
import com.start.mb.boot.server.MsgKefuNewsService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息图文消息 前端控制器
 * </p>
 *
 * @author wll
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/api/core/msg")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MsgKefuNewsController extends BaseController {

    private final MsgKefuNewsService msgKefuNewsService;

    @GetMapping
    public ResponseEntity getMsgList(){
        return createResponseEntity(msgKefuNewsService.getMsgList());
    }

    @GetMapping("/page")
    public ResponseEntity getMsgListForPage(Page page){
        return createResponseEntity(msgKefuNewsService.getMsgListForPage(page));
    }



}
