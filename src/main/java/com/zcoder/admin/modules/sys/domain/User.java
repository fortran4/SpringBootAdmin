package com.zcoder.admin.modules.sys.domain;

import com.zcoder.admin.modules.core.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:04
 * @description: <p></p>
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; //用户ID

    @Column
    private String loginName; //用户名

    @Column
    private String loginPwd; //密码

    @Column
    private List<Role> roles; //角色

    @Column
    private String validateCode; //验证码

    @Column
    private String realName; //真实姓名

    @Column
    private String phone;  //电话

    @Column
    private String email; // 邮箱


    @Column
    private String headImg; //头像地址

    @Column
    private String gender; //性别

    @Column
    private Date birthday;  //生日
}
