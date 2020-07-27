package com.example.demo.system.service.impl;

import com.example.demo.system.dto.PermissionDto;
import com.example.demo.system.dto.PermissionMenuDto;
import com.example.demo.system.dto.PermissionTreeDto;
import com.example.demo.system.entity.Permission;
import com.example.demo.system.entity.User;
import com.example.demo.system.mapper.PermissionMapper;
import com.example.demo.system.mapper.UserMapper;
import com.example.demo.system.service.IPermissionService;
import com.example.demo.utils.ServiceException;
import com.example.demo.utils.Token;
import io.jsonwebtoken.Claims;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/1/3
 **/
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionMenuDto> listPermisssionByToken(String token) {
        if (!StringUtils.isEmpty(token)){
            Claims claims = Token.parseToken(token.split(" ")[1]);
            User user = userMapper.getByUsername(claims.getSubject());
            if (null != user){
                List<PermissionMenuDto> permissionDto = permissionMapper.listByRoleId(user.getRoleId());
                return permissionDto;
            }
        }
        throw new ServiceException("账号或密码错误！");
    }

    /**
     * 获取菜单树
     * @return
     */
    @Override
    public List<PermissionTreeDto> queryTree() {
        return permissionMapper.queryTree();
    }

    /**
     * 通过pid  获取菜单列表
     * @param pid
     * @return
     */
    @Override
    public List<PermissionDto> listPermissionByid(Long pid) {
        return permissionMapper.listPermissionByPid(pid);
    }

    /**
     * 新增或修改
     * @param permissionDto
     * @return
     */
    @Override
    public int addOrUpdate(PermissionDto permissionDto) {
        validData(permissionDto);
        Permission permission = mapperFacade.map(permissionDto, Permission.class);
        if (null == permissionDto.getId()) {
            return permissionMapper.save(permission);
        } else {
            return permissionMapper.update(permission);
        }
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public PermissionDto detail(Long id) {
        return permissionMapper.detail(id);
    }

    /**
     * 删除
     * @param ids
     */
    @Override
    public int remove(String ids) {
        Map<String,Object> params = new HashMap<>(5);
        params.put("pids", ids);
        int count = permissionMapper.count(params);
        if (count > 0) {
            throw new ServiceException("删除一级菜单前请先删除子菜单");
        }
        return permissionMapper.remove(ids);
    }

    /**
     * 校验数据
     * @param dto
     */
    private void validData(PermissionDto dto) {
        if (null == dto.getPid()) {
            dto.setPid(0L);
        }
        if (StringUtils.isEmpty(dto.getPath())) {
            throw new ServiceException("请求路径不可为空");
        }
        Map<String, Object> params = new HashMap<>(5);
        params.put("pid", dto.getPid());
        params.put("path", dto.getPath());
        if (null != dto.getId()) {
            params.put("id", dto.getId());
        }

        int count = permissionMapper.count(params);
        if (count > 0) {
            throw new ServiceException("该请求路径已存在");
        }
    }
}
