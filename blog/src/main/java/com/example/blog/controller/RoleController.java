package com.example.blog.controller;

import com.example.blog.entity.AdminRole;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.AdminPermissionService;
import com.example.blog.service.AdminRoleMenuService;
import com.example.blog.service.AdminRolePermissionService;
import com.example.blog.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: lxk
 * @date: 2021/7/5 20:05
 * @description: 角色控制类
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;

    /**
     * 添加新的角色
     *
     * @param requestRole
     * @return
     */
    @PostMapping("/add")
    public Result insertRole(@RequestBody AdminRole requestRole) {
        adminRoleService.insertRole(requestRole);
        return ResultFactory.buildSuccessResult("添加用户成功");
    }

    /**
     * 更新用户状态
     *
     * @param requestRole
     * @return
     */
    @PutMapping("/status")
    public Result updateRoleStatus(@RequestBody AdminRole requestRole) {
        System.out.println("requestRole  " +  requestRole);
        adminRoleService.updateRoleStatus(requestRole);
        String message = "用户" + requestRole.getNameZh() + "状态更新成功";
        return ResultFactory.buildSuccessResult(message);
    }

    /**
     * 修改用户的权限
     *
     * @param requestRole
     * @return
     */
    @PutMapping("/message")
    public Result updateRolePermission(@RequestBody AdminRole requestRole) {
        adminRoleService.updateRole(requestRole);
        adminRolePermissionService.updateRolePermission(requestRole.getId(), requestRole.getPerms());
        String message = "修改角色信息成功";
        return ResultFactory.buildSuccessResult(message);
    }

    /**
     * 更新角色菜单
     *
     * @param rid
     * @param menusIds
     * @return
     */
    @PutMapping("/update/menu")
    public Result updateRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds) {
        adminRoleMenuService.updateRoleMenu(rid, menusIds);
        return ResultFactory.buildSuccessResult("更新成功");
    }

    /**
     * 查询全部权限菜单
     *
     * @return
     */
    @GetMapping("/list")
    public Result selectPermsAndMenus() {
        return ResultFactory.buildSuccessResult(adminRoleService.selectPermsAndMenus());
    }

    /**
     * 查询全部权限列表
     *
     * @return
     */
    @GetMapping("/perm")
    public Result selectPermissionList() {
        return ResultFactory.buildSuccessResult(adminPermissionService.selectPermissionList());
    }
}
