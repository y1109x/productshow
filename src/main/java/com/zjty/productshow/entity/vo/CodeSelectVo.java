package com.zjty.productshow.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeSelectVo {

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date finishTime;

    @ApiModelProperty(value = "即Mac地址")
    private String sn;

    @ApiModelProperty(value = "产品系统名称")
    private String productName;

    @ApiModelProperty(value = "请求页码")
    private int page;

    @ApiModelProperty(value = "请求数量")
    private int size;
}
