package com.example.demo.system.controller;

import com.example.demo.common.Query;
import com.example.demo.system.entity.User;
import com.example.demo.system.service.IUserService;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/23
 *
 **/

@Api(value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation("通过token获取用户信息")
    @GetMapping("/info")
    public Result getUserInfo(@RequestParam(value = "token") String token){
        HashMap info = new HashMap(5);
        info.put("roles", "admin");
        info.put("introduction", "I am a super administrator");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "Super Admin");
        return Result.success(info);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success();
    }

    @ApiOperation("获取指定用户及其角色")
    @GetMapping("/{id}")
    public Result getById(@PathVariable long id){
        return Result.success(userService.getUserAndRole(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType = "String")
    })
    @ApiOperation("获取用户及角色列表")
    @GetMapping("/list")
    public Result getUsers(Query query, @ApiIgnore @RequestParam Map<String, Object> params){
        return Result.pageSuccess(userService.getUserAndRoleList(query, params));
    }

    @ApiOperation("添加或修改用户信息")
    @PostMapping
    public Result addOrUpdateUser(@RequestBody User user){
        userService.addOrUpdateUser(user);
        return Result.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable long id){
        return Result.success(userService.deleteUser(id));
    }
}
