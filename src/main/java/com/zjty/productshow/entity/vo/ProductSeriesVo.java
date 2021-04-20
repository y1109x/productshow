package com.zjty.productshow.entity.vo;

import com.zjty.productshow.entity.Image;
import com.zjty.productshow.entity.Product;
import com.zjty.productshow.entity.ProductSeries;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "新增产品")
public class ProductSeriesVo {
    @ApiModelProperty(value = "产品系列详细信息")
    private ProductSeries productSeries;

    @ApiModelProperty(value = "产品版本详细信息")
    private List<Product> products;

    @ApiModelProperty(value = "产品图片详细信息")
    private List<Image> images;
}
