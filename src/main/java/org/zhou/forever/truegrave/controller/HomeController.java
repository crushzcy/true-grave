package org.zhou.forever.truegrave.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zhou.forever.truegrave.domain.User;
import org.zhou.forever.truegrave.service.IUserService;
import org.zhou.forever.truegrave.util.PasswordHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName HomeController
 * @Description home controller
 * @Author Mr.wang
 * @Date 2019/10/21 16:08
 **/
@Controller
public class HomeController {

    @Autowired
    IUserService userService;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            request.setAttribute("msg", "用户名或者密码不不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "redirect:grave";
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

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            request.setAttribute("msg", "用户名或者密码不不能为空！");
            return "login";
        }

        User u = userService.findUserByName(username);
        if(u != null) {
            return "error";
        }
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        PasswordHelper.encryptPassword(user);
        user.setStatus(true);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userService.insertUser(user);
        return "redirect:login";
    }

    @RequestMapping("/usersPage")
    public String usersPage() {
        return "user/users";
    }

    @RequestMapping(value={"/grave",""})
    public String grave() {
        return "grave";
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
