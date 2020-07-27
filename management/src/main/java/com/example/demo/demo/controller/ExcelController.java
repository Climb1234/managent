package com.example.demo.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.example.demo.demo.listener.DemoExcelListener;
import com.example.demo.demo.entity.DemoExcel;
import com.example.demo.demo.mapper.DemoExcelMapper;
import com.example.demo.demo.service.IDemoExcelService;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/29
 **/
@RestController
@Api("Excel接口")
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private IDemoExcelService excelService;

    @Autowired
    private DemoExcelMapper excelMapper;

    @ApiOperation("Excel上传")
    @PostMapping("upload")
    public Result excelUpload(MultipartFile file) throws IOException {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        /*String fileName = uploadDir + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener(new DemoDAO())).sheet().doRead();*/

        // 写法2：
        /*String fileName = uploadDir + File.separator + "demo.xlsx";
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }*/

        EasyExcel.read(file.getInputStream(), DemoExcel.class, new DemoExcelListener(excelService)).sheet().doRead();
        return Result.success();
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public Result getPageList() {
        return Result.pageSuccess(excelMapper.getPageList());
    }

    @DeleteMapping
    @ApiOperation("删除")
    public Result remove(@RequestParam String ids) {
        excelMapper.remove(ids);
        return Result.success();
    }

    @GetMapping("model")
    @ApiOperation("模型下载")
    public void downloadModel(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("test", "UTF-8") + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        EasyExcel.write(out).head(DemoExcel.class).sheet("测试").doWrite(new ArrayList());
        out.flush();
    }
}
