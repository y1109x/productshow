package com.zjty.productshow.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSeriesVo1 {
    private Integer id;

    @ApiModelProperty(value = "中文名称")
    private String chName;
}
