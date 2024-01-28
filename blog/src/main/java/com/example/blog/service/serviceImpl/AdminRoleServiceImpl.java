package com.example.blog.service.serviceImpl;

import com.example.blog.dao.AdminRoleDAO;
import com.example.blog.entity.AdminMenu;
import com.example.blog.entity.AdminPermission;
import com.example.blog.entity.AdminRole;
import com.example.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: lxk
 * @date: 2021/6/30 19:57
 * @description: 管理员角色服务类
 * @modified By:
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    @Resource
    private AdminRoleDAO adminRoleDAO;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 查询全部角色的菜单权限
     * @return
     */
    @Override
    public List<AdminRole> selectPermsAndMenus() {
        // 查询全部角色
        List<AdminRole> roles = adminRoleDAO.selectRoleList();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        // for 循环中使用role对对象内部元素修改会同步修改到roles中，个人认为可以看作指针
        for (AdminRole role : roles) {
            perms = adminPermissionService.selectPermissionByRid(role.getId());
            menus = adminMenuService.selectMenuByRoleId(role.getId());;
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    /**
     * 修改角色状态，是否可用
     * @param adminRole
     * @return
     */
    @Override
    public void updateRoleStatus(AdminRole adminRole) {
        adminRoleDAO.updateRole(adminRole);
    }

    /**
     * 修改角色的名字、中文名字
     * @param adminRole
     */
    @Override
    public void updateRole(AdminRole adminRole) {
        adminRoleDAO.updateRole(adminRole);
    }

    /**
     * 新增角色
     * @param adminRole
     */
    @Override
    public void insertRole(AdminRole adminRole) {
        // 新增的角色状态设置为可用
        adminRole.setStatus(1);
        adminRoleDAO.insertRole(adminRole);
    }

    /**
     * 删除角色，此方法太过危险暂不使用
     * @param id
     */
    @Override
    public void deleteRoleById(Integer id) {
        adminRoleDAO.deleteRoleById(id);
    }

}
