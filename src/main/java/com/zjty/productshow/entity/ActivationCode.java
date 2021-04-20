package com.zjty.productshow.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
@ApiModel(value = "激活码")
@Entity
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "产品系列id",example = "0")
    private Integer productSeriesId;

    @ApiModelProperty(value = "订单id",example = "0")
    private String orderId;

    @ApiModelProperty(value = "产品系列名称")
    private String productName;

    @ApiModelProperty(value = "激活码")
    private String activationCodeNo;

    @ApiModelProperty(value = "即Mac地址")
    private String sn;

    @ApiModelProperty(value = "激活时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date activationTime;

    @ApiModelProperty(value = "生成方式 0直接购买 1卡",example = "0")
    private Integer type;
}
