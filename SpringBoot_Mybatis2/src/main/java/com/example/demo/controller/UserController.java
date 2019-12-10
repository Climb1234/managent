package com.example.demo.controller;

import com.example.demo.dto.UserParam;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.CommonResult;
import com.example.demo.utils.Constants;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/23
 **/
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        String token = userService.login(user);
        if(!StringUtils.isEmpty(token)){
            String tokenHeader = "Authorization";
            HashMap tokenMap = new HashMap();
            tokenMap.put("token", token);
            tokenMap.put("tokenHead", tokenHeader);
            return Result.success(tokenMap);
        }
        return Result.failed(
                Constants.ErrorCode.LOGIN_FAILED.getValue(),
                Constants.ErrorCode.LOGIN_FAILED.getMessage());
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id){
        return Result.success(userService.getById(id));
    }

    @PostMapping
    public Result insert(@RequestBody User user){
        return Result.success(userService.insert(user));
    }

    @GetMapping("/list")
    public CommonResult getUsers(@RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 UserParam param){
        return new CommonResult().pageSuccess(userService.getUsers(param, pageNum, pageSize));
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody User user){
        return Result.success(userService.updateUser(user));
    }

    /*@DeleteMapping("/all")
    public void deleteAllUsers(){
        userService.deleteAllUsers();
    }*/

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable int id){
        return Result.success(userService.deleteUser(id));
    }

    @GetMapping("/hi")
    public Result index(){
        return Result.success("hello spring boot");
    }
}
