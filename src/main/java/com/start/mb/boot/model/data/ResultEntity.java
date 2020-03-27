package com.start.mb.boot.model.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author wll
 * @version version
 * @title ResultEntity
 * @desc
 * @date 2020/3/27
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultEntity<T> {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_FAIL = "ERROR";

    public ResultEntity(){
        this(STATUS_OK, null);
    }

    public ResultEntity(String status, String errorDescription){
        this.status = status;
        this.errorDescription = errorDescription;
        this.responseDate=new Date();
    }

    /**
     * 结果状态：OK/ERROR
     * */
    private String status;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误信,用作前端提示
     * */
    private String errorDescription;

    /**
     * 错误时间戳
     */
    private Long errorTime;

    /**
     * 存放多个返回结果
     */
    private List<T> entities;

    /**
     * 存放单个返回结果
     */
    private T entity;

    /**
     * 分页查询时使用,是否第一页
     */
    private Boolean first;
    /**
     * 分页查询时使用,是否最后一页
     */
    private Boolean last;
    /**
     * 一页的记录个数
     */
    private Long size;
    /**
     * 当前页,从0开始
     */
    private Integer number;
    /**
     * 当前页的记录个数
     */
    private Integer numberOfElements;
    /**
     * 总共多少页
     */
    private Long totalPages;
    /**
     * 总共多少记录
     */
    private Long totalElements;

    private Date responseDate;

    /**
     * 错误信息
     */
    public static ResultEntity fail(String msg){
        return new ResultEntity(STATUS_FAIL, msg);
    }

    /**
     * 处理成功信息
     */
    public static ResultEntity success(String msg){
        return new ResultEntity(STATUS_OK, msg);
    }
}
