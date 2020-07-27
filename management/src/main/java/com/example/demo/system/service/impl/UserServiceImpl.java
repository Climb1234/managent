package com.example.demo.system.service.impl;

import com.example.demo.common.Query;
import com.example.demo.system.dto.UserDto;
import com.example.demo.system.entity.User;
import com.example.demo.system.mapper.UserMapper;
import com.example.demo.system.service.IUserService;
import com.example.demo.utils.ServiceException;
import com.example.demo.utils.Token;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/23
 **/

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户及角色列表
     * @param query
     * @param params
     * @return
     */
    @Override
    public List<UserDto> getUserAndRoleList(Query query, Map<String, Object> params) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return userMapper.getUserAndRoleList(params);
    }

    /**
     * id 获取用户及其角色
     * @param id
     * @return
     */
    @Override
    public UserDto getUserAndRole(long id) {
        return userMapper.getUserAndRole(id);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Override
    public int addOrUpdateUser(User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            throw new ServiceException("用户名不可为空");
        }
        Map<String, Object> params = new HashMap<>(5);
        params.put("username", user.getUsername());
        if (null != user.getId()) {
            params.put("excludeId", user.getId());
        }
        int count = userMapper.countUser(params);
        if (count > 0) {
            throw new ServiceException("该用户名已经存在");
        }

        if (null != user.getId()){
            return userMapper.updateUser(user);
        }else{
            user.setCreateTime(new Date());
            return userMapper.insert(user);
        }
    }

    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteUser(long id) {
        return userMapper.deleteUser(id);
    }

    /**
     * 用户登录
     * @param user
     * @return Token
     */
    @Override
    public HashMap login(User user) throws UnknownAccountException, IncorrectCredentialsException {
        HashMap map = new HashMap(5);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(usernamePasswordToken);
        user = (User)subject.getPrincipal();
        UserDto userDto = getUserAndRole(user.getId());
        String token = Token.createToken(user.getUsername(), userDto.getRoleName());
        map.put("token", token);
        map.put("tokenHead", "Authorization");
        map.put("avatar", user.getAvatar());
        return map;
    }

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    @Override
    public User getByUserName(String username) {
        return userMapper.getByUsername(username);
    }
}

