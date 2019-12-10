package com.example.demo.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Result {
    //操作成功
    public static final String SUCCESS = "200";
    //操作失败
    public static final String FAILED = "500";
    private String code;
    private String message;
    private Object data;

    public static Result success() {
        return ok(null).build();
    }

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public static Result success(Object data) {
        return ok(data).build();
    }

    /**
     * 普通成功返回
     */
    public static Result success(String message, Object data) {
        return ok(data).message(message).build();
    }

    /**
     * 普通失败提示信息
     */
    public static Result failed() {
        return notOk().build();
    }

    public static Result failed(String message) {
        return notOk().message(message).build();
    }

    public static Result failed(String code, String message){
        return Result.builder().code(code).message(message).build();
    }

    private static Result.ResultBuilder ok(Object data) {
        return Result.builder().code(SUCCESS).data(data);
    }

    private static Result.ResultBuilder notOk() {
        return Result.builder().code(FAILED);
    }
}
