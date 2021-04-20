package com.zjty.productshow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "产品系列")
@Entity
public class ProductSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "私钥")
    private String privateKey;

    @ApiModelProperty(value = "中文名称")
    private String chName;

    @ApiModelProperty(value = "英文名称")
    private String enName;

    @ApiModelProperty(value = "Logo的下载url")
    private String logo;

    @Type(type = "text")
    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "关键词")
    private String keyWord;

    @ApiModelProperty(value = "原价格")
    private double originalPrice;

    @ApiModelProperty(value = "实际价格")
    private double actualPrice;

    @ApiModelProperty(value = "总下载次数")
    private Integer totalDownloads;

    @ApiModelProperty(value = "激活次数")
    private Integer activateTimes;

    @ApiModelProperty(value = "上下架状态 0下架 1上架",example = "0")
    private Integer status;

    @ApiModelProperty(value = "图片id",example = "0")
    private Integer imageId;

    @ApiModelProperty(value = "更新时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date updateTime;

}
