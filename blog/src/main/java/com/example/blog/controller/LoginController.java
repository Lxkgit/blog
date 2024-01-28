package com.example.blog.controller;

import com.example.blog.entity.BlogUser;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.LoginService;
import com.example.blog.util.UUIDUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


/**
 * @author: lxk
 * @date: 2021/6/28 10:48
 * @description: 登录控制类
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * @param requestUser
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody BlogUser requestUser) {
        // 转译 防止恶意注册
        String email = requestUser.getEmail();
        email = HtmlUtils.htmlEscape(email);
        // 注册shiro的subject
        Subject subject = SecurityUtils.getSubject();
        // 设置登录超时时间
        subject.getSession().setTimeout(1800000);
        // 用来存储用户和密码
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(email, requestUser.getPassword());
        // 记住密码
        usernamePasswordToken.setRememberMe(true);

        try {
            // 验证登录
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("密码错误");
        } catch (UnknownAccountException e) {
            return ResultFactory.buildFailResult("账号不存在");
        } catch (LockedAccountException e) {
            return ResultFactory.buildFailResult("账号未激活，或已被锁定");
        }
        BlogUser user = loginService.selectUserByEmail(email);
        System.out.println(user + "登陆成功" + subject.isAuthenticated());
        return ResultFactory.buildSuccessResult(user);
    }

    /**
     * 登出接口
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        System.out.println("logout");
        Subject subject = SecurityUtils.getSubject();
        // 登出
        subject.logout();
        return ResultFactory.buildSuccessResult("退出成功");
    }

    /**
     * 注册账号
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody BlogUser user){
        // 新注册的账号未激活
        user.setStatus(0);
        // 随机产生code，用于用户在邮箱中确认
        String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
        // 保存code到用户类
        user.setCode(code);
        try {
            // 用户数据存数据库
            loginService.insertUser(user);
            return ResultFactory.buildSuccessResult("注册成功");
        } catch (Exception e) {
            System.out.println("用户：" + user.getEmail() + "注册失败");
            return ResultFactory.buildFailResult("注册失败");
        }
    }

    /**
     * 核对用户激活码，用户点击邮箱中url链接访问
     * @param code
     * @return
     */
    @GetMapping("/checkCode")
    public String checkCode(String code) {
        BlogUser user = loginService.checkCode(code);
        if (user != null){
            loginService.updateUserStatus(user);
        }
        return "身份认证成功";
    }

    @GetMapping("/authentication")
    public String authentication() {
        return "身份认证成功";
    }
}
