package com.example.demo.system.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/2
 **/
@Getter
@Setter
public class PermissionMenuDto {
    /**
     * 请求路径
     */
    private String path;

    /**
     * 前端资源路径
     */
    private String component;

    private String redirect;

    /**
     * 名称和图标
     */
    private Meta meta;

    private List<Children> children;

}

@Getter
@Setter
class Meta{
    /**
     * 名称
     */
    private String title;

    /**
     * 图标
     */
    private String icon;
}

@Getter
@Setter
class Children {
    private String path;

    private String component;

    private Meta meta;
}
