package com.zcoder.admin.modules.sys.domain;

import com.zcoder.admin.modules.core.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 9:22
 * @description: <p>系统菜单</p>
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId; //菜单ID

    /**
     * 菜单名称
     */
    @Column
    private String menuName; //菜单名称

    /**
     *图标
     */
    @Column
    private String icon;  //图标
    /**
     *链接
     */
    @Column
    private String href;//链接地址
    /**
     *是否显示 0：显示，1：隐藏
     */
    @Column
    private String isShow; //是否显示
    /**
     *父菜单ID
     */
    @Column
    private Long parentId; //父菜单ID
    /**
     *权限标识
     */
    @Column
    private String permission; //权限标识
    /**
     *排序
     */
    @Column
    private Integer sort; //排序
    /**
     *目标框架（默认mainFrame）
     */
    @Column
    private String target; //目标框架
    /**
     *备注
     */
    @Column
    private  String remarks; //备注

    /**
     * 子菜单
     */
    @Column
    private List<Menu> childMenu; //子菜单集合


}
