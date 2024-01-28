package com.example.blog.realm;

import com.example.blog.entity.BlogUser;
import com.example.blog.service.AdminPermissionService;
import com.example.blog.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * @author: lxk
 * @date: 2021/6/30 7:22
 * @description: shiro中自定义Realm
 * @modified By:
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    @Autowired
    private AdminPermissionService adminPermissionService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户的所有权限
        String email = principalCollection.getPrimaryPrincipal().toString();
        Set<String> permissions = adminPermissionService.selectPermissionURLsByEmail(email);
        System.out.println(permissions);
        System.out.println("授权" + permissions.toString());

        // 将权限放入授权信息中
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        s.setStringPermissions(permissions);
        return s;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String email = token.getPrincipal().toString();
        BlogUser user = loginService.selectUserByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        if (user.getStatus() == 0){
            throw new LockedAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(email, passwordInDB, ByteSource.Util.bytes(salt), getName());
        System.out.println("用户： " + email + "通过认证");
        return authenticationInfo;
    }
}
