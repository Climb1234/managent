package com.example.demo.system.mapper;

import com.example.demo.system.dto.RoleDto;
import com.example.demo.system.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/8
 **/
@Repository
public interface RoleMapper {
    /**
     * 获取角色列表
     * @param params
     * @return
     */
    List<Role> getList(@Param("params") Map<String, Object> params);

    /**
     * 新增
     * @param role
     * @return
     */
    int save(Role role);

    /**
     * 修改
     * @param role
     * @return
     */
    int update(Role role);

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
