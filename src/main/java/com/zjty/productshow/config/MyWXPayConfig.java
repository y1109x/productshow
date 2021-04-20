package com.zjty.productshow.config;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@ConfigurationProperties(prefix="wx.pay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyWXPayConfig implements WXPayConfig {
    /**
     * appID
     */
    private String appID;

    /**
     * 商户号
     */
    private String mchID = "1607189440";

    /**
     * API 密钥
     */
    private String key;

    /**
     * API证书绝对路径 (本项目放在了 resources/cert/wxpay/apiclient_cert.p12")
     */
    private String certPath;

    /**
     * HTTP(S) 连接超时时间，单位毫秒
     */
    private int httpConnectTimeoutMs = 8000;

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     */
    private int httpReadTimeoutMs = 10000;

    /**
     * 微信支付异步通知地址
     */
    private String payNotifyUrl;


    /**
     * 统一下单url
     */
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/v3/pay/transactions/native";

    /**
     * 获取商户证书内容（这里证书需要到微信商户平台进行下载）
     *
     * @return 商户证书内容
     */
    @Override
    public InputStream getCertStream() {
        InputStream certStream = getClass().getClassLoader().getResourceAsStream(certPath);
        return certStream;
    }
}

