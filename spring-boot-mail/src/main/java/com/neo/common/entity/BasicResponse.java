package com.neo.common.entity;

import java.util.List;

import lombok.Data;

/**
 * 基础返回类
 * @author zhouhb
 * @date 2019/07/01
 *
 */
@Data
public class BasicResponse {

    protected String resultCode;

    protected String message;

    public BasicResponse() {
        super();
    }

    public static BasicResponse success(String message) {
        return new BasicResponse("0", message);
    }

    public static BasicResponse error(String code, String message) {
        return new BasicResponse(code, message);
    }

    public static BasicResponse error(String message) {
        return new BasicResponse("1", message);
    }

    public BasicResponse(String resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "BasicResponse{" +
                "resultCode='" + resultCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}