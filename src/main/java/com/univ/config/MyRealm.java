package com.univ.config;

import java.util.HashSet;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// 1. 继承自AuthorizingRealm
public class MyRealm extends AuthorizingRealm {

    // 每次授权时会调用(当然，实际一般会启用缓存)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 取用户的标识，可能是用户名、用户id等等
        String userId = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 2. 模拟获取用户的所有角色
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        info.setRoles(roles);
        // 3. 模拟获取用户的所有权限
        Set<String> permissions = new HashSet<>();
        permissions.add("user:add");
        info.setStringPermissions(permissions);
        return info;
    }

    // 每次登录时调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (!(token instanceof UsernamePasswordToken)) {
            System.out.println("AuthenticationToken类型不对");
            return null;
        }
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        String username = token1.getUsername();
        char[] password = token1.getPassword();
        // 要求用户名与密码均为univ
        if (username.equals("univ") && new String(password).equals("univ")) {
            return new SimpleAuthenticationInfo("univ", "univ", getName());
        }
        return null;
    }
}
