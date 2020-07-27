package com.example.demo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/30
 **/
@Getter
@Setter
public class Query {
    @ApiModelProperty("当前页")
    private Integer pageNum = 1;
    @ApiModelProperty("每页数量")
    private Integer pageSize = 10;
}
