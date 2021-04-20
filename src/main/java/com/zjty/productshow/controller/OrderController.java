
package com.zjty.productshow.controller;

import com.sun.istack.NotNull;

import com.zjty.productshow.dao.ActivationCodeDao;
import com.zjty.productshow.dao.OrderDao;
import com.zjty.productshow.entity.ActivationCode;
import com.zjty.productshow.entity.ServerResponse;
import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.PayBean;
import com.zjty.productshow.service.PayService;
import com.zjty.productshow.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping(value = "/pay")
@Api(value = "订单管理接口", description = "订单管理接口")
public class OrderController {

    @Autowired
    private PayService payService;
    @Autowired
    private ActivationCodeDao activationCodeDao;

    @ApiOperation("卡支付接口")
    @PostMapping(value = "/front/cardPay")
    public ServerResponse cardPay(@NotNull @RequestBody PayBean payBean){
        ServerResponse serverResponse = payService.cardPay(payBean);
        return serverResponse;
    }


    @ApiOperation("前台页面 根据订单id 查看激活码")
    @GetMapping("/front/{orderId}")
    public ServerResponse selectProjectById(@PathVariable("orderId") @NotNull String orderId){
        List<ActivationCodeBean> codeBeans = payService.findByOrderId(orderId);
        if(codeBeans != null){
            return ServerResponse.ok(codeBeans);
        }
        return ServerResponse.error("没有此订单");
    }

/*

    //生成微信支付二维码接口
    //微信支付
    @GetMapping("/createNative")
    public ServerResponse wxPay(@NotNull @RequestBody PayBean payBean) {
        //返回相关信息  二维码地址和其他信息
        Map map =wechatService .createNative(orderNo);
        //自定义封装的统一返回结果（可查看博客获取）
        return ServerResponse.ok(map);
    }

*/



}

