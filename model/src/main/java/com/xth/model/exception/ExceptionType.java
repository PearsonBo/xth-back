package com.xth.model.exception;

/**
 * @author 异常类型
 */
public enum ExceptionType {

    BLANK(""),

    /**
     * 未定义
     */
    NOT_BIZ("未定义"),
    /**
     * 登陆错误
     */
    LOGIN_ERR("登陆错误"),
    /**
     * 乐观锁
     */
    OP_LOCK("乐观锁"),
    /**
     * 数据库参数错误
     */
    DB_PARAM("数据库参数错误"),
    /**
     * 接口参数错误
     */
    INTERFACE_PARAM("接口参数错误"),
    /**
     * 没有资源
     */
    NO_RESOURCE("没有资源"),
    /**
     * 没有结果
     */
    NO_RESULT("没有结果"),
    /**
     * 违反业务规则
     */
    VIOLATE_BIZ_RULE("违反业务规则"),
    /**
     * 未通过业务检查
     */
    VIOLATE_BIZ_CHECK("未通过业务检查"),
    /**
     * 权限
     */
    SECURITY("权限"),
    /**
     * 未登录
     */
    NOT_LOGIN("未登录"),
    /**
     * 导入导出
     */
    IMPORT_EXPORT("导入导出"),
    /**
     * 重复提交
     */
    DUPLICATE_SUBMIT("重复提交"),
    /**
     * 导入导出
     */
    DB_RET_ERROR("导入导出"),
    /**
     * 存在脏数据
     */
    DIRTY_DATA("存在脏数据"),
    /**
     * 存在脏数据
     */
    X6_ERROR("调用X6接口错误"),
    /**
     * 熔断器降级
     */
    HYSTRIX_FALL_BACK("熔断器降级");

    private String name;

    ExceptionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
