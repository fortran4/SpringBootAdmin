package com.fortran.admin.modules.core.common;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 10:56
 * @description: Dao基类
 */
public interface Dao<T> {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public  T get(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
     T get(T entity);

    /**
     * 查询数据列表
     * @param entity
     * @return
     */
     List<T> findListByCondition(T entity);

    /**
     * 插入数据
     * @param entity
     * @return
     */
     int insert(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
     T update(T entity);

    /**
     * 更新数据状态
     * @param entity
     * @return
     */
    T updateStatus(T entity);

    /**
     * 删除数据
     * @param entity
     * @return
     */
     int delete(T entity);

}
