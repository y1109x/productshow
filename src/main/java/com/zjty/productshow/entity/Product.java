package com.zjty.productshow.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(value = "产品")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "产品系列id",example = "0")
    private Integer productSeriesId;

    @ApiModelProperty(value = "下载次数",example = "0")
    private Integer downloadTimes;

    @ApiModelProperty(value = "操作系统")
    private String systemOs;

    @ApiModelProperty(value = "下载url")
    private String url;

    @ApiModelProperty(value = "文件名")
    private String productName;

    @ApiModelProperty(value = "上下架状态 0下架 1上架",example = "0")
    private Integer status;
}
