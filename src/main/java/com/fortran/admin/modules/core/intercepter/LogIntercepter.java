package com.fortran.admin.modules.core.intercepter;

import com.fortran.admin.modules.sys.dao.LogDao;
import com.fortran.admin.modules.sys.domain.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: lin
 * @Date: 2016-08-04 Time: 9:41
 * @description: <p>操作日志拦截器</p>
 */
@Aspect
@Component
@Configuration
@Slf4j
public class LogIntercepter {

    @Value("${system.log.enabled}")
    private boolean enabled = true;

    private final static int LOG_TYPE_ACCESS = 1;

    private final static int LOG_TYPE_EXCEPTION = 2;

    @Autowired
    private LogDao logDao;

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    private ThreadLocal<Log> logThread = new ThreadLocal<>();

    private ExecutorService executor = Executors.newCachedThreadPool();

    @Pointcut("execution(* com.fortran.admin.modules.*.service..*Service.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 省略日志记录内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        if (log.isDebugEnabled()) {
            log.debug("request uri : " + request.getRequestURL().toString());
            log.debug("request method : " + request.getMethod());
            log.debug("request remote addr: " + request.getRemoteAddr());
            log.debug("class method : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                    + joinPoint.getSignature().getName());
            log.debug("args : " + Arrays.toString(joinPoint.getArgs()));
        }

        Log log = new Log();
        log.setCreateDate(new Date());
        Object[] args = joinPoint.getArgs();
        log.setMethod(joinPoint.getSignature().getName());
        if (args.length > 0) {
            log.setParams(Arrays.toString(args));
        }
        log.setRemoteAddr(request.getRemoteAddr());
        log.setRequestUri(request.getRequestURL().toString());
        log.setUserAgent(request.getHeader("user-agent"));
        log.setType(String.valueOf(LOG_TYPE_ACCESS));
        logThread.set(log);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {

        if (log.isDebugEnabled()) {
            log.debug("response : " + ret);
            log.debug("spend time : " + (System.currentTimeMillis() - startTime.get()) + "ms");
        }

        Log currentLog = logThread.get();
        currentLog.setResponse(String.valueOf(ret));
        executor.execute(new MyLog());

    }

    /**
     * 方法抛出异常走此方法
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "webLog()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("method : {}", joinPoint.getSignature().getName());
        log.error("exception : {}", ex);
        Log currentLog = logThread.get();
        currentLog.setType(String.valueOf(LOG_TYPE_EXCEPTION));
        currentLog.setException(ex.getMessage());
        executor.execute(new MyLog());
    }

    class MyLog extends Thread {

        @Override
        public void run() {
            Log currentLog = logThread.get();
            if (enabled) {
                logDao.insert(currentLog);
            }else{
                log.warn("system log is not enabled. please checking 'system.log.enabled' config.");
            }
        }

    }

}
