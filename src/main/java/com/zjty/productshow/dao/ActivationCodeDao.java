package com.zjty.productshow.dao;

import com.zjty.productshow.entity.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivationCodeDao extends JpaRepository<ActivationCode, Integer>, JpaSpecificationExecutor<ActivationCode> {

    List<ActivationCode> findBySn(String sn);

    List<ActivationCode> findByOrderId(String orderId);

    List<ActivationCode> findByProductSeriesIdAndSn(Integer productSeriesId, String sn);
}
