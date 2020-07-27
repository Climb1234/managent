package com.example.demo.system.service.impl;

import com.example.demo.common.Query;
import com.example.demo.system.dto.RoleDto;
import com.example.demo.system.entity.Role;
import com.example.demo.system.mapper.RoleMapper;
import com.example.demo.system.mapper.RolePermissionMapper;
import com.example.demo.system.service.IRoleService;
import com.example.demo.utils.ServiceException;
import com.github.pagehelper.PageHelper;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/7/1
 **/
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 分页  角色列表
     * @param query
     * @param params
     * @return
     */
    @Override
    public List<Role> getPageList(Query query, Map<String, Object> params) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return roleMapper.getList(params);
    }

    /**
     * 非分页  角色列表
     * @param params
     * @return
     */
    @Override
    public List<Role> getList(Map<String, Object> params) {
        return roleMapper.getList(params);
    }

    /**
     * 新增或修改
     * @param roleDto
     */
    @Override
    public void addOrUpdate(RoleDto roleDto) {
        if (roleDto.getPermissionIdList().isEmpty()) {
            throw new ServiceException("请选择授权菜单");
        }
        Role role = mapperFacade.map(roleDto, Role.class);
        if (null == roleDto.getId()) {
            role.setGmtCreate(new Date());
            roleMapper.save(role);
            rolePermissionMapper.save(role.getId(), roleDto.getPermissionIdList());
        }else {
            rolePermissionMapper.removeByRoleId(roleDto.getId());
            rolePermissionMapper.save(roleDto.getId(), roleDto.getPermissionIdList());
            roleMapper.update(role);
        }
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public RoleDto detail(Long id) {
        return roleMapper.detail(id);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @Override
    public int remove(String ids) {
        return roleMapper.remove(ids);
    }
}
