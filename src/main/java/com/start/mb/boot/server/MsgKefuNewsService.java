package com.start.mb.boot.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.start.mb.boot.model.entity.MsgKefuNews;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <p>
 * 消息图文消息 服务类
 * </p>
 *
 * @author wll
 * @since 2020-03-27
 */
public interface MsgKefuNewsService extends IService<MsgKefuNews> {

    List<MsgKefuNews> getMsgList();

    IPage<MsgKefuNews> getMsgListForPage(IPage page);
}
