package com.fortran.admin.modules.core.validator;

import com.fortran.admin.modules.core.message.RespMsg;

/**
 * @author: lin
 * @Date: 2016-07-29 Time: 15:06
 * @description:
 */
public interface ServerValidator<T> {

    RespMsg validator(T t, Rules rules);

}
