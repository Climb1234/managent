package com.example.demo.system.mapper;

import com.example.demo.system.dto.PermissionDto;
import com.example.demo.system.dto.PermissionMenuDto;
import com.example.demo.system.dto.PermissionTreeDto;
import com.example.demo.system.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/3
 **/
@Repository
public interface PermissionMapper {
    /**
     * 通过权限ID获取菜单列表
     * @param roleId
     * @return 菜单列表
     */
    List<PermissionMenuDto> listByRoleId(Long roleId);

    /**
     * 获取菜单树
     * @return
     */
    List<PermissionTreeDto> queryTree();

    /**
     * 获取菜单列表
     * @param pid
     * @return
     */
    List<PermissionDto> listPermissionByPid(Long pid);

    /**
     * 新增
     * @param permission
     * @return
     */
    int save(Permission permission);

    /**
     * 修改
     * @param permission
     * @return
     */
    int update(Permission permission);

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

    /**
     * 计数
     * @param params
     * @return
     */
    int count(@Param("params") Map<String, Object> params);
}
