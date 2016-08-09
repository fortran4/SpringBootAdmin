package com.fortran.admin.modules.core.common;

import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.sys.domain.User;

import java.io.Serializable;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 11:04
 * @description: domain 基类
 */
public class Domain<T> implements Serializable {
    /**
     * 当前用户
     */
    protected User currentUser;


    protected String status;



    /**
     * 当前实体分页对象

     */
    protected Page<T> page;


    public Page<T> getPage() {
        if (page == null){
            page = new Page<T>();
        }
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
