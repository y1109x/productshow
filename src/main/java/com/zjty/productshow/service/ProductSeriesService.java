package com.zjty.productshow.service;

import com.zjty.productshow.entity.ProductSeries;
import com.zjty.productshow.entity.vo.PageResponse;
import com.zjty.productshow.entity.vo.ProductSeriesVo;
import com.zjty.productshow.entity.vo.SelectAllVo;
import com.zjty.productshow.entity.vo.SelectShelfVo;


import java.util.List;

public interface ProductSeriesService {

    ProductSeriesVo save(ProductSeriesVo productSeriesvo);

    PageResponse<ProductSeries> findAll(SelectAllVo selectAllVo);

    List<ProductSeries> findByStatus(Integer status);

    ProductSeriesVo findById(Integer id);

    List<ProductSeries> operateShelf(SelectShelfVo selectShelfVo);
}
