package com.fortran.admin.modules.sys.domain;

import com.fortran.admin.modules.core.common.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 9:22
 * @description: <p>系统菜单</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends Domain {

    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     *图标
     */
    private String icon;
    /**
     *链接
     */
    private String href;
    /**
     *是否显示 0：显示，1：隐藏
     */
    private String isShow;
    /**
     *父菜单ID
     */
    private Long parentId;
    /**
     *权限标识
     */
    private String permission;
    /**
     *排序
     */
    private Integer sort;
    /**
     *目标框架（默认mainFrame）
     */
    private String target;
    /**
     *备注
     */
    private  String remarks;


}
