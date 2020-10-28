package com.molin.project200908.shiro;

import com.molin.project200908.pojo.quanxian.User;
import com.molin.project200908.service.quanxian.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Set<String> permissionSet =userSerivce.findPermissionsByUserId(user.getId());
        Set<String> strings = userSerivce.findrolessByUserId(user.getId());
        System.out.println(strings);
        if (permissionSet.size() > 0) {
            authorizationInfo.setStringPermissions(permissionSet);
        }
        if (strings.size() > 0) {
            authorizationInfo.setRoles(strings);
        }
        return authorizationInfo;
    }

    @Autowired
    private IUserService userSerivce;

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
//        System.out.println("执行认证逻辑");

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;

        User user = userSerivce.findByName(token.getUsername());

        if (user == null) {
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }

        //当前realm对象的name
        String realmName = getName();
        System.out.println("==================" + realmName);
        //2.判断密码
        //盐值
        ByteSource credenttialsSalt = ByteSource.Util.bytes(user.getUsername());
        //封装用户信息，构建AuthenticationInfo对象并返回
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
                credenttialsSalt, realmName);
        return authcInfo;
    }
}
