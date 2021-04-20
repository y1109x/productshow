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
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "产品系列")
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "产品系列id",example = "0")
    private Integer ProductId;

    @ApiModelProperty(value = "产品名称")
    private String ProductName;

    @ApiModelProperty(value = "是否激活 0未激活 1已激活",example = "0")
    private Integer isActivate;

    @ApiModelProperty(value = "激活时间")
    private Date activateTime;

    @ApiModelProperty(value = "下载时间")
    private Date downloadTime;

    @ApiModelProperty(value = "使用时间")
    private Date useTime;

    @ApiModelProperty(value = "操作名称")
    private Date operation;

}