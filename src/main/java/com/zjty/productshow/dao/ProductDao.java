package com.zjty.productshow.dao;

import com.zjty.productshow.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByProductSeriesIdAndStatus(Integer productSeriesId, Integer status);

    Product findByIdAndStatus(Integer productId,Integer status);


}
