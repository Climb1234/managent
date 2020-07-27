package com.example.demo.system.controller;

import com.example.demo.common.Query;
import com.example.demo.system.dto.RoleDto;
import com.example.demo.system.entity.Role;
import com.example.demo.system.service.IRoleService;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/8
 **/
@Api("角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "query", dataType = "String")
    })
    @ApiOperation("分页 获取角色列表")
    @GetMapping("/page")
    public Result getPageList(@ApiIgnore @RequestParam Map<String, Object> params, Query query){
        return Result.pageSuccess(roleService.getPageList(query, params));
    }

    @ApiOperation("非分页 获取角色列表")
    @GetMapping("/list")
    public Result getList(@ApiIgnore @RequestParam Map<String, Object> params){
        return Result.success(roleService.getList(params));
    }

    @ApiOperation("新增或修改")
    @PostMapping
    public Result addOrUpdate(@RequestBody RoleDto roleDto) {
        roleService.addOrUpdate(roleDto);
        return Result.success();
    }

    @ApiOperation("详情")
    @GetMapping("{id}")
    public Result detail(@PathVariable Long id) {
        return Result.success(roleService.detail(id));
    }

    @ApiOperation(value = "删除", notes = "ids")
    @DeleteMapping
    public Result remove(@RequestParam String ids) {
        return Result.success(roleService.remove(ids));
    }
}
