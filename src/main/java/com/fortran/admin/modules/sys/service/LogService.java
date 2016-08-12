package com.fortran.admin.modules.sys.service;

import com.fortran.admin.modules.core.common.CrudService;
import com.fortran.admin.modules.core.config.mybatis.Page;
import com.fortran.admin.modules.core.config.mybatis.PageHelper;
import com.fortran.admin.modules.core.exception.ServiceException;
import com.fortran.admin.modules.sys.dao.LogDao;
import com.fortran.admin.modules.sys.domain.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lin on 16/8/9.
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class LogService extends CrudService<LogDao,Log> {

    /**
     * <p>查询所有日志</p>
     * @param page 分页参数
     * @return
     * @throws ServiceException
     */
    public Page<Log> findLogs(Page<Log> page)throws ServiceException{
        List<Log> data = dao.findLogs(page.getCondition());
        return PageHelper.endPage();
    }


    public List<Log> findLogs(Log log) throws ServiceException{
        return dao.findLogs(log);
    }
}
