package com.zcoder.admin.modules.sys.service;

import com.zcoder.admin.modules.core.common.CrudService;
import com.zcoder.admin.modules.core.exception.ServiceException;
import com.zcoder.admin.modules.sys.dao.MenuDao;
import com.zcoder.admin.modules.sys.domain.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lin
 * @Date: 2016-08-02 Time: 13:13
 * @description:<p>菜单服务层</p>
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class MenuService extends CrudService<MenuDao, Menu> {


    /**
     * <p>查询所有</p>
     * @param pageable 分页参数
     * @param menu 查询条件
     * @return
     * @throws ServiceException
     */
    public Page<Menu> findAll(Pageable pageable, final Menu menu)throws ServiceException{

        Specification<Menu> spec = new Specification<Menu>() {
            public Predicate toPredicate(Root<Menu> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(menu.getMenuName()!=null && menu.getMenuName().trim().length()>0){
                    list.add(cb.equal(root.get("menuName").as(String.class), menu.getMenuName()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        return  dao.findAll(spec,pageable);
    }

}
