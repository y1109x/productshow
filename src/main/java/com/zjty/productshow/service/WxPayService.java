package com.zjty.productshow.service;

import com.zjty.productshow.entity.vo.PayBean;

import java.util.Map;

public interface WxPayService {
    public Map<Object, Object> save(PayBean payBean, String body);

}
