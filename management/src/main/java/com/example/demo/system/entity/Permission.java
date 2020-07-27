package com.example.demo.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/2
 **/
@Getter
@Setter
public class Permission {
    private long id;

    /**
     * 父级权限id
     */
    private long pid;

    /**
     * 名称
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；
     */
    private int type;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 前端资源路径
     */
    private String component;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 排序
     */
    private int sort;
}
