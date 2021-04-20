package com.zjty.productshow.service;

import com.zjty.productshow.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    Product upload(MultipartFile file, Product product) throws IOException;

    Integer download(Integer productId);


}
