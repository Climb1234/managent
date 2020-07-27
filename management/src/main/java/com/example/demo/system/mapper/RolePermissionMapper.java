package com.example.demo.system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/7/2
 **/
@Repository
public interface RolePermissionMapper {

    /**
     * 新增
     * @param roleId
     * @param permissionIdList
     * @return
     */
    int save(@Param("roleId") Long roleId, @Param("permissionIdList") List<Long> permissionIdList);

    /**
     * 通过roleId 删除该角色关联的菜单
     * @param roleId
     * @return
     */
    int removeByRoleId(Long roleId);
}
