package com.zjty.productshow.service.impl;

import com.zjty.productshow.config.MyWXPayConfig;
import com.zjty.productshow.dao.ActivationCodeDao;
import com.zjty.productshow.dao.OrderDao;
import com.zjty.productshow.entity.ActivationCode;
import com.zjty.productshow.entity.PayOrder;
import com.zjty.productshow.entity.vo.PayBean;
import com.zjty.productshow.service.WxPayService;
import com.zjty.productshow.wxUtil.HttpClientUtil;
import com.zjty.productshow.wxUtil.WXPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WxPayServiceImpl implements WxPayService {
    @Autowired
    private MyWXPayConfig wxPayAppConfig;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ActivationCodeDao activationCodeDao;

    @Override
    public Map<Object, Object> save(PayBean payBean,String body){

/*
        //生成订单
        PayOrder payOrder = new PayOrder();

        // 1. 生成订单
        // 订单号，流水号，金额，付款状态，创建时间
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = date.format(new Date()) + WXPayUtils.getCurrentTimestampMs();
        payOrder.setId(orderNo);
        payOrder.setActualPrice(payBean.getActualPrice());
        payOrder.setCreatTime(new Date());
        payOrder.setCount(payBean.getCount());
        payOrder.setPayType(1);
        //将生成的预支付订单保存到数据库
        orderDao.save(payOrder);

        //2.将前端传过来的sn保存到数据库
        ArrayList<ActivationCode> codeList = new ArrayList<>();
        String productSeriesId = payBean.getProductSeriesId().toString();
        List<String> sns = payBean.getSns();
        for (String sn : sns) {
            ActivationCode activationCode = new ActivationCode();
            activationCode.setType(2);
            activationCode.setProductSeriesId(payBean.getProductSeriesId());
            activationCode.setOrderId(payOrder.getId());
            activationCode.setSn(sn);
            activationCode.setActivationTime(new Date());
            codeList.add(activationCode);
        }
        activationCodeDao.saveAll(codeList);

        // 调用统一下单方法，返回 codeUrl 地址
        String codeUrl = unifiedOrder("1","202103231123551616469835986",2.2,body);






 */
        Map<Object, Object> pays = new HashMap<>();
        pays.put("url_code", "http://www.baidu.com");
        pays.put("orderId", "202103231123551616469835986");
        return pays;
    }


    private String unifiedOrder(String productSeriesId, String orderNo, double amount, String body){
        // 生成签名
        try{
            SortedMap<String, String> params = new TreeMap<>();
            params.put("appid",wxPayAppConfig.getAppID());
            params.put("mch_id",wxPayAppConfig.getMchID());
            params.put("nonce_str", WXPayUtils.generateUUID());
            params.put("body",body);                      // 商品描述
            params.put("out_trade_no",orderNo);           // 商户订单号
            params.put("total_fee",String.valueOf((int)(amount*100)));              // 标价金额（单位为分）
            params.put("spbill_create_ip", "外网可以访问的 ip 地址");    // 终端IP

            params.put("notify_url", wxPayAppConfig.getPayNotifyUrl());    // 异步接收微信支付结果通知的回调地址
            params.put("trade_type","NATIVE");                  // 交易类型
            params.put("product_id",productSeriesId);                // 微信支付要求NATIVE支付，此参数必填

            // sign 签名
            String sign = WXPayUtils.createSign(params, wxPayAppConfig.getKey());
            params.put("sign",sign);
            System.out.println(sign);

            // map转xml
            String payXml = WXPayUtils.mapToXml(params);
            System.out.println(payXml);

            // 统一下单
            String s = HttpClientUtil.doPost(MyWXPayConfig.UNIFIED_ORDER_URL, payXml, 10000);
            if(null == s){
                return null;
            }
            Map<String, String> unifiedOrderMap = WXPayUtils.xmlToMap(s);
            System.out.println(unifiedOrderMap.toString());
            if(unifiedOrderMap != null){
                // 前台添加定时器，进行轮询操作，直到支付完毕
                return unifiedOrderMap.get("code_url");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




}
