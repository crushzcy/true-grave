package org.zhou.forever.truegrave.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhou.forever.truegrave.domain.Role;
import org.zhou.forever.truegrave.domain.User;
import org.zhou.forever.truegrave.service.IPermissionService;
import org.zhou.forever.truegrave.service.IRoleService;
import org.zhou.forever.truegrave.service.IUserService;

import java.security.PermissionCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Program true-grave
 * @Class CustomRealm
 * @Description 自定义Realm， 实现授权和认证
 * @Author Mr.Wang
 * @Date 2019-10-21 00:36
 * @Version 1.0
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    @Autowired
    IPermissionService permissionService;

    @Autowired
    IRoleService roleService;

    /**
     * 用户授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();

        if(user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色与权限字符串集合
            Collection<String> roleCollection = new HashSet<>();
            Collection<String> permissionCollection = new HashSet<>();

            Integer userId = userService.findUserByName(user.getUserName()).getUserId();

            //读取并赋值用户角色与权限
            roleCollection = userService.findRolesById(userId);
            info.addRoles(roleCollection);

            for(String role: roleCollection) {
                Integer roleId = roleService.findRoleByName(role).getRoleId();
                permissionCollection.addAll(permissionService.findPermissionsById(roleId));
            }
            info.addStringPermissions(permissionCollection);
            return info;
        }
        return null;
    }

    /**
     * 用户鉴权
     * 
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByName(token.getUsername());

        if(user == null) {
            throw new UnknownAccountException();
        }

        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());

        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
    }
}
