package com.start.mb.boot.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息图文消息
 * </p>
 *
 * @author wll
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MsgKefuNews extends Model<MsgKefuNews> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 公众号appid
     */
    private String appid;

    /**
     * 消息唯一键（用于关联表字段）
     */
    private String msgUid;

    /**
     * 图文素材mediaid
     */
    private String mediaId;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否删除： 0:未删 1:已删
     */
    @TableLogic
    private Integer isDel;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
