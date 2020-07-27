package com.example.demo.system.service;

import com.example.demo.common.Query;
import com.example.demo.system.dto.RoleDto;
import com.example.demo.system.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/7/1
 **/
public interface IRoleService {
    /**
     * 分页  角色列表
     * @param query
     * @param params
     * @return
     */
    List<Role> getPageList(Query query, Map<String, Object> params);

    /**
     * 非分页  角色列表
     * @param params
     * @return
     */
    List<Role> getList(Map<String, Object> params);

    /**
     * 新增或修改
     * @param roleDto
     */
    void addOrUpdate(RoleDto roleDto);

    /**
     * 详情
     * @param id
     * @return
     */
    RoleDto detail(Long id);

    /**
     * 删除
     * @param ids
     * @return
     */
    int remove(String ids);
}
