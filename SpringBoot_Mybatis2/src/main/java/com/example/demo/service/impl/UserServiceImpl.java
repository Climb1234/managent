package com.example.demo.service.impl;

import com.example.demo.dto.UserParam;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.Token;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/23
 **/

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(int id) {
        return userMapper.getById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> getUsers(UserParam param, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.getUsers(param);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public void deleteAllUsers() {
        userMapper.deleteAllUsers();
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public String login(User user) {
        if (null != user.getUsername() && null != user.getPassword()){
            UserParam param = new UserParam();
            param.setUsername(user.getUsername());
            param.setPassword(user.getPassword());
            List<User> list = this.getUsers(param, 1, 5);
            if (list.size() > 0){
                //账户、密码验证正确，返回token
                return Token.createJWT(user.getUsername(), user.getPassword());
            }
            return "";
        }
        return "";
    }
}

