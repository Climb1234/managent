package com.example.demo.system.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/7/2
 **/
@Getter
@Setter
public class PermissionTreeDto {
    private Long id;

    private String label;

    private List<PermissionTreeDto> children = new ArrayList<>();
}
