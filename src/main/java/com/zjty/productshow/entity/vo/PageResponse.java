package com.zjty.productshow.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@ApiModel(value = "分页排序",description = "分页排序")
public class PageResponse<T> {
    @ApiModelProperty(value = "当前页",example = "1")
    private int currentPage;
    @ApiModelProperty(value = "显示数量",example = "10")
    private int size;
    @ApiModelProperty(value = "总数",example = "32")
    private Long count;
    @ApiModelProperty(value = "分页返回数据集合")
    private List<T> rowList;
}
