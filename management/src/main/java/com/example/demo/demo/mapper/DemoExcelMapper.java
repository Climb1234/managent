package com.example.demo.demo.mapper;

import com.example.demo.demo.entity.DemoExcel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/27
 **/
@Repository
public interface DemoExcelMapper {
    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(List<DemoExcel> list);

    /**
     * 分页
     * @return
     */
    List<DemoExcel> getPageList();

    /**
     * 删除
     * @param ids
     * @return
     */
    int remove(String ids);
}
