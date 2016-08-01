package com.fortran.admin.modules.core.common;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:27
 * @description: service层
 */
@Transactional(readOnly = true)
public interface Service<Dao extends CrudDao, T> {

}
