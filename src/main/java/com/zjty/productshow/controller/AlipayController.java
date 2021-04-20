package com.zjty.productshow.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sun.istack.NotNull;
import com.zjty.productshow.config.AlipayConfig;
import com.zjty.productshow.dao.ActivationCodeDao;
import com.zjty.productshow.dao.OrderDao;
import com.zjty.productshow.dao.ProductSeriesDao;
import com.zjty.productshow.entity.*;

import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.PayBean;
import com.zjty.productshow.service.ActivationCodeService;
import com.zjty.productshow.service.AlipayService;
import com.zjty.productshow.service.PayService;
import com.zjty.productshow.wxUtil.WXPayUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.zjty.productshow.service.impl.PayServiceImpl.getMD5String;


@RestController
@RequestMapping("/Alipay/front")
@Api(value = "支付宝支付",description = "支付宝支付")
public class AlipayController {

    @Autowired
    private PayService payService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ActivationCodeDao activationCodeDao;
    @Autowired
    private ActivationCodeService activationCodeService;
    @Autowired
    private ProductSeriesDao productSeriesDao;

    /**
     * 订单支付
     */
    @ApiOperation("支付宝支付接口")
    @PostMapping(value = "/createNative")
    public ServerResponse goPay(@NotNull @RequestBody PayBean payBean) throws Exception{

        Integer productSeriesId = payBean.getProductSeriesId();
        ProductSeries productSeries = productSeriesDao.findById(productSeriesId).get();
        if (productSeries.getStatus()==0){
            return ServerResponse.error(401,"抱歉，该产品已下架！");
        }

        Set<String> sns = payBean.getSns();
        for (String sn : sns) {
            List<ActivationCode> codeList = activationCodeDao.findByProductSeriesIdAndSn(productSeriesId, sn);

            if (codeList != null &&  !codeList.isEmpty() ){
                for (ActivationCode activationCode : codeList) {
                    if (activationCode.getActivationCodeNo() != null){
                        return ServerResponse.error(400,"该sn"+sn+"已经购买过该产品的激活码");
                    }
                }
            }
        }

        //生成订单类
        PayOrder payOrder = new PayOrder();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
        String outTradeNo = date.format(new Date()) + WXPayUtils.getCurrentTimestampMs();
        payOrder.setId(outTradeNo);
        payOrder.setPayType(1);
        payOrder.setCount(payBean.getCount());
        payOrder.setPayablePrice(payBean.getActualPrice());
        payOrder.setActualPrice(payBean.getActualPrice());
        payOrder.setPayStatus(0);
        payOrder.setCreatTime(new Date());
        PayOrder payOrder1 = orderDao.save(payOrder);
        System.out.println(JSONObject.toJSONString(payOrder1));

        //保存前端传递过来的sn
        ArrayList<ActivationCode> activationCodes = new ArrayList<>();
        for (String sn : sns) {
            ActivationCode activationCode = new ActivationCode();
            activationCode.setSn(sn);
            activationCode.setProductSeriesId(productSeriesId);
            activationCode.setProductName(productSeries.getChName());

            activationCode.setOrderId(payOrder1.getId());
            activationCode.setType(1);
            activationCodes.add(activationCode);
        }
        activationCodeDao.saveAll(activationCodes);


        Map<Object, Object> pays = new HashMap<>();
        //String pay = alipayService.goPay(outTradeNo, totalAmount, subject);
        Map map = alipayService.createNative(outTradeNo, payBean.getActualPrice());
        return ServerResponse.ok(map);
    }


    /**
     * 查询支付状态
     *
     * @param out_trade_no
     * @return
     */
    @RequestMapping("/queryPayStatus")
    public ServerResponse queryPayStatus(String out_trade_no) {
            // 调用查询接口
            Map<String, String> map = null;
            try {
                map = alipayService.queryPayStatus(out_trade_no);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (map == null ) {// 出错
                return new ServerResponse(401,"查询支付状态异常");
            }
             if (map.get("tradestatus") != null && map.get("tradestatus").equals("WAIT_BUYER_PAY")){
                return new ServerResponse(402,"用户还未支付");
            }
            if (map.get("outtradeno") == null){
                return new ServerResponse(404,"请求出错");
            }
            // 如果成功
            if (map.get("tradestatus") != null && map.get("tradestatus").equals("TRADE_SUCCESS")) {

                PayOrder payOrder = orderDao.findById(out_trade_no);
                payOrder.setPayTime(new Date());
                payOrder.setPayStatus(1);
                payOrder.setCardNo(map.get("tradeNo"));
                PayOrder payOrder1 = orderDao.save(payOrder);

                List<ActivationCode> codeList = activationCodeService.findByOrderId(out_trade_no);
                for (ActivationCode activationCode : codeList) {
                    ProductSeries productSeries = productSeriesDao.findById(activationCode.getProductSeriesId()).get();
                    String code = "TYKJ"+activationCode.getSn()+productSeries.getPrivateKey();
                    for (int i =0; i<7; i++){
                        code = getMD5String(code);
                    }
                    if (code != null){
                        activationCode.setActivationCodeNo(code);
                        activationCode.setActivationTime(new Date());
                        activationCode.setProductSeriesId(productSeries.getId());
                        activationCode.setProductName(productSeries.getChName());
                        

                        //更新产品系列激活次数
                        productSeries.setActivateTimes(productSeries.getActivateTimes()+1);
                        productSeriesDao.save(productSeries);
                        activationCodeDao.save(activationCode);
                    }
                }
                return new ServerResponse(200,"支付成功");
            }
            if (map.get("tradestatus") != null && map.get("tradestatus").equals("TRADE_CLOSED")) {
                return new ServerResponse(403,"未付款交易超时关闭");
            }
            if (map.get("tradestatus") != null && map.get("tradestatus").equals("TRADE_FINISHED")) {
                return new ServerResponse(405,"交易结束");
            }
        return null;
    }

}
