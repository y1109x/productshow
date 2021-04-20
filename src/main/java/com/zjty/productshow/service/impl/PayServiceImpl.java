package com.zjty.productshow.service.impl;

import com.alibaba.fastjson.JSON;
import com.zjty.productshow.dao.ActivationCodeDao;
import com.zjty.productshow.dao.CardDao;
import com.zjty.productshow.dao.OrderDao;
import com.zjty.productshow.dao.ProductSeriesDao;
import com.zjty.productshow.entity.*;
import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.PayBean;
import com.zjty.productshow.service.PayService;
import com.zjty.productshow.util.MacAddressToolUtil;
import com.zjty.productshow.wxUtil.WXPayUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private CardDao cardDao;
    @Autowired
    private ProductSeriesDao productSeriesDao;
    @Autowired
    private ActivationCodeDao activationCodeDao;
    @Autowired
    private OrderDao orderDao;


    public String pay(PayBean payBean){
        Integer payType = payBean.getPayType();
        if (payType != null && payType == 3){

        }
        return null;
    }

    /**
     * 卡支付方式
     * @param payBean
     * @return
     */
    public ServerResponse cardPay(PayBean payBean){
        Integer productSeriesId = payBean.getProductSeriesId();
        ProductSeries productSeries = productSeriesDao.findById(productSeriesId).get();
        if (productSeries.getStatus()==0){
            return ServerResponse.error(401,"抱歉，该产品已下架！");
        }


        Set<String> sns = payBean.getSns();
        for (String sn : sns) {
            List<ActivationCode> codeList = activationCodeDao.findByProductSeriesIdAndSn(productSeriesId, sn);

            if (codeList != null &&  !codeList.isEmpty()){
                for (ActivationCode activationCode : codeList) {
                    if (activationCode.getActivationCodeNo() != null) {
                        return ServerResponse.error("该sn" + sn + "已经购买过该产品的激活码");
                    }
                }
            }
        }

        //生成订单
        PayOrder payOrder = new PayOrder();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderNo = date.format(new Date()) + WXPayUtils.getCurrentTimestampMs();
        payOrder.setId(orderNo);
        payOrder.setPayType(3);
        payOrder.setCount(payBean.getCount());
        payOrder.setActualPrice(payBean.getActualPrice());
        payOrder.setCreatTime(new Date());
        payOrder.setActualPrice(payBean.getActualPrice());
        payOrder = orderDao.save(payOrder);

        System.out.println(JSON.toJSONString("-------"+payOrder));

        String cardNo = payBean.getCardNo();
        System.out.println("==cardNo"+ JSON.toJSONString(cardNo));

        ArrayList<ActivationCodeBean> codeBeans = new ArrayList<>();
        Set<String> sns1 = payBean.getSns();
        List<String> snList = new ArrayList<>();
        for (String sn : sns1) {
            snList.add(sn);
        }

        if (cardNo != null){
            Card card = cardDao.findByCardNo(cardNo);

            //判断卡是0单一卡 还是1通用卡
            if (card!=null) {
                Integer type = card.getType();
                if (type != null && type == 0) {
                    //单一卡时，还需要判断卡和商品是否匹配 卡中剩余激活次数 卡中剩余次数>= 需要激活的次数
                    if (payBean.getProductSeriesId() != null && payBean.getProductSeriesId().equals(card.getProductSeriesId()) && card.getRemainingTimes() > 0 && payBean.getCount() > 0 && card.getRemainingTimes() >= payBean.getCount()) {
                        List<ActivationCodeBean> beanList = newCode(payBean.getProductSeriesId(), snList, card, payOrder);
                        System.out.println("===payOrder" + payOrder.getId());
                        return ServerResponse.ok(payOrder.getId());
                    } else {
                        return ServerResponse.error("卡和商品不匹配或者卡中剩余激活次数不足！");
                    }

                } else if (type != null && type == 1) {
                    if (card.getOriginalTimes() >= payBean.getCount() && card.getRemainingTimes() > 0 && payBean.getCount() > 0 && card.getRemainingTimes() >= payBean.getCount()) {
                        List<ActivationCodeBean> beanList = newCode(payBean.getProductSeriesId(), snList, card, payOrder);
                        System.out.println("===payOrder" + payOrder.getId());
                        return ServerResponse.ok(payOrder.getId());
                    } else {
                        return ServerResponse.error("卡中剩余激活次数不足！");
                    }
                }
                return ServerResponse.error("你输入的卡号有误");

            }
        }
        return ServerResponse.error("请输入正确的卡号");
    }


    /**
     * 根据sn(mac地址）生成激活码
     * @param productSeriesId
     * @param sns
     * @param card
     * @return
     */
    public List<ActivationCodeBean> newCode(Integer productSeriesId, List<String> sns, Card card, PayOrder order){
        Map<String, String> map = new HashMap<String,String>();
        ArrayList<ActivationCodeBean> codeBeans = new ArrayList<>();
        ProductSeries productSeries = productSeriesDao.findById(productSeriesId).get();
        if (sns != null){
            for (String sn : sns) {
                String code = "TYKJ"+sn+productSeries.getPrivateKey();

//                String code = MacAddressToolUtil.workMac(sn+productSeries.getPrivateKey());
                for (int i =0; i<7; i++){
                    code = getMD5String(code);
                }

                if (code != null){
                    //生成激活码对象
                    ActivationCode activationCode = new ActivationCode();

                    activationCode.setActivationTime(new Date());
                    activationCode.setActivationCodeNo(code);
                    activationCode.setSn(sn);
                    activationCode.setProductSeriesId(productSeriesId);

                    productSeries.setActivateTimes(productSeries.getActivateTimes()+1);

                    activationCode.setProductName(productSeries.getChName());
                    activationCode.setType(1);
                    activationCode.setOrderId(order.getId());
                    activationCodeDao.save(activationCode);

                    card.setRemainingTimes(card.getRemainingTimes()-1);
                    /*card.setOrderId(order.getId());*/
                    cardDao.save(card);

                    productSeriesDao.save(productSeries);

                    ActivationCodeBean activationCodeBean = new ActivationCodeBean();
                    activationCodeBean.setActivationCodeNo(code);
                    activationCodeBean.setSn(sn);
                    activationCodeBean.setProductSeriesName(productSeries.getChName());

                    order.setPayTime(new Date());
                    order.setCardNo(card.getCardNo());
                    order.setPayStatus(1);

                    orderDao.save(order);
                    codeBeans.add(activationCodeBean);

                }
            }
            return codeBeans;
        }
        return null;
    }

    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<ActivationCodeBean> findByOrderId(String orderId) {
        ArrayList<ActivationCodeBean> codeBeans = new ArrayList<>();
        List<ActivationCode> codeList = activationCodeDao.findByOrderId(orderId);
        PayOrder order = orderDao.findById(orderId);

        if (codeList != null){
            for (ActivationCode activationCode : codeList) {
                ActivationCodeBean activationCodeBean = new ActivationCodeBean();

                activationCodeBean.setSn(activationCode.getSn());
                activationCodeBean.setActivationCodeNo(activationCode.getActivationCodeNo());
                activationCodeBean.setProductSeriesName(activationCode.getProductName());
                codeBeans.add(activationCodeBean);
            }
            return codeBeans;
        }
        return null;
    }
}
