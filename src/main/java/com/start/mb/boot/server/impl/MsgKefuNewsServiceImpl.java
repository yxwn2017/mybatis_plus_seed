package com.start.mb.boot.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.start.mb.boot.model.entity.MsgKefuNews;
import com.start.mb.boot.persistence.mapper.MsgKefuNewsMapper;
import com.start.mb.boot.server.MsgKefuNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消息图文消息 服务实现类
 * </p>
 *
 * @author wll
 * @since 2020-03-27
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MsgKefuNewsServiceImpl extends ServiceImpl<MsgKefuNewsMapper, MsgKefuNews> implements MsgKefuNewsService {

    private final MsgKefuNewsMapper msgKefuNewsMapper;

    @Override
    public List<MsgKefuNews> getMsgList() {
        QueryWrapper<MsgKefuNews> queryWrapper = new QueryWrapper<>();
        return msgKefuNewsMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<MsgKefuNews> getMsgListForPage(IPage page) {

        IPage<MsgKefuNews> pageDate = msgKefuNewsMapper.selectPage(page, null);
        return pageDate;
    }
}
