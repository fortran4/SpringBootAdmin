package com.fortran.admin.modules.core.config.shiro;

import com.fortran.admin.modules.core.config.cache.CacheHelper;
import com.fortran.admin.modules.sys.dao.UserDao;
import com.fortran.admin.modules.sys.domain.Menu;
import com.fortran.admin.modules.sys.domain.Role;
import com.fortran.admin.modules.sys.domain.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 自定义验证
 */
@Component
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        log.info("current login : {}", loginName);
        User user = userDao.findByLoginName(loginName);
        if (user != null) {

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //set role
            List<Role> roles = userDao.findRolesByLoginName(loginName);
            Set<String> roleNames = Sets.newHashSet();
            for (Role role : roles) {
                roleNames.add(role.getRoleName());
            }
            info.setRoles(roleNames);

            //set permisssion
            List<Menu> menus = userDao.findPermissionByLoginName(loginName);
            CacheHelper.put(loginName, menus);
            List<String> permisssions = Lists.newArrayList();
            for (Menu menu : menus) {
                permisssions.add(menu.getPermission());
            }
            info.addStringPermissions(permisssions);
            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userDao.findByLoginName(token.getUsername());
        if (user != null) {
            log.info("login name : {} at {} ", user.getLoginName(), new Date());
            isKick(user.getLoginName());
            return new SimpleAuthenticationInfo(user.getLoginName(), user.getLoginPwd(), getName());
        }
        return null;
    }

    /**
     * <p>是否踢出同名登录人</p>
     *
     * @param userName 登录账号
     * @return
     */
    protected void isKick(String userName) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {

            if (userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                session.setTimeout(0);//设置session立即失效，即将其踢出系统
                log.info("loginName:{} is kicked.",userName);
                break;
            }

        }
    }
}
