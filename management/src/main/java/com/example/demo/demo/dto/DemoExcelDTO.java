package com.example.demo.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/7/19
 **/
@Getter
@Setter
public class DemoExcelDTO {

    private Long id;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private Double doubleData;

}
