package com.zcoder.admin.modules.core.common;

import com.google.common.base.Preconditions;
import com.zcoder.admin.modules.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 12:44
 * @description:
 */
@Service
public abstract class CrudService <D extends JpaRepository, T>  {

    @Autowired
    protected D dao;


    /**
     * 获取单条数据
     *
     * @param id 主键
     * @return
     */
    public T get(String id) throws ServiceException {
        return (T) dao.findOne(id);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<T> findAll()throws ServiceException {
        return dao.findAll();
    }

    /**
     * 插入数据
     *
     * @param entity 实体
     * @return
     */
    @Transactional(readOnly = false)
    public  T insert(T entity) throws ServiceException{
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return (T)dao.save(entity);
    }

    /**
     * 更新数据
     * @param entity 实体
     * @return
     */
    public T saveAndFlush(T entity)throws ServiceException {
        Preconditions.checkArgument(entity != null, "entity must be not null");
        return (T) dao.saveAndFlush(entity);
    }

    /**
     * 保存集合数据
     *
     * @param it 实体集合
     * @return
     */
    public T save(Iterable it) throws ServiceException{
        Preconditions.checkArgument(it != null, "entity must be not null");
        return (T) dao.save(it);
    }

    /**
     * 删除数据
     * @param entity 实体
     * @return
     */
    public void delete(T entity) throws ServiceException{
        Preconditions.checkArgument(entity != null, "entity must be not null");
        dao.delete(entity);
    }


    /**
     * 是否存在
     * @param id
     * @return
     */
    public boolean exists(Long id) {
        return dao.exists(id);
    }



}
