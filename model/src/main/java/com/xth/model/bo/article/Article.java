package com.xth.model.bo.article;

import com.xth.model.base.AbstractBo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章
 *
 * @Author: Hu Jianbo
 * @Date: 2018/10/4 0010 下午 22:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Article extends AbstractBo {

    /**
     * 内容标题
     */
    @ApiModelProperty("内容标题")
    private String title;

    /**
     * 内容缩略名
     */
    @ApiModelProperty("内容缩略名")
    private String slug;

    /**
     * 作者id
     */
    @ApiModelProperty("作者id")
    private String authorId;

    /**
     * 作者
     */
    @ApiModelProperty("作者")
    private String author;

    /**
     * 图片url
     */
    @ApiModelProperty("图片url")
    private String imgUrl;

    /**
     * 是否热门推荐,0:否，1：是
     */
    @ApiModelProperty("是否热门推荐,0:否，1：是")
    private Boolean isHot;

    /**
     * 文章状态
     */
    @ApiModelProperty("文章状态")
    private String status;

    /**
     * 点击次数
     */
    @ApiModelProperty("点击次数")
    private Integer hits;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

}