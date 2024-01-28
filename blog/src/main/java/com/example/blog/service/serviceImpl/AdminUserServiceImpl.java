package com.example.blog.service.serviceImpl;

import com.example.blog.dao.AdminUserDAO;
import com.example.blog.entity.BlogUser;
import com.example.blog.service.AdminUserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: lxk
 * @date: 2021/7/5 20:14
 * @description: 管理用户服务类
 * @modified By:
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserDAO adminUserDAO;

    /**
     * 返回用户列表
     * @return
     */
    @Override
    public List<BlogUser> selectUserList() {
        return adminUserDAO.selectUserList();
    }

    /**
     * 通过id更新用户状态
     * @param user
     */
    @Override
    public void updateUserStatusById(BlogUser user) {
        adminUserDAO.updateUserStatusById(user);
    }

    /**
     * 用户修改密码，通过用户账号即邮箱查找用户，然后修改密码
     * @param user
     */
    @Override
    public void updateUserPassword(BlogUser user) {
        // 通过邮箱查找用户
        BlogUser userInDB = adminUserDAO.selectUserByEmail(user.getEmail());
        // 产生随机盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // MD5加密次数
        int times = 1024;
        // 将盐保存进用户类，下一步存入数据库
        userInDB.setSalt(salt);
        // 使用用户密码、salt、加密次数，然后调用MD5加密方法进行密码加密
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,times);
        // 获取加密后的密码
        userInDB.setPassword(md5Hash.toHex());
        // 更新用户信息
        adminUserDAO.updateUserPassword(userInDB);
    }

    /**
     * 修改用户名字
     * @param user
     */
    @Override
    public void updateUserMsg(BlogUser user) {
        adminUserDAO.updateUserMsg(user);
    }

}
