package com.example.demo.shiro.controller;

import com.example.demo.system.entity.User;
import com.example.demo.system.service.IUserService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/12
 **/
@Api("授权管理")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @ApiOperation("未登录")
    @GetMapping("/notlogin")
    public Result notLogin() {
        return Result.failed("未登录");
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        try{
            return Result.success(userService.login(user));
        } catch (Exception e) {
            Constants.ErrorCode errorCode = Constants.ErrorCode.LOGIN_FAILED;
            return Result.failed(errorCode.getValue(), errorCode.getMessage());
        }
    }
}
