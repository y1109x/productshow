package com.zjty.productshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ProductshowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductshowApplication.class, args);
    }

}
