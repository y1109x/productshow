package com.zjty.productshow.wxUtil;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    public static String doPost(String url, String data, int timeOut){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 超时设置
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeOut) // 连接超时
                .setConnectionRequestTimeout(timeOut) // 请求超时
                .setSocketTimeout(timeOut)
                .setRedirectsEnabled(true) // 允许自动重定向
                .build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type","text/html; chartset=UTF-8");

        if(data != null && data instanceof String){ // 使用字符串传参
            StringEntity stringEntity = new StringEntity(data,"UTF-8");
            httpPost.setEntity(stringEntity);
        }

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                String result = EntityUtils.toString(httpEntity);
                return result;
            }
        } catch (Exception e){
            e.printStackTrace();
        }  finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
