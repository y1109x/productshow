package com.zjty.productshow.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateCardVo {
    @ApiModelProperty(value = "产品系列id")
    private Integer productSeriesId;

    @ApiModelProperty(value = "原激活次数")
    private Integer originalTimes;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "卡类型 0单一卡 1通用卡",example = "0")
    private Integer type;



}
