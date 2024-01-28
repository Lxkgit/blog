package com.example.blog.controller;

import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lxk
 * @date: 2021/6/30 20:21
 * @description: 后台菜单控制类
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/admin")
public class MenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 查询当前用户的菜单
     * @return
     */
    @GetMapping("/menu")
    public Result selectMenusByCurrentUser() {
        return ResultFactory.buildSuccessResult(adminMenuService.selectMenusByCurrentUser());
    }

    /**
     * 查询全部菜单列表，角色id为1是管理员，管理员有全部菜单权限（不建议修改管理员菜单权限）
     * @return
     */
    @GetMapping("/role/menu")
    public Result selectAllMenus() {
        return ResultFactory.buildSuccessResult(adminMenuService.selectMenuByRoleId(1));
    }
}
