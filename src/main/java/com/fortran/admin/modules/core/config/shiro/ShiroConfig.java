package com.fortran.admin.modules.core.config.shiro;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 集成shiro
 *
 * @author lin
 */
@Configuration
public class ShiroConfig {


    /**
     * <p>缓存管理器 使用Ehcache实现</p>
     * @return
     */
    @Bean(name = "shiroCacheManager")
    public EhCacheManager getEhCacheManager(net.sf.ehcache.CacheManager manager) {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManager(manager);
        return em;
    }

    /**
     * <p>保证实现了Shiro内部lifecycle函数的bean执行</p>
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * <p>会话ID生成</p>
     * @return
     */
    @Bean(name = "sessionIdGenerator")
    public SessionIdGenerator getSessionIdGenerator(){
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * <p>会话DAO</p>
     * @return
     */
    @Bean(name = "sessionDAO")
    public EnterpriseCacheSessionDAO getEnterpriseCacheSessionDAO(){
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(getSessionIdGenerator());
        return sessionDAO;
    }

    /**
     *  <p>会话管理器</p>
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(getEnterpriseCacheSessionDAO());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    /**
     * <p>安全管理器</p>
     * @param myShiroRealm 自实行验证机制
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm,net.sf.ehcache.CacheManager manager) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
        dwsm.setCacheManager(getEhCacheManager(manager));
        dwsm.setSessionManager(getDefaultWebSessionManager());
        return dwsm;
    }

    /**
     * <p>开启权限注解功能</p>
     * <p>可在代码中使用如：@RequiresRoles("admin")进行权限验证</p>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    /**
     * 加载shiroFilter权限控制规则
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/static/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * ShiroFilter<br/>
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new MyShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/405");
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }
}