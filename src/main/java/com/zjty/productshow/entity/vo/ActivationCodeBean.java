package com.zjty.productshow.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCodeBean {
    @ApiModelProperty(value = "产品系列名称",example = "0")
    private String productSeriesName;

    @ApiModelProperty(value = "激活码")
    private String activationCodeNo;

    @ApiModelProperty(value = "即Mac地址")
    private String sn;

    @ApiModelProperty(value = "支付状态")
    private Integer payStatus;





}
