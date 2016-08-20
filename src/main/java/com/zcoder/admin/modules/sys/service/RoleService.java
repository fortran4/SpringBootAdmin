package com.zcoder.admin.modules.sys.service;

import com.zcoder.admin.modules.core.common.CrudService;
import com.zcoder.admin.modules.core.exception.ServiceException;
import com.zcoder.admin.modules.sys.dao.RoleDao;
import com.zcoder.admin.modules.sys.domain.Role;
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
 * @Date: 2016-08-02 Time: 13:11
 * @description:<p>角色服务层</p>
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class RoleService extends CrudService<RoleDao, Role> {

    /**
     * <p>查询所有</p>
     * @param pageable 分页参数
     * @param role 查询条件
     * @return
     * @throws ServiceException
     */
    public Page<Role> findAll(Pageable pageable, final Role role)throws ServiceException{

        Specification<Role> spec = new Specification<Role>() {
            public Predicate toPredicate(Root<Role> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(role.getRoleName()!=null && role.getRoleName().trim().length()>0){
                    list.add(cb.equal(root.get("roleName").as(String.class), role.getRoleName()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        return  dao.findAll(spec,pageable);
    }

}
