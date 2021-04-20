package com.zjty.productshow.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    //@Value("/Users/czq/Desktop/test/")
/*   @Value("/home/tykj/Desktop/icon/")
    private String path;*/


    @Value("/data/apps/")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将/static/**访问映射到classpath:/mystatic/
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + path);

    }
}

