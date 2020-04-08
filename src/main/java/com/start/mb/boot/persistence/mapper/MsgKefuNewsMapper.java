package com.start.mb.boot.persistence.mapper;

import com.start.mb.boot.model.entity.MsgKefuNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 消息图文消息 Mapper 接口
 * </p>
 *
 * @author wll
 * @since 2020-03-27
 */
public interface MsgKefuNewsMapper extends BaseMapper<MsgKefuNews> {


    /**
     * 列表查询课程可用的代金券
     *
     * @param studentId
     * @return
     */
    List<MsgKefuNews> listByCourse(@Param("studentId") Long studentId);
}
