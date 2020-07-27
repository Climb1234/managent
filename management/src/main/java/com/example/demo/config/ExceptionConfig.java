package com.example.demo.config;

import com.example.demo.utils.Result;
import com.example.demo.utils.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 统一处理异常返回
 * @Author Climb
 * @Date 2020/7/18
 **/
@ControllerAdvice
public class ExceptionConfig {
    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Result bizExceptionHandler(ServiceException e) {
        return Result.failed(e.getMessage());
    }

    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        return Result.failed();
    }
}
