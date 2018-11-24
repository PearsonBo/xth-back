package com.xth.model.vo.company;


import com.xth.model.base.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 21:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompanyVo extends AbstractVo {

    /**
     * 商户公司营业执照名称
     */
    @ApiModelProperty("商户公司营业执照名称")
    private String name;

    /**
     * 商户等级ID
     */
    @ApiModelProperty("商户等级ID")
    private Long companyLevelId;

    /**
     * 商户星级
     */
    @ApiModelProperty("商户星级")
    private Long starLevel;

    /**
     * 纳税人识别号
     */
    @ApiModelProperty("纳税人识别号")
    private String identification;

    /**
     * 法人
     */
    @ApiModelProperty("法人")
    private String legalPerson;

    /**
     * 成立时间
     */
    @ApiModelProperty("成立时间")
    private Date setUpTime;

}
