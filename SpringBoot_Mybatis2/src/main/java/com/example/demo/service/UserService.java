package com.example.demo.service;

import com.example.demo.dto.UserParam;
import com.example.demo.entity.User;

import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/23
 **/
public interface UserService {
    User getById(int id);

    int insert(User user);

    List<User> getUsers(UserParam param, Integer pageNum, Integer pageSize);

    int updateUser(User user);

    void deleteAllUsers();

    int deleteUser(int id);

    String login (User user);
}
