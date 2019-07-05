package com.neo.common.entity;


/**
 * 高级返回类
 * @author zhouhb
 * @date 2019/07/01
 *
 */

public class DataRes<T> extends BasicResponse {

    private T data;

    public DataRes(String resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public DataRes(String resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public static DataRes success(String message) {
        return new DataRes("0", message);
    }


    public static DataRes error(String code, String message) {
        return new DataRes(code, message);
    }

    public static DataRes error(String message) {
        return new DataRes("1", message);
    }


    public DataRes() {
        super();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "DataRes{" +
                "resultCode='" + resultCode + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}