package com.start.mb.boot.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wll
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("persistent_logins")
public class PersistentLogins extends Model<PersistentLogins> {

    private static final long serialVersionUID = 1L;

    private String username;

    private String series;

    private String token;

    private LocalDateTime lastUsed;

    /**
     * 删除标识:0-未删除;1-删除
     */
    @TableLogic
    private Integer delFlag;


    @Override
    protected Serializable pkVal() {
        return this.series;
    }

}
