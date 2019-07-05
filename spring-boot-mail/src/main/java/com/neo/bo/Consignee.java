package com.neo.bo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 收件人列表 基类
 * @author zhouhb
 * @date 2019/07/01
 *
 */

@Data
public class Consignee implements Serializable {
    private Long id;

    private String userId;

    private String userName;

    private String mailGroup;

    private String userRole;

    private String mailAddr;

    private String phoneNum;

    private Boolean isEnable;

    private String createBy;

    private String updateBy;

    
    /**
     * 时间格式转换，以下三种，任选其一即可   https://blog.csdn.net/qq_28483283/article/details/81326365
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso =DateTimeFormat.ISO.DATE_TIME)
    @JSONField(format="yyyy-MM-dd  HH:mm:ss ")
    private Date updateTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso =DateTimeFormat.ISO.DATE_TIME)
    @JSONField(format="yyyy-MM-dd  HH:mm:ss ")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", mailGroup=").append(mailGroup);
        sb.append(", userRole=").append(userRole);
        sb.append(", mailAddr=").append(mailAddr);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}