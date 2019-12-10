package com.example.demo.mapper;

import com.example.demo.dto.UserParam;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2019/10/22
 **/
@Repository
public interface UserMapper {
    User getById(int id);

    int insert(User user);

    List<User> getUsers(@Param("param") UserParam param);

    int updateUser(User user);

    void deleteAllUsers();

    int deleteUser(int id);
}
