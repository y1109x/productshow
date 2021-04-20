package com.zjty.productshow.controller;

import com.sun.istack.NotNull;
import com.zjty.productshow.entity.Product;
import com.zjty.productshow.entity.ServerResponse;
import com.zjty.productshow.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/product")
@Api(value = "产品版本接口",description = "产品版本的上传 下载")
public class ProductController {
    @Autowired
    private ProductService productService;


    @ApiOperation("上传产品版本安装包")
    @PostMapping("/upload")
    public ServerResponse uploadImage(@NotNull @RequestParam("file") MultipartFile file, Product product) throws IOException {
        return ServerResponse.ok(productService.upload(file, product));
    }

    @ApiOperation("下载产品")
    @GetMapping("/front/down/{productId}")
    public ServerResponse uploadImage(@NotNull @PathVariable Integer productId) throws IOException {
        Integer downloadTimes = productService.download(productId);
        System.out.println(downloadTimes);
        if (downloadTimes != null){
            return ServerResponse.ok(200);
        }
        return ServerResponse.error();
    }



}
