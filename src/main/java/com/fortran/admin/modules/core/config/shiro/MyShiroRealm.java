package com.fortran.admin.modules.core.config.shiro;

import com.fortran.admin.modules.sys.dao.UserDao;
import com.fortran.admin.modules.sys.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义验证
 */
@Component
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserDao userDao;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        // 获取当前登录输入的用户名，等价于(String)
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        log.info("current login : {}",loginName);
         User user = userDao.findByLoginName(loginName);
        if (user != null) {
            // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
          /*  info.setRoles(user.getRolesName());
            // 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            List<SysRole> roleList = user.getSysRoles();
            for (SysRole role : roleList) {
                info.addStringPermissions(role.getPermissionsName());
            }*/
            // 或者按下面这样添加
            // 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
            // simpleAuthorInfo.addRole("admin");
            // 添加权限
            // simpleAuthorInfo.addStringPermission("admin:manage");
            // logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
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
            log.info("login name : {} at {} ",user.getLoginName(),new Date());
            return new SimpleAuthenticationInfo(user.getLoginName(), user.getLoginPwd(), getName());
        }
        return null;
    }
}
