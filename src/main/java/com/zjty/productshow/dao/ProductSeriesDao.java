package com.zjty.productshow.dao;

import com.zjty.productshow.entity.ProductSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSeriesDao extends JpaRepository<ProductSeries, Integer>, JpaSpecificationExecutor<ProductSeries> {

    ProductSeries findByIdAndStatus(Integer productSeriesId,Integer status);

    /*List<ProductSeries> findAllByStatus(Integer status);*/

    List<ProductSeries> findByStatus(Integer status);







}
