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
@Entity
@ApiModel(value = "卡")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "产品系列id",example = "0")
    private Integer productSeriesId;

    /*@ApiModelProperty(value = "订单id",example = "0")
    private String orderId;*/

    @ApiModelProperty(value = "卡编号")
    private String cardNo;

    @ApiModelProperty(value = "产品名称")
    private String productSeriesName;

    @ApiModelProperty(value = "原激活次数",example = "0")
    private Integer originalTimes;

    @ApiModelProperty(value = "剩余激活次数",example = "0")
    private Integer RemainingTimes;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "生成时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date applicationTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "卡类型 0单一卡 1通用卡",example = "0")
    private Integer type;

}
