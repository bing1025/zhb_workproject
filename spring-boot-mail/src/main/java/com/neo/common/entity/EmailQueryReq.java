package com.neo.common.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 邮件请求参数
 * @author zhouhb
 * @date 2019/07/01
 *
 */
@Data
public class EmailQueryReq {
	
	/*
	 * 邮件内容
	 */
    @NotNull(message = "邮件内容不能为空")
    protected String  content;
    
    /*
	 * 邮件主题
	 */
    @NotNull(message = "邮件主题不能为空")
    protected String subject;
    
    /*
   	 * 附件 的路径
   	 */
    protected String filePath;
    
    
    /*
   	 * 查询 邮件的条件 ：根据名称单发邮件功能
   	 */
    String userId;
    
    
    /*
   	 * 查询 邮件的条件 ：根据群组发送邮件功能（支持单选或者多选）
   	 */
    List<String>  mailGroups;
    
    /*
   	 * 查询 邮件的条件 ：批量根据id发邮件功能（支持单选或者多选）
   	 */
    List<Long> ids;

}
