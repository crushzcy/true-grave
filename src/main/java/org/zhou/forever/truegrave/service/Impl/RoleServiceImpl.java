package org.zhou.forever.truegrave.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhou.forever.truegrave.domain.Role;
import org.zhou.forever.truegrave.mapper.RoleMapper;
import org.zhou.forever.truegrave.service.IRoleService;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author Mr.wang
 * @Date 2019/10/21 14:30
 **/
@Component
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role findRoleByName(String roleName) {
        return roleMapper.selectByName(roleName);
    }
}
