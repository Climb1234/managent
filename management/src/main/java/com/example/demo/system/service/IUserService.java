package com.example.demo.system.service;

import com.example.demo.common.Query;
import com.example.demo.system.dto.UserDto;
import com.example.demo.system.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/23
 **/
public interface IUserService {
    /**
     * id 获取用户及其角色
     * @param id
     * @return
     */
    UserDto getUserAndRole(long id);

    /**
     * 获取用户及角色列表
     * @param query
     * @param params
     * @return
     */
    List<UserDto> getUserAndRoleList(Query query, Map<String, Object> params);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int addOrUpdateUser(User user);

    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    int deleteUser(long id);

    /**
     * 用户登录
     * @param user
     * @return Token
     */
    HashMap login (User user);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User getByUserName(String username);
}
