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
@ApiModel(value = "图片")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "产品系列id",example = "0")
    private Integer productSeriesId;

    @ApiModelProperty(value = "图片名称")
    private String imageName;

    @ApiModelProperty(value = "图片下载地址")
    private String imageUrl;

}
