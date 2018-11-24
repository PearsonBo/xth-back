package com.xth.service;

import com.xth.model.base.AbstractBo;
import com.xth.model.base.AbstractVo;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 检查空指针
 *
 * @author Hu Jianbo
 */
public class CheckEmptyHelper {

    /**
     * string 是否存在
     */
    public static void checkLongAndStringAndString(String error, Long param1, String param2, String param3) {
        if (!existIdAndStringAndString(param1, param2, param3)) {
            throw new BizException(error, ExceptionType.VIOLATE_BIZ_CHECK);
        }
    }

    /**
     * string 是否存在
     */
    public static void checkLongAndString(String error, Long param1, String param2) {
        if (!existIdAndString(param1, param2)) {
            throw new BizException(error, ExceptionType.VIOLATE_BIZ_CHECK);
        }
    }

    /**
     * string 是否存在
     */
    public static void checkString(String error, String param) {
        checkString(error, ExceptionType.VIOLATE_BIZ_CHECK, param);
    }

    /**
     * @param error
     * @param param
     * @param type
     */
    public static void checkString(String error, ExceptionType type, String param) {
        if (!existString(param)) {
            throw new BizException(error, type);
        }
    }

    /**
     * string 是否存在
     */
    public static void checkDate(String error, Date param) {
        checkDate(error, ExceptionType.VIOLATE_BIZ_CHECK, param);
    }

    /**
     * string 是否存在
     */
    public static void checkDate(String error, ExceptionType type, Date param) {
        checkObject(error, type, param);
    }

    /**
     * long 是否存在
     *
     * @checkLong param
     */
    public static void checkLong(String error, Long param) {
        checkLong(error, ExceptionType.VIOLATE_BIZ_CHECK, param);
    }

    /**
     * long 是否存在
     *
     * @checkLong param
     */
    public static void checkLong(String error, ExceptionType type, Long param) {
        checkObject(error, type, param);
    }

    /**
     * Integer 是否存在
     */
    public static void checkInteger(String error, Integer param) {
        checkInteger(error, ExceptionType.VIOLATE_BIZ_CHECK, param);
    }

    /**
     * Integer 是否存在
     */
    public static void checkInteger(String error, ExceptionType type, Integer param) {
        checkObject(error, type, param);
    }

    /**
     * 检查对象是否存在
     */
    public static void checkObject(String error, Object object) {
        checkObject(error, ExceptionType.VIOLATE_BIZ_CHECK, object);
    }

    /**
     * 检查对象是否存在
     */
    public static void checkObject(String error, ExceptionType type, Object object) {
        if (!existObject(object)) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查多个对象是否存在
     *
     * @param error   错误信息
     * @param objects 对象集合
     */
    public static void checkObjectMultipleOr(String error, Object... objects) {
        checkObjectMultipleOr(error, ExceptionType.VIOLATE_BIZ_CHECK, objects);
    }

    /**
     * 批量检查Object对象是否为空
     */
    public static void checkObjectMultipleOr(String error, ExceptionType type, Object... objects) {
        for (Object object : objects) {
            if (!existObject(object)) {
                throw new BizException(error, type);
            }
        }
    }

    /**
     * 检查list是否存在
     */
    public static void checkList(String error, List list) {
        checkList(error, ExceptionType.VIOLATE_BIZ_CHECK, list);
    }

    /**
     * 检查list是否存在
     */
    public static void checkList(String error, ExceptionType type, List list) {
        if (!existList(list)) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查多个 and and 是否存在
     */
    public static void checkStringMultipleAnd(String error, String... params) {
        checkStringMultipleAnd(error, ExceptionType.VIOLATE_BIZ_CHECK, params);
    }

    /**
     * 检查多个 and and 是否存在
     */
    public static void checkStringMultipleAnd(String error, ExceptionType type, String... params) {
        int count = params.length;
        for (String param : params) {
            if (!existString(param)) {
                count--;
            }
        }
        if (count == 0) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查多个 and and 是否存在
     */
    public static void checkObjectMultipleAnd(String error, Object... params) {
        checkObjectMultipleAnd(error, ExceptionType.VIOLATE_BIZ_CHECK, params);
    }

    /**
     * 检查多个 and and 是否存在
     */
    public static void checkObjectMultipleAnd(String error, ExceptionType type, Object... params) {
        int count = params.length;
        for (Object param : params) {
            if (!existObject(param)) {
                count--;
            }
        }
        if (count == 0) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查多个 or or 是否存在
     */
    public static void checkStringMultipleOr(String error, String... params) {
        checkStringMultipleOr(error, ExceptionType.VIOLATE_BIZ_CHECK, params);
    }

    /**
     * 检查多个 or or 是否存在
     */
    public static void checkStringMultipleOr(String error, ExceptionType type, String... params) {
        for (String param : params) {
            if (!existString(param)) {
                throw new BizException(error, type);
            }
        }
    }

    /**
     * object 是否存在
     */
    private static boolean existObject(Object param) {
        if (param == null) {
            return false;
        }
        return true;
    }

    /**
     * string 是否存在
     */
    private static boolean existString(String param) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        return true;
    }

    /**
     * 检查list是否存在
     */
    private static boolean existList(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }

    /**
     * objectVo id 是否存在
     */
    public static boolean existObjectVoAndId(AbstractVo param) {
        if (existObject(param) && existObject(param.getId())) {
            return true;
        }
        return false;
    }

    /**
     * objectBo id 是否存在
     */
    public static boolean existObjectBoAndId(AbstractBo param) {
        if (existObject(param) && existObject(param.getId())) {
            return true;
        }
        return false;
    }

    /**
     * id string 是否存在
     */
    public static boolean existIdAndString(Long id, String param1) {
        if (existObject(id) && existString(param1)) {
            return true;
        }
        return false;
    }

    /**
     * objectVo id 是否存在
     */
    public static boolean existIdAndStringAndString(Long id, String param1, String param2) {
        if (existIdAndString(id, param1) && existString(param2)) {
            return true;
        }
        return false;
    }

    /**
     * boolean
     */
    public static boolean existBoolean(Boolean param) {
        if (param != null && param) {
            return true;
        }
        return false;
    }

}
