package com.zjty.productshow.dao;

import com.zjty.productshow.entity.PayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<PayOrder, Integer>, JpaSpecificationExecutor<PayOrder> {

    PayOrder findById(String orderId);
}
