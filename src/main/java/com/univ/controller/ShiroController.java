package com.univ.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author univ date 2023/5/30
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @GetMapping("/demo")
    public String test() {
        System.out.println("demo ok");
        return "/demo ok";
    }

    /**
     * 这里使用了@RequiresRoles，显然此时必然要求是一个带登录态的请求。
     * curl -H 'Cookie:JSESSIONID=76622F3C1D32DB3D545F0FCE12724D88' 127.0.0.1:8080/api/shiro/user/add
     * @return
     */
    @GetMapping("/user/add")
    @RequiresRoles("admin")
    public String userAdd() {
        System.out.println("/user/add ok");
        return "/user/add ok";
    }

    @GetMapping("/user/update")
    @RequiresPermissions("user:update")
    public String userUpdate() {
        System.out.println("/user/update ok");
        return "/user/update ok";
    }

    /**
     * @param userName 为univ才能登录成功
     */
    @GetMapping("/login")
    public String login(@RequestParam("username") String userName) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userName, "univ");
        subject.login(token);
        System.out.println("/login ok, 后续要携带 JSESSIONID=" + subject.getSession().getId());
        return "/login ok, 后续要携带 JSESSIONID=" + subject.getSession().getId();
    }

    /**
     * 这里必须要求是已登录(@RequiresUser)，这样才能清空掉登录态，否则获取到的就是一个空白的Subject
     * curl -HXGET -H 'Cookie:JSESSIONID=76622F3C1D32DB3D545F0FCE12724D88' 127.0.0.1:8080/api/shiro/logout
     */
    @GetMapping("/logout")
    @RequiresUser
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("/logout ok");
        return "logout ok";
    }


}
