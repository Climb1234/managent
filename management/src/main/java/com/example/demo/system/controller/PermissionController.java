package com.example.demo.system.controller;

import com.example.demo.system.dto.PermissionDto;
import com.example.demo.system.service.IPermissionService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/3
 **/
@Api("菜单管理")
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @ApiOperation("通过token获取该用户关联的左侧菜单")
    @GetMapping("/list")
    public Result getPermissionByUser(HttpServletRequest request){
        String authorization = request.getHeader(Constants.TOKEN_HEADER);
        if (!StringUtils.isEmpty(authorization)) {
            return Result.success(permissionService.listPermisssionByToken(authorization));
        }
        return Result.success();
    }

    @ApiOperation("获取菜单列表")
    @GetMapping("/list-lazy")
    public Result listPermission(@RequestParam(defaultValue = "0") Long pid){
        return Result.success(permissionService.listPermissionByid(pid));
    }

    @ApiOperation("获取菜单树")
    @GetMapping("/tree")
    public Result queryLazyTree() {
        return Result.success(permissionService.queryTree());
    }

    @ApiOperation("新增或修改")
    @PostMapping
    public Result addOrUpdate(@RequestBody PermissionDto permissionDto){
        return Result.success(permissionService.addOrUpdate(permissionDto));
    }

    @ApiOperation("详情")
    @GetMapping("{id}")
    public Result detail(@PathVariable Long id) {
        return Result.success(permissionService.detail(id));
    }

    @ApiOperation("删除")
    @DeleteMapping
    public Result remove(@RequestParam String ids) {
        return Result.success(permissionService.remove(ids));
    }
}
