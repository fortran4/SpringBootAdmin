package com.fortran.admin.modules.core.common;

import com.google.common.base.Preconditions;
import com.fortran.admin.modules.core.config.mybatis.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 12:44
 * @description:
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao, T extends Domain> implements Service {

    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param id  主键
     * @return
     */
    public T get(String id) {
        Preconditions.checkArgument(id != null, "id must be not null");
        return (T) dao.get(id);
    }

    /**
     * 获取单条数据
     *
     * @param entity 实体
     * @return
     */
    T get(T entity) {
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return (T) dao.get(entity);
    }

    /**
     * 查询数据列表
     *
     * @param entity 实体
     * @return
     */
    List<T> findListByCondition(T entity) {
        return dao.findListByCondition(entity);
    }

    /**
     * 插入数据
     *
     * @param entity 实体
     * @return
     */
    @Transactional(readOnly = false)
    int insert(T entity) {
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return dao.insert(entity);
    }

    /**
     * 更新数据
     *
     * @param entity 实体
     * @return
     */
    @Transactional(readOnly = false)
    T update(T entity) {
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return (T) dao.update(entity);
    }

    /**
     * 更新数据状态
     *
     * @param entity 实体
     * @return
     */
    @Transactional(readOnly = false)
    T updateStatus(T entity) {
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return (T) dao.update(entity);
    }

    /**
     * 删除数据
     *
     * @param entity 实体
     * @return
     */
    @Transactional(readOnly = false)
    int delete(T entity) {
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return dao.delete(entity);
    }

    /**
     * 查询分页数据
     *
     * @param page   分页对象
     * @param entity 实体
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findListByCondition(entity));
        return page;
    }


}
