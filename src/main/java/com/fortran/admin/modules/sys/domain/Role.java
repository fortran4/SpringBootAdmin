package com.fortran.admin.modules.sys.domain;

import com.fortran.admin.modules.core.common.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:04
 * @description: <p>角色</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Domain {

    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 绝对对应的权限菜单
     */
    private List<Menu> permissionList;



}
