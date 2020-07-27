package com.example.demo.system.dto;

import com.example.demo.system.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/7
 **/
@Setter
@Getter
public class UserDto extends User {
    private String roleName;
    private String roleDescription;
}
