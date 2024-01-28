package com.example.blog.controller;

import com.example.blog.entity.BlogUser;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lxk
 * @date: 2021/7/5 20:05
 * @description: 用户控制类
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private AdminUserService userService;

    /**
     * 更新用户状态
     * @param requestUser
     * @return
     */
    @PutMapping("/status")
    public Result updateUserStatus(@RequestBody BlogUser requestUser) {
        userService.updateUserStatusById(requestUser);
        return ResultFactory.buildSuccessResult("用户状态更新成功");
    }

    /**
     * 修改密码 暂时未使用
     * @param requestUser
     * @return
     */
    @PutMapping("/password")
    public Result updateUserPassword(@RequestBody BlogUser requestUser) {
        userService.updateUserPassword(requestUser);
        return ResultFactory.buildSuccessResult("重置密码成功");
    }

    /**
     * 修改用户信息
     * @param requestUser
     * @return
     */
    @PostMapping("/message")
    public Result updateUser(@RequestBody BlogUser requestUser) {
        System.out.println(requestUser);
        userService.updateUserMsg(requestUser);
        return ResultFactory.buildSuccessResult("修改用户信息成功");
    }

    /**
     * 查询系统用户列表
     * @return
     */
    @GetMapping("/list")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.selectUserList());
    }

}
