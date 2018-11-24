package com.xth.model.exception;


import java.util.HashMap;
import java.util.Map;

/**
 * @author 业务异常定义
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 7935408141184859889L;

    public static final String SPACE = " ";

    private boolean showExceptionMessage2user = true;

    private ExceptionType exceptionType;

    private Map<String, String> errorMap = new HashMap<String, String>();

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    private void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Boolean getShowExceptionMessage2user() {
        return showExceptionMessage2user;
    }

    private void setShowExceptionMessage2user(Boolean showExceptionMessage2user) {
        this.showExceptionMessage2user = showExceptionMessage2user;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public BizException(String message, ExceptionType exceptionType) {
        super(exceptionType.getName() + SPACE + message);
        this.setExceptionType(exceptionType);
    }

    public BizException(String message, ExceptionType exceptionType, boolean showExceptionMessage2user) {
        super(exceptionType.getName() + SPACE + message);
        this.setExceptionType(exceptionType);
        this.setShowExceptionMessage2user(showExceptionMessage2user);

    }

    public BizException(String message, Throwable cause, ExceptionType exceptionType, boolean showExceptionMessage2user) {
        super(exceptionType.getName() + SPACE + message, cause);
        this.setExceptionType(exceptionType);
        this.setShowExceptionMessage2user(showExceptionMessage2user);

    }
}
