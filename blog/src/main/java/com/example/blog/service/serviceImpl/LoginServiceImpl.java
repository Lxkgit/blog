package com.example.blog.service.serviceImpl;

import com.example.blog.dao.LoginDAO;
import com.example.blog.email.EmailService;
import com.example.blog.entity.BlogUser;
import com.example.blog.service.LoginService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: lxk
 * @date: 2021/6/28 10:49
 * @description: 用户登录服务
 * @modified By:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginDAO loginDAO;

    @Autowired
    private EmailService emailService;

    /**
     * 通过Email查询用户，Email是用户的账号
     * @param email
     * @return
     */
    @Override
    public BlogUser selectUserByEmail(String email) {
        return loginDAO.selectUserByEmail(email);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public int insertUser(BlogUser user) {


        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        //**************************************本地测试的路径 会失效。**************************************************************
        //String context = "<a href=\"/user/checkCode?code="+code+"\">激活请点击:"+code+"</a>";
        //*************************************************************************************************************************

        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        //注意:此处的链接地址,是项目内部地址,如果我们没有正式的服务器地址,暂时无法从qq邮箱中跳转到我们自己项目的激活页面

        //方式1 注意看上述说明(如不服务器测试 把上面本地代码链接注释)
//        String context = "<a href=\"服务器端地址/user/checkCode?code=" + code + "\">激活用户认证请点击=> " + code + "</a>";
        //方式2 注意看上述说明(如不服务器测试 把上面本地代码取消注释)
        //String context = "<a href='服务端地址/user/checkCode?code='+code+'>激活用户认证请点击=> '+code+'</a>";

        // 获取随机生成的code
        String code = user.getCode();
        // 邮件发送主题
        String subject = "用户账号激活";
        // 邮件内容
        String content = "激活请点击：";
        String url = "http://47.98.143.11:8095/blog-0.0.1-SNAPSHOT/user/checkCode?code="+code;
        System.out.println("用户Email：" + user.getEmail());
        System.out.println("用户激活链接: " + url);
        content += url;
        // 使用邮件服务类发送邮件
        try {
            emailService.sendHtmlMail(user.getEmail(),subject,content);
        } catch (Exception e) {
            return -1;
        }

        // 明文密码进行md5 + salt + hash散列
        // 默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        // 密码MD5加密次数
        int times = 1024;
        // 密码加密
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, times);
        user.setPassword(md5Hash.toHex());
        // 写入数据库
        return loginDAO.insertUser(user);
    }

    /**
     * 检查用户code
     * 业务流程：用户注册完成之后，账号是未激活的，向用户邮箱之中发送url链接，用户点击之后核对url中的code是否和发送是相同
     * 本方法只负责通过code查询用户，不负责验证
     * @param code
     * @return
     */
    @Override
    public BlogUser checkCode(String code) {
        return loginDAO.selectUserByCode(code);
    }

    /**
     * 修改用户状态
     * @param user
     */
    @Override
    public void updateUserStatus(BlogUser user) {
        // 调用次方法即修改用户状态，状态取反即可 0：账号不可用 1：账号可用
        if (user.getStatus() == 0) {
            user.setStatus(1);
        } else {
            user.setStatus(0);
        }
        user.setCode("");
        loginDAO.updateUserStatus(user);
    }
}
