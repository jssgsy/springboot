package com.univ.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author univ date 2023/5/30
 */
@Component
public class ShiroConfig {

    /**
     * 默认情况下已经有提供一个IniRealm实例，因此也可以直接在类路径下放置一个shiro.ini文件
     *
     */
    @Bean
    public Realm realm() {
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/shiro/demo", DefaultFilter.anon.name());
        definition.addPathDefinition("/shiro/login", DefaultFilter.anon.name());
        // 过滤器执行的顺序从上往下，兜底的/**放最后
        definition.addPathDefinition("/**", DefaultFilter.anon.name());
        return definition;
    }

}
