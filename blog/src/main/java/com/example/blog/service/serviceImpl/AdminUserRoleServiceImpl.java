package com.example.blog.service.serviceImpl;

import com.example.blog.dao.AdminUserRoleDAO;
import com.example.blog.entity.AdminUserRole;
import com.example.blog.service.AdminUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: lxk
 * @date: 2021/6/30 19:57
 * @description: 用户角色绑定类
 * @modified By:
 */
@Service
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

    @Resource
    private AdminUserRoleDAO adminUserRoleDAO;

    /**
     * 根据用户id查询角色id
     * @param uid
     * @return
     */
    @Override
    public List<AdminUserRole> selectRidByUid(int uid) {
        return adminUserRoleDAO.selectRidByUid(uid);
    }

}
