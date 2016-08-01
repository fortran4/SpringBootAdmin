package com.fortran.admin.modules.core.exception;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:01
 * @description: service层异常封装
 */
public class ServiceException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 4425626583033519834L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
