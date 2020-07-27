package com.example.demo.demo.service;

import com.example.demo.demo.entity.DemoExcel;

import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/27
 **/
public interface IDemoExcelService {
    /**
     * 批量保存
     * @param list
     */
    void saveBath(List<DemoExcel> list);
}
