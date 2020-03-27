package com.start.mb.boot.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.start.mb.boot.common.Exception.RequestParameterError;
import com.start.mb.boot.model.data.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @author wll
 * @version version
 * @title BaseController
 * @desc
 * @date 2020/3/27
 */
public abstract class BaseController {

    @Autowired
    protected  HttpServletRequest request;
    @Autowired
    protected  HttpServletResponse response;


    /**
     * 数组返回格式
     *
     * @param entities
     * @return
     */
    protected ResponseEntity<ResultEntity> createResponseEntity(List<?> entities) {
        ResultEntity response = new ResultEntity();
        response.setNumberOfElements(Objects.isNull(entities) ? 0 : entities.size());
        response.setEntities(entities);
        response.setStatus(ResultEntity.STATUS_OK);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 单个对象返回格式
     *
     * @param entity
     * @return
     */
    protected ResponseEntity<ResultEntity> createResponseEntity(Object entity) {
        ResultEntity response = new ResultEntity();
        response.setEntity(entity);
        response.setStatus(ResultEntity.STATUS_OK);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 分页返回结果
     *
     * @param
     */
    protected ResponseEntity<ResultEntity> createResponseEntity(IPage<?> page) {
        ResultEntity response = new ResultEntity();

        response.setSize(page.getSize());
        response.setTotalPages(page.getPages());
        response.setTotalElements(page.getTotal());
        response.setEntities(page.getRecords());
//        response.setEntity(entity);
        response.setStatus(ResultEntity.STATUS_OK);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    protected void checkBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(e -> sb.append(((FieldError) e).getField()).append(e.getDefaultMessage()).append("  "));
            throw new RequestParameterError(sb.toString());
        }
    }
}
