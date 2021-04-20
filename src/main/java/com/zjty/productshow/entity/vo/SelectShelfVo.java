package com.zjty.productshow.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectShelfVo {

    @ApiModelProperty(value = "产品系列集合")
    private List<Integer> productSeriesIds;

    @ApiModelProperty(value = "操作类型 0下架 1上架")
    private Integer type;



}
