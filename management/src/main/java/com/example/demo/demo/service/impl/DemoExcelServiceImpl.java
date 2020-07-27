package com.example.demo.demo.service.impl;

import com.example.demo.demo.entity.DemoExcel;
import com.example.demo.demo.mapper.DemoExcelMapper;
import com.example.demo.demo.service.IDemoExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/27
 **/
@Service
public class DemoExcelServiceImpl implements IDemoExcelService {
    @Autowired
    private DemoExcelMapper demoExcelMapper;

    /**
     * 批量保存
     * @param list
     */
    @Override
    public void saveBath(List<DemoExcel> list) {
        int count = demoExcelMapper.insertBatch(list);
        System.out.println("数据插入===" + count + "条");
    }
}
