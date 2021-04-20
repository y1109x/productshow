package com.zjty.productshow.dao;

import com.zjty.productshow.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDao extends JpaRepository<Image, Integer>, JpaSpecificationExecutor<Image> {
    /**
     * 根据产品系列id 和是否为logo 查询图片
     * @param productSeriesId
     * @return
     */
    List<Image> findAllByProductSeriesId(Integer productSeriesId);


}
