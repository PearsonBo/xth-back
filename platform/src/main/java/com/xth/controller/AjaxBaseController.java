package com.xth.controller;

import com.xth.model.base.PackVo;
import com.xth.model.enums.PredefineCode;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 22:08
 */
public class AjaxBaseController {

    protected static final String CREATE = "/create";

    protected static final String UPDATE = "/update";

    protected static final String DELETE = "/delete";

    protected static final String FIND = "/find";

    protected static final String LIST = "/list";

    protected static final String LIST_PAGINATION = "/listPagination";

    protected static final String EXPORT = "/export";

    protected static final String IMPORT = "/import";

    protected static final String TERMINATE = "/terminate";

    protected static final String ACTIVE = "/active";

    protected static final String FROZEN = "/frozen";

    protected static final String INACTIVE = "/inactive";

    protected static final String LIST_PAGINATION_WITH_DATA_PERMISSION = "listPaginationWithDataPermission";

    protected static final Logger LOG = LoggerFactory.getLogger(AjaxBaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public PackVo<Serializable> ajaxException(Exception e) {
        PackVo<Serializable> packVo = new PackVo<>();
        packVo.setSuccess(Boolean.FALSE);
        LOG.error("异常", e);
        if (e instanceof BizException) {
            packVo.setMessage(e.getMessage());
            if (e.getMessage().indexOf(ExceptionType.NOT_LOGIN.getName()) != -1) {
                packVo.setCode(PredefineCode.NOT_LOGIN.getName());
            }
            return packVo;
        }

        if (e instanceof MissingServletRequestParameterException
                || e instanceof NumberFormatException
                || e instanceof TypeMismatchException
                || e instanceof IllegalArgumentException
                || e instanceof SecurityException) {
            packVo.setMessage(e.getMessage());
            return packVo;
        }

        BizException bizException = new BizException("系统异常", e, ExceptionType.NOT_BIZ, false);
        packVo.setMessage(bizException.getMessage());
        return packVo;
    }

}
