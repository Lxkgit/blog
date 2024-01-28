package com.example.blog.service.serviceImpl;

import com.example.blog.dao.AdminPermissionDAO;
import com.example.blog.entity.*;
import com.example.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: lxk
 * @date: 2021/7/4 11:46
 * @description:
 * @modified By:
 */
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Resource
    private AdminPermissionDAO adminPermissionDAO;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    @Autowired
    private LoginService loginService;

    /**
     * 查询全部权限列表
     * @return
     */
    @Override
    public List<AdminPermission> selectPermissionList() {
        // 返回全部权限数据
        return adminPermissionDAO.selectPermissionList();
    }

    /**
     * email是在系统中的用户
     * 此方法是查询用户的所有权限
     * @param email
     * @return
     */
    @Override
    public Set<String> selectPermissionURLsByEmail(String email) {
        // 查询当前用户的id
        Integer id = loginService.selectUserByEmail(email).getId();
        // 根据当前用户的id查询该用户的全部角色
        List<Integer> rids = adminUserRoleService.selectRidByUid(id)
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        // 查询角色对应的权限id
        List<Integer> pids = adminRolePermissionService.selectPermissionIdByRids(rids)
                .stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        // 根据权限id在权限表中取得权限url
        List<AdminPermission> permissions = adminPermissionDAO.selectPermissionByIds(pids);
        // 整理权限，只需要权限的url
        Set<String> URLs = permissions.stream().map(AdminPermission::getUrl)
                .collect(Collectors.toSet());
        return URLs;
    }

    /**
     * 根据角色id查询权限
     * @param rid
     * @return
     */
    @Override
    public List<AdminPermission> selectPermissionByRid(int rid) {
        // 根据rid查询pid，并整理为list
        List<Integer> permIds = adminRolePermissionService.selectPermissionIdByRid(rid)
                .stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        // 权限角色对应的权限id为空就返回，防止查询传入null报错
        if (permIds.isEmpty())
            return null;
        // 根据权限id查询权限
        List<AdminPermission> perms = adminPermissionDAO.selectPermissionByIds(permIds);
        return perms;
    }

    @Override
    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = adminPermissionDAO.selectPermissionList();
        for (AdminPermission p: ps) {
            // match prefix
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

}
