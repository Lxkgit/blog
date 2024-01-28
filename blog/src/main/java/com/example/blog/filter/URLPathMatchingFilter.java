package com.example.blog.filter;

import com.example.blog.service.AdminPermissionService;
import com.example.blog.util.SpringContextUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author: lxk
 * @date: 2021/7/4 18:24
 * @description: 拦截器
 * @modified By:
 */
@Log4j2
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    AdminPermissionService adminPermissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        if (null == adminPermissionService) {
            adminPermissionService = SpringContextUtils.getContext().getBean(AdminPermissionService.class);
        }

        String requestAPI = getPathWithinApplication(request);

        Subject subject = SecurityUtils.getSubject();


        if (!subject.isAuthenticated()) {
            log.info(subject.getPrincipal() + "未登录用户尝试访问需要登录的接口: " + requestAPI);
            return false;
        }

        // 判断访问接口是否需要过滤（数据库中是否有对应信息）
        boolean needFilter = adminPermissionService.needFilter(requestAPI);
        System.out.println(requestAPI);
        System.out.println("用户 " + subject.getPrincipal() + " 请求api: " + requestAPI);
        if (!needFilter) {
            System.out.println("接口： " + requestAPI + " 不需要过滤");
            return true;
        } else {
            // 判断当前用户是否有相应权限
            boolean hasPermission = false;
            String email = subject.getPrincipal().toString();
            Set<String> permissionAPIs = adminPermissionService.selectPermissionURLsByEmail(email);
            for (String api : permissionAPIs) {
                // 匹配前缀
                if (requestAPI.startsWith(api)) {
                    hasPermission = true;
                    break;
                }
            }
            System.out.println(hasPermission);
            if (hasPermission) {
                log.info("用户：" + email + "访问了：" + requestAPI + "接口");
                return true;
            } else {
                log.warn( "用户：" + email + "访问了没有权限的接口：" + requestAPI);
                return false;
            }
        }
    }
}
