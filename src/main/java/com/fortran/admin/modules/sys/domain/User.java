package com.fortran.admin.modules.sys.domain;

import com.fortran.admin.modules.core.common.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:04
 * @description: <p></p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Domain{

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 拥有角色
     */
    private List<Role> roles;

    /**
     * 登录验证码
     */
    private String validateCode;



}
