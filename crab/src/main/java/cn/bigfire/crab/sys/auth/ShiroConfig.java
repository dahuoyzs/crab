package cn.bigfire.crab.sys.auth;

import cn.bigfire.crab.common.util.CacheMap;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/12/10  14:31
 * @ Desc   ：Shiro 配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * ShiroConfig 的SpringBoot整合需要配置 三个Bean
     * ShiroFilterFactoryBean 过滤器
     * DefaultWebSecurityManager  Web权限管理器
     * Realm 领域授权规则
     * */
    /**
     * Shir内置过滤器，可以实现权限相关的拦截器
     *      常用的过滤器：
     *          anon:   无需认证(登录)即可访问
     *          authc:  必须认证才能访问
     *          user:   必须使用rememberMe的功能才能直接访问
     *          perms:  该资源必须得到资源权限才能访问
     *          role:   该资源必须得到角色权限才能访问
     *
     *         自定义:  同时支持，自定义过滤器，用于处理各种权限。
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);//设置安全管理器
        Map<String, String> filterMap = new LinkedHashMap<>();
        /** 三方组件取消拦截
        filterMap.put("/crab/webjars/**", "anon");
        filterMap.put("/crab/druid/**", "anon");
        filterMap.put("/crab/swagger/**", "anon");
        filterMap.put("/crab/v2/api-docs", "anon");
        filterMap.put("/crab/swagger-ui.html", "anon");
        filterMap.put("/crab/swagger-resources/**", "anon");
         */
        //取消拦截
        filterMap.put("/crab/sys/user/login", "anon");
        filterMap.put("/crab/sys/login", "anon");


        //仅拦截sys模块
        filterMap.put("/crab/sys/**", "authFilter");
        Map map = new CacheMap().put("authFilter",new AuthFilter());
        shiroFilterFactoryBean.setFilters(map);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(AuthRealm authRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }
    @Bean
    public AuthRealm authRealm(){
        return new AuthRealm();
    }


    /**
     * 下面的代码是添加注解支持
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
