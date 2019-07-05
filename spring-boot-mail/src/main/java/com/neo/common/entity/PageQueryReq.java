package com.neo.common.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * 分页请求参数
 * @author zhouhb
 * @date 2019/07/01
 *
 */
@Data
public class PageQueryReq {

	/*
	 * 每页条数
	 */
    @NotNull(message = "查询参数pageNum非法")
    @Min(value = 1, message = "查询条数必须大于1")
    protected Integer pageNum;

    /*
	 * 页数码
	 */
    @NotNull(message = "查询参数pageSize非法")
    @Min(value = 0, message = "查询起始索引必须大于0")
    protected Integer pageSize;

    /*
	 * 页排序字段
	 */
    @NotBlank(message = "查询参数order非法")
    protected String order;

    /*
	 * 正序 asc，逆序  desc
	 */
    @NotBlank(message = "查询参数sort非法")
    protected String sort;
    
    /*
	 * 分页查询条件
	 */
    protected Object params;

}
