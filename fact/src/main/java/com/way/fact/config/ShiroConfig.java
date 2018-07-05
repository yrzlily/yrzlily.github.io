package com.way.fact.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;


/**
 * Shiro配置类
 * @author Administrator
 */
@Configuration
public class ShiroConfig  {

    private final static Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     *主体配置
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //免权限路劲
        filterChainDefinitionMap.put("/nav","anon");
        filterChainDefinitionMap.put("/login","anon");

        //登出路劲
        filterChainDefinitionMap.put("/user/logout","logout");

        //必须认证的路劲
        filterChainDefinitionMap.put("/**","authc");

        //未登录跳转界面
        filterFactoryBean.setLoginUrl("/login/check");

        //登录成功跳转界面
        filterFactoryBean.setSuccessUrl("/user/index");

        //未授权界面;
        filterFactoryBean.setUnauthorizedUrl("/login/unAuth");

        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }

    /**
     * 管理域
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        return new MyShiroRealm();
    }

    /**
     * 设置session过期时间
     * @return
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
     *会话管理
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 加入注解的使用
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
