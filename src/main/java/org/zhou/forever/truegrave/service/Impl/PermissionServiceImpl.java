package org.zhou.forever.truegrave.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhou.forever.truegrave.mapper.PermissionMapper;
import org.zhou.forever.truegrave.service.IPermissionService;

import java.util.Set;

/**
 * @ClassName PermissionServiceImpl
 * @Description permissionServiceImpl
 * @Author Mr.wang
 * @Date 2019/10/21 14:11
 **/
@Component
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Set<String> findPermissionsById(Integer roleId) {
        return permissionMapper.selectPermissionsById(roleId);
    }
}
