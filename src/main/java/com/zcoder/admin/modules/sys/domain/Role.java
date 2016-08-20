package com.zcoder.admin.modules.sys.domain;

import com.zcoder.admin.modules.core.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:04
 * @description: <p>角色</p>
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId; //角色ID

    @Column
    private String roleName; //角色名称

    @Column
    private List<Menu> permissionList; //权限

    @Column
    private String remarks; //备注



}
