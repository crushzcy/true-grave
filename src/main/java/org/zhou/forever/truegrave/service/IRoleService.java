package org.zhou.forever.truegrave.service;

import org.zhou.forever.truegrave.domain.Role;

/**
 * @ClassName IRoleService
 * @Description interface
 * @Author Mr.wang
 * @Date 2019/10/21 14:28
 **/
public interface IRoleService {

    Role findRoleByName(String name);
}
