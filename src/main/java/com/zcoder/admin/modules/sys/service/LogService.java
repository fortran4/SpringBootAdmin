package com.zcoder.admin.modules.sys.service;

import com.zcoder.admin.modules.core.common.CrudService;
import com.zcoder.admin.modules.core.exception.ServiceException;
import com.zcoder.admin.modules.sys.dao.LogDao;
import com.zcoder.admin.modules.sys.domain.Log;
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
 * Created by lin on 16/8/9.
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao,Log> {

   /**
     * <p>查询所有日志</p>
     * @param pageable 分页参数
     * @param param 查询条件
     * @return
     * @throws ServiceException
     */
    public Page<Log> findAll(Pageable pageable,final Log param)throws ServiceException{

        Specification<Log> spec = new Specification<Log>() {
            public Predicate toPredicate(Root<Log> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if(param.getType()!=null && param.getType().trim().length()>0){
                    list.add(cb.equal(root.get("type").as(String.class), param.getType()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };

        return  dao.findAll(spec,pageable);
    }

}
