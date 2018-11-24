package com.xth.model.enums;


import java.util.ArrayList;
import java.util.List;

import static com.xth.model.exception.ExceptionType.VIOLATE_BIZ_RULE;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 23:44
 */
public enum CommonActiveStatusEnum {
    /**
     * 草稿
     */
    DRAFT("草稿"),
    /**
     * 活动
     */
    ACTIVE("活动"),
    /**
     * 冻结
     */
    FROZEN("冻结"),
    /**
     * 作废
     */
    INACTIVE("作废");

    private String name;

    CommonActiveStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 是否可以激活
     *
     * @param commonActiveStatusEnum
     */
    public static void canActive(CommonActiveStatusEnum commonActiveStatusEnum) {
        switch (commonActiveStatusEnum) {
            case ACTIVE:
                throw new com.xth.model.exception.BizException("已激活的不能重复激活", VIOLATE_BIZ_RULE);
            case DRAFT:
                break;
            case INACTIVE:
                throw new com.xth.model.exception.BizException("已作废的不能激活", VIOLATE_BIZ_RULE);
            case FROZEN:
                break;
            default:
                break;
        }
    }

    /**
     * 是否可以冻结
     *
     * @param commonActiveStatusEnum
     */
    public static void canFrozen(CommonActiveStatusEnum commonActiveStatusEnum) {
        switch (commonActiveStatusEnum) {
            case ACTIVE:
                break;
            case DRAFT:
                throw new com.xth.model.exception.BizException("草稿状态不能冻结", VIOLATE_BIZ_RULE);
            case INACTIVE:
                throw new com.xth.model.exception.BizException("已作废的不能冻结", VIOLATE_BIZ_RULE);
            case FROZEN:
                throw new com.xth.model.exception.BizException("已冻结的不能重复冻结", VIOLATE_BIZ_RULE);
            default:
                break;
        }
    }

    /**
     * 是否可以作废
     *
     * @param commonActiveStatusEnum
     */
    public static void canInactive(CommonActiveStatusEnum commonActiveStatusEnum) {
        switch (commonActiveStatusEnum) {
            case ACTIVE:
                break;
            case DRAFT:
                throw new com.xth.model.exception.BizException("草稿状态不能作废", VIOLATE_BIZ_RULE);
            case INACTIVE:
                throw new com.xth.model.exception.BizException("已作废的不能重复作废", VIOLATE_BIZ_RULE);
            case FROZEN:
                break;
            default:
                break;
        }
    }

    /**
     * 是否需要校验
     *
     * @param commonActiveStatusEnum
     * @return
     */
    public static boolean needCheck(CommonActiveStatusEnum commonActiveStatusEnum) {
        if (CommonActiveStatusEnum.DRAFT.equals(commonActiveStatusEnum)) {
            return false;
        }
        return true;
    }

    /**
     * 获取所有非作废状态集合
     *
     * @return 获取所有非作废状态集合
     */
    public static List<CommonActiveStatusEnum> getNotInactiveStatusList() {
        List<CommonActiveStatusEnum> statusList = new ArrayList<>();
        statusList.add(CommonActiveStatusEnum.ACTIVE);
        statusList.add(CommonActiveStatusEnum.DRAFT);
        statusList.add(CommonActiveStatusEnum.FROZEN);
        return statusList;
    }

}
