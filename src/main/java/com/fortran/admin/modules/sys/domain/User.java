package com.fortran.admin.modules.sys.domain;

import com.fortran.admin.modules.core.common.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:04
 * @description: <p></p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Domain{

    private String loginName;

    private String loginPwd;

}
