package org.zhou.forever.truegrave.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zhou.forever.truegrave.realm.CustomRealm;

/**
 * @Program true-grave
 * @Class ShiroConfig
 * @Description shiro config file
 * @Author Mr.Wang
 * @Date 2019-10-20 23:34
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        credentialsMatcher.setHashAlgorithmName("MD5");
        // 加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 主要配置一些相应的url的规则和访问权限
     * 
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
        @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会寻找Web工程共目录下的"/login.jsp"页面 或 "/login"映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");

        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 游客，开放权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        // 用户，需要角色权限 "user"
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        // 管理员，需要角色权限 "admin"
        filterChainDefinitionMap.put("/admin/**", "role[admin]");
        // 开放登录接口
        filterChainDefinitionMap.put("/login", "anon");
        // 其余接口一律拦截
        // 这行必须要放在所有权限的最后，不然会导致所有的url都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(
        @Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(customRealm(matcher));
        return securityManager;

    }

    @Bean("customRealm")
    public CustomRealm customRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(matcher);
        return customRealm;
    }

}
