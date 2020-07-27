package com.example.demo.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/22
 **/
@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;

    /**
     * 角色ID
     */
    private long roleId;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createTime;
}
