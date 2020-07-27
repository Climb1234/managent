package com.example.demo.utils;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/4
 **/
public class ServiceException extends RuntimeException {
    private String errorMessage;

    public ServiceException(String errorMessage){
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage(){
        return this.errorMessage;
    }
}
