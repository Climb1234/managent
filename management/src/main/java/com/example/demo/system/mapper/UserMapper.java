package com.example.demo.system.mapper;

import com.example.demo.system.dto.UserDto;
import com.example.demo.system.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/22
 **/
@Repository
public interface UserMapper {
    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    User getById(long id);

    /**
     * 用户新增
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(long id);


    /**
     * 获取指定用户信息及角色
     * @param id
     * @return
     */
    UserDto getUserAndRole (Long id);

    /**
     * 获取用户信息及其角色
     * @param params
     * @return
     */
    List<UserDto> getUserAndRoleList(@Param("params") Map<String, Object> params);

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 统计用户数量
     * @param params
     * @return
     */
    int countUser(@Param("params") Map<String, Object> params);
}
