package com.sjth.erpcloud.module.auth.controller;

import javax.servlet.http.HttpSession;

import com.sjth.erpcloud.module.auth.entity.AuthUser;
import com.sjth.erpcloud.module.auth.service.AuthUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
    //用户登录
    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session) {
        //把前端输入的username和password封装为token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("user", subject.getPrincipal());
            return "index";
        } catch (Exception e) {
            return "login";
        }
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    //访问login时跳到login.jsp
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //admin角色才能访问
    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin success";
    }

    //有delete权限才能访问
    @RequestMapping("/edit")
    @ResponseBody
    public String edit() {
        return "edit success";
    }

    @RequestMapping("/test")
    @ResponseBody
    @RequiresRoles("guest")
    public String test(){
        return "test success";
    }
    @Autowired
    private AuthUserService userService;
    @RequestMapping("/find")
    @ResponseBody
    public AuthUser find(@RequestParam("username") String username
           ){
        return userService.findByUsername(username);
    }
}