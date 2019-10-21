package org.zhou.forever.truegrave.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zhou.forever.truegrave.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HomeController
 * @Description home controller
 * @Author Mr.wang
 * @Date 2019/10/21 16:08
 **/
@Controller
public class HomeController {

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

    public String login(HttpServletRequest request, User user, Model model) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())){
            request.setAttribute("msg", "用户名或者密码不不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            subject.login(token);
            return "redirect:usersPage";
        } catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录, 请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或者密码不正确！");
            return "login";
        }
    }

    @RequestMapping("/usersPage")
    public String usersPage() {
        return "user/users";
    }

    @RequestMapping("/rolesPage")
    public String rolesPage() {
        return "role/roles";
    }

    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }
}
