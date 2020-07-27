package com.example.demo.utils;

import lombok.Getter;

/**
 * @Description 公共静态常量、枚举
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
    /**
     * Token中的过期时间(单位：ms)(7天)
     */
    public static final long TTL_MILLIS = 7*60*60*24*1000;

    public static final String TOKEN_HEADER = "Authorization";

    /**
     * 错误码
     */
    @Getter
    public enum  ErrorCode{
        /**
         * 成功
         */
        SUCCESS(200, "成功"),
        /**
         * 未授权
         */
        LOGIN_FAILED(4011, "账号或密码错误"),
        /**
         * 运行错误
         */
        INTERNAL_SERVER_ERROR(500, "服务器跑路了");

        private Integer value;
        private String message;
        ErrorCode(Integer value, String message){
            this.value = value;
            this.message = message;
        }
    }
}
