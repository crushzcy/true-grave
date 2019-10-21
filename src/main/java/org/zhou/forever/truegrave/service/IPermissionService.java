package org.zhou.forever.truegrave.service;

import java.util.Set;

/**
 * @InterfaceName IPermission
 * @Description TODO
 * @Author Mr.wang
 * @Date 2019/10/21 14:09
 **/
public interface IPermissionService {

     Set<String> findPermissionsById(Integer roleId);
}
