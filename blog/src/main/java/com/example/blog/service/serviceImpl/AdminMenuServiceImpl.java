package com.example.blog.service.serviceImpl;

import com.example.blog.dao.AdminMenuDAO;
import com.example.blog.entity.AdminMenu;
import com.example.blog.entity.AdminRoleMenu;
import com.example.blog.entity.AdminUserRole;
import com.example.blog.entity.BlogUser;
import com.example.blog.service.AdminMenuService;
import com.example.blog.service.AdminRoleMenuService;
import com.example.blog.service.AdminUserRoleService;
import com.example.blog.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lxk
 * @date: 2021/6/30 19:56
 * @description: 管理员菜单服务类
 * @modified By:
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    @Resource
    private AdminMenuDAO adminMenuDAO;
    @Autowired
    private LoginService loginService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;

    /**
     * 获取当前用户对应的全部菜单
     * @return
     */
    @Override
    public List<AdminMenu> selectMenusByCurrentUser() {
        // 使用shiro,如果正常登陆（执行subject.login(token)成功）就能在全局通过SecurityUtils.getSubject().getPrincipal()获取用户信息
        String email = SecurityUtils.getSubject().getPrincipal().toString();
        // 从数据库中获取当前用户
        BlogUser user = loginService.selectUserByEmail(email);
        // 获取当前用户的角色id,角色id即为Rid，一个用户可能有多个角色，所以用List，下同
        List<Integer> rids = adminUserRoleService.selectRidByUid(user.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        // 获取当前角色所对应的菜单id
        List<Integer> menuIds = adminRoleMenuService.selectMenuIdByRids(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        // 根据菜单id列表查询所有菜单
        List<AdminMenu> menus = adminMenuDAO.selectMenuByIds(menuIds).stream()
                .distinct().collect(Collectors.toList());
        // 整理查询出来的菜单，因为菜单有不同的级别，即一级菜单、二级菜单等
        handleMenus(menus);
        return menus;
    }

    /**
     * 根据角色id查询菜单
     * @param rid
     * @return
     */
    @Override
    public List<AdminMenu> selectMenuByRoleId(int rid) {
        // 根据角色id查询菜单，并提取出菜单id整理在list中
        List<Integer> menuIds = adminRoleMenuService.selectMenuIdByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        // 当前角色没有对应菜单直接返回
        if (menuIds.isEmpty())
            return null;
        // 根据菜单id查询菜单
        List<AdminMenu> menus = adminMenuDAO.selectMenuByIds(menuIds);
        // 整理菜单
        handleMenus(menus);
        return menus;
    }

    /**
     * 查询当前id下的子菜单
     * @param parentId
     * @return
     */
    @Override
    public List<AdminMenu> selectMenuByParentId(int parentId) {
        // 根据当前菜单id查询其它菜单的"parent_id"列是否为当前菜单
        return adminMenuDAO.selectMenuByParentId(parentId);
    }

    /**
     * 整理菜单
     * @param menus
     */
    @Override
    public void handleMenus(List<AdminMenu> menus) {
        // 移除父id不为0的全部菜单，因为父id不为0意味着是子菜单
        menus.removeIf(m -> m.getParentId() != 0);
        // 循环每一个一级菜单，查找其子菜单
        menus.forEach(m -> {
            // 根据一级菜单的id查询其子菜单
            List<AdminMenu> children = selectMenuByParentId(m.getId());
            // 设置子菜单
            m.setChildren(children);
        });
    }

}
