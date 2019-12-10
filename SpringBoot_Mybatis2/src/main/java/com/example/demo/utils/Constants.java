package com.example.demo.utils;

import lombok.Getter;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/11/19
 **/

@Getter
public class Constants {
    private Constants() {}

    /**
     * Token 中的固定值
     */
    public static final String ID = "id";
    public static final long TTL_MILLIS = 123456;

    //错误码
    @Getter
    public enum  ErrorCode{
        //登陆失败
        LOGIN_FAILED("401.1", "账号或密码错误");

        private String value;
        private String message;
        ErrorCode(String value, String message){
            this.value = value;
            this.message = message;
        }
    }
}
