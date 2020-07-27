package com.example.demo.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/8
 **/
@Api("文件管理")
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${app.uploadDir}")
    private String uploadDir;

    @ApiOperation("文件上传(图片、文件)")
    @PostMapping("/upload")
    public String fileUpload(@RequestPart("file") MultipartFile file){
        if(file.isEmpty()){
            return "上传失败";
        }
        String fileName = file.getOriginalFilename();

        File dest = new File(uploadDir + File.separator + fileName);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return "上传成功";
        } catch (Exception e) {
            // TODO
            return "上传失败";
        }
    }

    @ApiOperation("图片下载(.png/.jpg)")
    @GetMapping("/downloadImg")
    public void fileDownload(@RequestParam(value = "fileName") String fileName, HttpServletResponse response){
        try {
            // 得到要下载的文件名
            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");

            // 得到要下载的文件
            File file = new File(uploadDir + File.separator + fileName);

            // 如果文件不存在
            if (!file.exists()) {
                return ;
            }
            // 设置响应头，控制浏览器下载该文件
            if (fileName.contains(".jpg")){
                response.setContentType("image/jpeg");
            }else{
                response.setContentType("image/png");
            }
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(uploadDir + File.separator + fileName);
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();
        } catch (Exception e) {

        }
    }
}
