package com.zjty.productshow.entity.vo;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSelectVo {

    @ApiModelProperty(value = "开始时间")
    @JSONField(format = "yyyy-MM-dd")

    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date finishTime;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @ApiModelProperty(value = "产品名称")
    private String productSeriesName;

    @ApiModelProperty(value = "请求页码")
    private int page;

    @ApiModelProperty(value = "请求数量")
    private int size;

}
