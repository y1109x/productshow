package com.zjty.productshow.entity.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayBean {
    @ApiModelProperty(value = "卡号",example = "0")
    private String cardNo;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    @ApiModelProperty(value = "购买激活码个数",example = "0")
    private Integer count;

    @ApiModelProperty(value = "mac地址",example = "0")
        Set<String> sns;

    @ApiModelProperty(value = "应付价格")
    private Double payablePrice;

    @ApiModelProperty(value = "实付价格")
    private Double actualPrice;

    @ApiModelProperty(value = "付款方式 1支付宝 2微信 3卡",example = "0")
    private Integer payType;

    @ApiModelProperty(value = "产品系列id",example = "0")
    private Integer productSeriesId;



}
