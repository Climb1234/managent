package com.example.demo.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author Climb
 * @Date 2020/6/12
 **/
@Configuration
public class ShiroConfig  {
    /**
     * 创建shiroFilterFactoryBean
     * * anon: 无需认证（登录）就可访问
     * * authc: 必须认证才能访问
     * * user: 使用rememberMe功能可直接访问
     * * perms: 该资源必须得到资源权限才能访问
     * * role: 该资源必须得到相关角色权限才能访问
     * @return
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/auth/notlogin");

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>(3);
        filters.put("oauth2", new Oauth2Filter());
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>(15);
        // 放行
        filterMap.put("/user/logout", "anon");
        filterMap.put("/auth/login", "anon");

        // 放行swagger
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterMap.put("/swagger-resources/configuration/ui", "anon");
        filterMap.put("/swagger-resources/configuration/security", "anon");

        filterMap.put("/excel/upload", "anon");

        // 登录才能访问
//        filterMap.put("/**", "authc");
        filterMap.put("/**", "oauth2");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefalutWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }


}
