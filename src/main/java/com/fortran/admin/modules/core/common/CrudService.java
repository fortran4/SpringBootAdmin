package com.fortran.admin.modules.core.common;

import com.fortran.admin.modules.core.exception.ServiceException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 12:44
 * @description:
 */
public abstract class CrudService<D extends CrudDao, T extends Domain> implements Service {

    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param id 主键
     * @return
     */
    public T get(String id) throws ServiceException{
        return (T) dao.get(id);
    }

    /**
     * 获取单条数据
     *
     * @param entity 实体
     * @return
     */
    public T get(T entity) throws ServiceException{
        return (T) dao.get(entity);
    }

    /**
     * 查询数据列表
     *
     * @param entity 实体
     * @return
     */
    public List<T> findListByCondition(T entity)throws ServiceException {
        return dao.findListByCondition(entity);
    }

    /**
     * 插入数据
     *
     * @param entity 实体
     * @return
     */
    @Transactional(readOnly = false)
    public int insert(T entity) throws ServiceException{
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return dao.insert(entity);
    }

    /**
     * 更新数据
     *
     * @param entity 实体
     * @return
     */
    public T update(T entity)throws ServiceException {
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
    public int updateStatus(T entity) throws ServiceException{
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return dao.updateStatus(entity);
    }

    /**
     * 删除数据
     *
     * @param entity 实体
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(T entity) throws ServiceException{
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return dao.delete(entity);
    }

}
