package com.zcoder.admin.modules.sys.dao;

import com.zcoder.admin.modules.sys.domain.Menu;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 13:10
 * @description: <p>系统菜单</p>
 */
@Repository
public interface MenuDao  extends JpaRepository<Menu, Long>,JpaSpecificationExecutor<Menu> {


    List<Menu> findMenus(Menu menu)throws DataAccessException;

}

