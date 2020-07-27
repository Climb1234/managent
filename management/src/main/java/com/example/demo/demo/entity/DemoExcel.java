package com.example.demo.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/27
 **/
@Data
public class DemoExcel {
    @ExcelProperty("用户")
    private String username;

    @ExcelProperty("生日")
    @DateTimeFormat("yyyy/MM/dd HH:mm:ss")
    private Date birthday;

    @ExcelProperty("doubleData")
    private Double doubleData;
}
