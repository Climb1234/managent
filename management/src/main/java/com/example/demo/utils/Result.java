package com.example.demo.utils;

import com.github.pagehelper.PageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 通用返回对象
 * @Author Climb
 * @Date 2019/11/19
 **/

@Getter
@Setter
@Builder
public class Result {
    private Integer code;
    private String message;
    private Boolean success;
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
     * 返回分页成功数据
     * @param data
     * @return
     */
    public static Result pageSuccess(List data){
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> result = new HashMap<>(6);
        result.put("pageSize", pageInfo.getPageSize());
        result.put("totalPage", pageInfo.getPages());
        result.put("total", pageInfo.getTotal());
        result.put("pageNum", pageInfo.getPageNum());
        result.put("list", pageInfo.getList());
        return ok(result).build();
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

    public static Result failed(Integer code, String message){
        return Result.builder().code(code).success(false).message(message).build();
    }

    private static Result.ResultBuilder ok(Object data) {
        return Result.builder().code(Constants.ErrorCode.SUCCESS.getValue()).success(true).data(data);
    }

    private static Result.ResultBuilder notOk() {
        return Result.builder().code(Constants.ErrorCode.INTERNAL_SERVER_ERROR.getValue()).success(false);
    }
}
