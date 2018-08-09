package com.way.fact.shiro;

import com.way.fact.bean.Permission;
import com.way.fact.bean.Role;
import com.way.fact.bean.User;
import com.way.fact.dao.UserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 * @author yrz
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * 权限验证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();

        for (Role role : user.getRoles()) {
            authorizationInfo.addRole(role.getRoles());
            for (Permission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取令牌
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        //用户验证
        String username = (String) token.getPrincipal();
        log.info("username = {}",username);
        User user = userDao.findByUsername(username);
        if(user==null){
            return null;
        }

        //密码验证
        String password =new  SimpleHash("MD5" , token.getCredentials() , null , 1024).toString();
        log.info("password = {}" , password);
        if(!password.equals(user.getPassword())){
            return null;
        }

        return new SimpleAuthenticationInfo(user, token.getCredentials() , ByteSource.Util.bytes(user.getPassword()),this.getName());
    }
}
