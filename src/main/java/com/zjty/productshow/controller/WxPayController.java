package com.zjty.productshow.controller;
import com.sun.istack.NotNull;
import com.zjty.productshow.config.MyWXPayConfig;
import com.zjty.productshow.dao.ActivationCodeDao;
import com.zjty.productshow.dao.OrderDao;
import com.zjty.productshow.dao.ProductSeriesDao;
import com.zjty.productshow.entity.ActivationCode;
import com.zjty.productshow.entity.PayOrder;
import com.zjty.productshow.entity.ProductSeries;
import com.zjty.productshow.entity.ServerResponse;
import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.PayBean;

import com.zjty.productshow.service.ActivationCodeService;
import com.zjty.productshow.service.WxPayService;
import com.zjty.productshow.wxUtil.WXPayUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.*;

import static com.zjty.productshow.service.impl.PayServiceImpl.getMD5String;

@RestController
@RequestMapping("/wxPay/front")
public class WxPayController{

    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private ActivationCodeService activationCodeService;
    @Autowired
    private ProductSeriesDao productSeriesDao;
    @Autowired
    private ActivationCodeDao activationCodeDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MyWXPayConfig wxPayConfig;


    @PostMapping("/pay")
    @ApiOperation("前台页面 微信支付 生成二维码")
    public ServerResponse wxPay(@NotNull @RequestBody PayBean payBean, HttpServletResponse response){
        ProductSeries productSeries = productSeriesDao.findById(payBean.getProductSeriesId()).get();
        if (productSeries.getStatus() == 0){
            return ServerResponse.error(400,"抱歉该产品已下架！");
        }


        String body = "套餐描述";
        Map<Object, Object> pays = new HashMap<>();
        //pays = wxPayService.save(payBean, body);
        pays.put("url_code", "http://www.zjtys.com.cn/zjty/view/home/index.html");
        pays.put("orderId", "202103231123551616469835986");

        if(pays != null){
            return ServerResponse.ok(pays);
        }else {
            return ServerResponse.error("下单失败！");
        }

    }


    @RequestMapping("/callback")
    public void OrderCallBack(HttpServletRequest request, HttpServletResponse response){
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            // BufferedReader是包装设计模式，性能更高
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            bufferedReader.close();
            inputStream.close();
            Map<String,String> callBackMap = WXPayUtils.xmlToMap(stringBuffer.toString());
            System.out.println(callBackMap.toString());

            SortedMap<String, String> sortedMap = WXPayUtils.getSortedMap(callBackMap);
            // 校验签名是否正确
            if(WXPayUtils.isCorrectSign(sortedMap, wxPayConfig.getKey())){
                System.out.println("签名校验成功！");
                // 更新订单状态
                if("SUCCESS".equals(sortedMap.get("result_code"))){
                    String outTradeNo = sortedMap.get("out_trade_no"); // 流水号
                    Integer productSeriesId = Integer.valueOf(sortedMap.get("product_id")); // 产品id

                   //保存订单状态
                    PayOrder payOrder = orderDao.findById(outTradeNo);
                    payOrder.setPayTime(new Date());
                    payOrder.setPayStatus(1);
                    PayOrder payOrder1 = orderDao.save(payOrder);

                    //
                    ProductSeries productSeries = productSeriesDao.findById(productSeriesId).get();

                    List<ActivationCode> codeList = activationCodeService.findByOrderId(outTradeNo);
                    for (ActivationCode activationCode : codeList) {
                        String code = "TYKJ"+activationCode.getSn()+productSeries.getPrivateKey();
                        for (int i =0; i<7; i++){
                            code = getMD5String(code);
                        }
                        if (code != null){
                            activationCode.setActivationCodeNo(code);

                            //更新产品系列激活次数
                            productSeries.setActivateTimes(productSeries.getActivateTimes()+1);
                        }
                    }
                    productSeriesDao.save(productSeries);
                    activationCodeDao.saveAll(codeList);
                    response.setContentType("text/xml");
                    response.getWriter().println("success");
                    return;
                }

                // 未成功，就都处理为失败订单
                response.setContentType("text/html");
                response.getWriter().println("fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @ApiOperation("前台页面 根据订单id 查看激活码")
    @GetMapping("/getDetail/{orderId}")
    public ServerResponse selectFrontProjectById(@PathVariable @NotNull String  orderId){
        ArrayList<ActivationCodeBean> codeBeans = new ArrayList<>();

        List<ActivationCode> codeList = activationCodeDao.findByOrderId(orderId);
        for (ActivationCode activationCode : codeList) {
            ActivationCodeBean activationCodeBean = new ActivationCodeBean();
            //查询购买的产品系列名称
            ProductSeries productSeries = productSeriesDao.findById(activationCode.getProductSeriesId()).get();
            if (productSeries!=null){
                activationCodeBean.setProductSeriesName(productSeries.getChName());
                activationCodeBean.setActivationCodeNo(activationCode.getActivationCodeNo());
                activationCodeBean.setSn(activationCode.getSn());
                activationCodeBean.setPayStatus(1);
            }
            codeBeans.add(activationCodeBean);
        }
        return ServerResponse.ok(codeBeans);
    }


}
