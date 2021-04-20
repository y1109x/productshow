package com.zjty.productshow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(value = "订单")
public class PayOrder {
    @Id
    private String id;

    @ApiModelProperty(value = "卡号",example = "0")
    private String cardNo;

    @ApiModelProperty(value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    @ApiModelProperty(value = "购买激活码个数",example = "0")
    private Integer count;

    @ApiModelProperty(value = "付款时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    @ApiModelProperty(value = "付款方式 1支付宝 2微信 3卡",example = "0")
    private Integer payType;

    @ApiModelProperty(value = "应付价格",example = "0.0")
    private Double payablePrice;

    @ApiModelProperty(value = "实付价格",example = "0.0")
    private Double actualPrice;

    @ApiModelProperty(value = "支付状态 0未支付 1已支付")
    private Integer payStatus;

}
