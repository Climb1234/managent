package com.example.demo.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/7/4
 **/
@ApiModel("菜单DTO")
@Getter
@Setter
public class PermissionDto {
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 父级权限id
     */
    @ApiModelProperty("父级id")
    private Long pid;

    /**
     * 名称
     */
    @ApiModelProperty("菜单名称")
    private String title;

    /**
     * 图标
     */
    @ApiModelProperty("图标")
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；
     */
    @ApiModelProperty("权限类型：0->目录；1->菜单；")
    private int type;

    /**
     * 请求路径
     */
    @ApiModelProperty("请求路径")
    private String path;

    /**
     * 前端资源路径
     */
    @ApiModelProperty("资源路径")
    private String component;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private int sort;

    @ApiModelProperty("是否存在子级")
    private Boolean hasChildren = true;

    @ApiModelProperty("父级菜单名称")
    private String parentName;
}
