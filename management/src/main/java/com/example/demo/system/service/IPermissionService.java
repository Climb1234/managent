package com.example.demo.system.service;

import com.example.demo.system.dto.PermissionDto;
import com.example.demo.system.dto.PermissionMenuDto;
import com.example.demo.system.dto.PermissionTreeDto;

import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/3
 **/
public interface IPermissionService {
    /**
     * 通过解析token获取该用户的菜单列表
     * @param token
     * @return
     */
    List<PermissionMenuDto> listPermisssionByToken (String token);

    /**
     * 获取菜单树
     * @return
     */
    List<PermissionTreeDto> queryTree();

    /**
     * 通过pid 获取菜单列表
     * @param pid
     * @return
     */
    List<PermissionDto> listPermissionByid(Long pid);

    /**
     * 新增或修改
     * @param permissionDto
     * @return
     */
    int addOrUpdate(PermissionDto permissionDto);

    /**
     * 详情
     * @param id
     * @return
     */
    PermissionDto detail(Long id);

    /**
     * 删除
     * @param ids
     * @return
     */
    int remove(String ids);
}
