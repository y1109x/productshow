package com.zjty.productshow.service;

import com.alipay.api.AlipayApiException;

import java.math.BigDecimal;
import java.util.Map;

public interface AlipayService {

    /**
     * 生成支付宝支付二维码
     *
     * @param out_trade_no
     *            订单号
     * @param total_fee
     *            金额(分)
     * @return
     */
    public Map createNative(String out_trade_no, Double total_fee);





    /**
     * 交易查询
     * @param outTradeNo 订单编号（唯一）
     */
    String query(String outTradeNo) throws AlipayApiException;

    /**
     * 交易关闭
     * @param outTradeNo（唯一）
     */
    String close(String outTradeNo) throws AlipayApiException;


    /**
     * 查询支付状态
     * @param out_trade_no
     */
    public Map queryPayStatus(String out_trade_no);
}

