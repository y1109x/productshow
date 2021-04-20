package com.zjty.productshow.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectAllVo {
    @ApiModelProperty(value = "中文名称")
    private String chName;

    @ApiModelProperty(value = "上下架状态 0下架 1上架",example = "0")
    private Integer status;

    @ApiModelProperty(value = "请求页码")
    private int page;

    @ApiModelProperty(value = "请求数量")
    private int size;

}


