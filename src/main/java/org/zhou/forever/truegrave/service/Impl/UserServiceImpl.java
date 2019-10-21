package org.zhou.forever.truegrave.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhou.forever.truegrave.domain.Role;
import org.zhou.forever.truegrave.domain.User;
import org.zhou.forever.truegrave.mapper.UserMapper;
import org.zhou.forever.truegrave.service.IUserService;

import java.util.Set;

/**
 * @ClassName UserServiceImpl
 * @Description userServiceImpl
 * @Author Mr.wang
 * @Date 2019/10/21 11:10
 **/
@Component
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByName(String userName) {
        return userMapper.selectByName(userName);
    }

    @Override
    public Set<String> findRolesById(Integer userId) {
        return userMapper.selectRolesById(userId);
    }
}
