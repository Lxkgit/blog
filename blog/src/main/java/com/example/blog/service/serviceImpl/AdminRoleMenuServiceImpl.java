package com.example.blog.service.serviceImpl;

import com.example.blog.dao.AdminRoleMenuDAO;
import com.example.blog.entity.AdminRoleMenu;
import com.example.blog.service.AdminRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: lxk
 * @date: 2021/6/30 19:56
 * @description: 管理员角色菜单服务类
 * @modified By:
 */
@Service
public class AdminRoleMenuServiceImpl implements AdminRoleMenuService {

    @Resource
    private AdminRoleMenuDAO adminRoleMenuDAO;

    /**
     * 通过角色id查询角色权限菜单
     * @param rid
     * @return
     */
    @Override
    public List<AdminRoleMenu> selectMenuIdByRid(Integer rid) {
        return adminRoleMenuDAO.selectMenuIdByRid(rid);
    }

    /**
     * 通过角色id列表查询权限菜单
     * @param rids
     * @return
     */
    @Override
    public List<AdminRoleMenu> selectMenuIdByRids(List<Integer> rids) {
        return adminRoleMenuDAO.selectMenuIdByRids(rids);
    }

    /**
     * 更新角色权限菜单，先删除角色的权限菜单，然后在添加，并不是update，但效果是update
     * @param rid
     * @param menusIds
     */
    @Override
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        // 删除角色对应的全部菜单
        adminRoleMenuDAO.deleteRoleMenuByRid(rid);
        List<AdminRoleMenu> rms = new ArrayList<>();
        // 将前端页面设置的菜单功能授予此角色
        for (Integer mid : menusIds.get("menusIds")) {
            AdminRoleMenu rm = new AdminRoleMenu();
            rm.setMid(mid);
            rm.setRid(rid);
            rms.add(rm);
        }
        // 保存数据库
        adminRoleMenuDAO.insertRoleMenu(rms);
    }
}
