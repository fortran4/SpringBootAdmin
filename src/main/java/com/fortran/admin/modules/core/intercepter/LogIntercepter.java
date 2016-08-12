package com.fortran.admin.modules.core.intercepter;

import com.fortran.admin.modules.sys.domain.Log;
import com.fortran.admin.modules.sys.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author: lin
 * @Date: 2016-08-04 Time: 9:41
 * @description: <p>操作日志拦截器</p>
 */
@Aspect
@Component
@Configuration
public class LogIntercepter {

    private static final Logger logger = LoggerFactory.getLogger(LogIntercepter.class);

    @Value("${system.log.enabled}")
    private boolean enabled = true;

    private final static int LOG_TYPE_ACCESS = 1;

    private final static int LOG_TYPE_EXCEPTION = 2;

    @Autowired
    private LogService logService;

    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();

    private static final ThreadLocal<Log> logThread = new ThreadLocal<>();


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
        if (logger.isDebugEnabled()) {
            logger.debug("request uri : " + request.getRequestURL().toString());
            logger.debug("request method : " + request.getMethod());
            logger.debug("request remoteAddr: " + request.getRemoteAddr());
            logger.debug("request method : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                    + joinPoint.getSignature().getName());
            logger.debug("args : " + Arrays.toString(joinPoint.getArgs()));
        }

        Log log = new Log();
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

        if (logger.isDebugEnabled()) {
            logger.debug("response : " + ret);
            logger.debug("spend time : " + (System.currentTimeMillis() - startTime.get()) + "ms");
        }

        Log currentLog = logThread.get();
        currentLog.setResponse(String.valueOf(ret));
       // logService.insert(currentLog);

    }

    /**
     * 方法抛出异常走此方法
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "webLog()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        logger.error("method : {}", joinPoint.getSignature().getName());
        logger.error("exception : {}", ex);
        Log currentLog = logThread.get();
        currentLog.setType(String.valueOf(LOG_TYPE_EXCEPTION));
        currentLog.setException(ex.getMessage());
       // saveLog(currentLog);
    }

    private void saveLog(Log log) {
        Log currentLog = logThread.get();
        if (enabled) {
            logService.insert(currentLog);
            logThread.remove();
        } else {
            logger.warn("system log is not enabled. please checking 'system.log.enabled' config.");
        }
    }


}
