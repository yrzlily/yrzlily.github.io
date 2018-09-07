package com.way.fact.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.*;


/**
 * Shiro配置类
 * @author yrz
 */
@Configuration
public class ShiroConfig  {

    private final static Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     *主体配置
     * @param securityManager 认证中心
     * @return 处理认证信息
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //免权限路劲
        filterChainDefinitionMap.put("/index/**","anon");
        filterChainDefinitionMap.put("/login/**","anon");
        filterChainDefinitionMap.put("/product","anon");

        //登出路劲
        filterChainDefinitionMap.put("/user/logout","logout");

        //前端必须认证的路径
        filterChainDefinitionMap.put("/order/**" , "authc");

        //后端必须认证的路劲
        filterChainDefinitionMap.put("/main/**","authc");
        filterChainDefinitionMap.put("/admin/**","authc");
        filterChainDefinitionMap.put("/role/**","authc");
        filterChainDefinitionMap.put("/type/**","authc");
        filterChainDefinitionMap.put("/cate/**","authc");
        filterChainDefinitionMap.put("/goods/**","authc");

        //未登录跳转界面
        filterFactoryBean.setLoginUrl("/login/index");

        //登录成功跳转界面
        filterFactoryBean.setSuccessUrl("/login/index");

        //未授权界面;
        filterFactoryBean.setUnauthorizedUrl("/login/unAuth");

        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    /**
     * 管理域
     * @return 管理角色认证
     */
    @Bean
    public MyShiroRealm myShiroRealm()
    {
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        realm.setCachingEnabled(false);
        return new MyShiroRealm();
    }

    /**
     * 设置session过期时间
     * @return 缓存时间
     */
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        return  defaultWebSessionManager;
    }

    /**
     *
     * @return 会话管理
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     *
     * @param securityManager 加入注解的使用
     * @return 使用注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     *
     * @return 凭证匹配器
     */
    @Bean(name="credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

}
