package com.zjty.productshow.service;

import com.zjty.productshow.entity.ServerResponse;
import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.PayBean;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PayService {

    public ServerResponse cardPay(PayBean payBean);

    List<ActivationCodeBean> findByOrderId(String orderId);
}
