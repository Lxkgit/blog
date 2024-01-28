package com.example.blog.service.serviceImpl;

import com.example.blog.dao.AdminRolePermissionDAO;
import com.example.blog.entity.AdminPermission;
import com.example.blog.entity.AdminRolePermission;
import com.example.blog.service.AdminRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lxk
 * @date: 2021/7/4 11:45
 * @description: 管理员权限角色类
 * @modified By:
 */
@Service
public class AdminRolePermissionServiceImpl implements AdminRolePermissionService {

    @Resource
    private AdminRolePermissionDAO adminRolePermissionDAO;

    /**
     * 通过角色id列表查询权限id
     * @param rids
     * @return
     */
    @Override
    public List<AdminRolePermission> selectPermissionIdByRids(List<Integer> rids) {
        return adminRolePermissionDAO.selectPermissionIdByRids(rids);
    }

    /**
     * 通过角色id查询权限id
     * @param rid
     * @return
     */
    @Override
    public List<AdminRolePermission> selectPermissionIdByRid(Integer rid) {
        return adminRolePermissionDAO.selectPermissionIdByRid(rid);
    }

    /**
     * 修改角色对应的权限
     * @param rid 角色id
     * @param perms 权限列表
     */
    @Override
    public void updateRolePermission(int rid, List<AdminPermission> perms) {
        // 删除角色对应的全部权限
        adminRolePermissionDAO.deletePermissionByRid(rid);
        List<AdminRolePermission> adminRolePermissions = new ArrayList<>();
        // 保存角色以及权限
        perms.forEach(p -> {
            AdminRolePermission adminRolePermission = new AdminRolePermission();
            adminRolePermission.setRid(rid);
            adminRolePermission.setPid(p.getId());
            adminRolePermissions.add(adminRolePermission);
        });
        // 将修改存入数据库
        adminRolePermissionDAO.insertRolePermission(adminRolePermissions);
    }
}
