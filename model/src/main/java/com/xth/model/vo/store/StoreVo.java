package com.xth.model.vo.store;


import com.xth.model.base.AbstractVo;
import com.xth.model.enums.DiscountType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 22:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StoreVo extends AbstractVo {

    /**
     * 场馆名称
     */
    @ApiModelProperty("场馆名称")
    private String name;

    /**
     * 场馆所属商户公司ID
     */
    @ApiModelProperty("场馆所属商户公司ID")
    private Long companyId;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private Long cityId;

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
     * 场馆会员等级id
     */
    @ApiModelProperty("场馆会员等级id")
    private Integer storeLevelId;

    /**
     * 星级
     */
    @ApiModelProperty("星级")
    private String starLevel;

    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String imgUrl;

    /**
     * 经营时间（多少年）
     */
    @ApiModelProperty("经营时间（多少年）")
    private Integer runYears;

    /**
     * 总面积(m2)
     */
    @ApiModelProperty("总面积(m2)")
    private String totalArea;

    /**
     * 训练面积
     */
    @ApiModelProperty("训练面积")
    private String trainingArea;

    /**
     * 训练场地数量
     */
    @ApiModelProperty("训练场地数量")
    private Integer siteNums;

    /**
     * 累计学员人数
     */
    @ApiModelProperty("累计学员人数")
    private Integer totalStuNums;

    /**
     * 在馆学员人数
     */
    @ApiModelProperty("在馆学员人数")
    private Integer inStuNums;

    /**
     * 折扣类型
     */
    @ApiModelProperty("折扣类型")
    private DiscountType discountType;

    /**
     * 场馆折扣，如果是比率折扣，保存的是数字；如果是满减，保存的是500|100，满500减100
     */
    @ApiModelProperty("场馆折扣，如果是比率折扣，保存的是数字；如果是满减，保存的是500|100，满500减100")
    private String discountContent;

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

    /**
     * 0代表非热门场馆，非0是热门场馆，数字越大越优先显示
     */
    @ApiModelProperty("0代表非热门场馆，非0是热门场馆，数字越大越优先显示")
    private Integer hotLevel;

    /**
     * 0代表非特色场馆，非0是特色场馆，数字越大越优先显示
     */
    @ApiModelProperty("0代表非特色场馆，非0是特色场馆，数字越大越优先显示")
    private Integer specialLevel;

    /**
     * 距当前位置的距离
     */
    @ApiModelProperty("距当前位置的距离")
    private Double distance;
}
