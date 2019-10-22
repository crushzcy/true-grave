package org.zhou.forever.truegrave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhou.forever.truegrave.domain.Role;
import org.zhou.forever.truegrave.domain.User;
import org.zhou.forever.truegrave.mapper.UserMapper;

import java.util.Set;

/**
 * @InterfaceName IUserService
 * @Description TODO
 * @Author Mr.wang
 * @Date 2019/10/21 11:01
 **/
public interface IUserService {

    User findUserByName(String userName);

    Set<String> findRolesById(Integer userId);

    int insertUser(User user);

}
