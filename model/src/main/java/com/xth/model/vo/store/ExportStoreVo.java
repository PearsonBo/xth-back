package com.xth.model.vo.store;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 22:12
 */
@Data
public class ExportStoreVo implements Serializable {

    /**
     * 场馆名称
     */
    @ApiModelProperty("场馆名称")
    private String name;

    /**
     * 市名称
     */
    @ApiModelProperty("市名称")
    private String cityName;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String address;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;


    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 联系人姓名
     */
    @ApiModelProperty("联系人姓名")
    private String contactName;

    /**
     * 联系人电话
     */
    @ApiModelProperty("联系人电话")
    private String contactPhone;

    /**
     * 在馆学员人数
     */
    @ApiModelProperty("在馆学员人数")
    private Integer inStuNums;

    /**
     * 星级
     */
    @ApiModelProperty("星级")
    private String starLevel;

    /**
     * 场馆折扣展示信息
     */
    @ApiModelProperty("场馆折扣展示信息")
    private String discountContentMessage;

    /**
     * 是否是热门场馆
     */
    @ApiModelProperty("是否是热门场馆,true代表属于热门，false属于非热门")
    private Boolean isHot;

}
