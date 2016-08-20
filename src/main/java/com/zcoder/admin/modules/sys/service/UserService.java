package com.zcoder.admin.modules.sys.service;

import com.zcoder.admin.modules.core.common.CrudService;
import com.zcoder.admin.modules.core.exception.ServiceException;
import com.zcoder.admin.modules.core.utils.guava.MyStrings;
import com.zcoder.admin.modules.sys.dao.UserDao;
import com.zcoder.admin.modules.sys.domain.Menu;
import com.zcoder.admin.modules.sys.domain.User;
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
 * @Date: 2016-07-26 Time: 11:28
 * @description:
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService extends CrudService<UserDao,User> {


    /**
     * <p>根据登录名查询系统菜单权限</p>
     * @param loginName 登录名
     * @return
     * @throws ServiceException
     */
    public  List<Menu> findPermissionByLoginName(String loginName) throws ServiceException{
        return dao.findPermissionByLoginName(loginName);
    }

    /**
     * <p>查询子菜单</p>
     * @param parentId 父ID
     * @return
     * @throws ServiceException
     */
    public List<Menu> findMenuByParentId(String parentId)throws ServiceException{
        return dao.findMenuByParentId(parentId);
    }


    /**
     * <p>查询所有</p>
     * @param pageable 分页参数
     * @param user 查询条件
     * @return
     * @throws ServiceException
     */
    public Page<User> findAll(Pageable pageable, final User user)throws ServiceException{

        Specification<User> spec = new Specification<User>() {
            public Predicate toPredicate(Root<User> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(!MyStrings.isNullOrEmpty(user.getLoginName())){
                    list.add(cb.equal(root.get("loginName").as(String.class), user.getLoginName()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        return  dao.findAll(spec,pageable);
    }



}
