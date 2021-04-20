package com.zjty.productshow.service.impl;

import com.alibaba.fastjson.JSON;
import com.zjty.productshow.dao.ProductDao;
import com.zjty.productshow.dao.ProductSeriesDao;
import com.zjty.productshow.entity.Product;
import com.zjty.productshow.entity.ProductSeries;
import com.zjty.productshow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("/data/apps/")
    private String path;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductSeriesDao productSeriesDao;

    @Override
    public Product upload(MultipartFile file, Product product) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            //获取文件后缀
            String suffixName = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));

            product.setProductName(file.getOriginalFilename());
            //获取文件名
            String fileName1 = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("\\") + 1);
            String fileNewName = fileName1.substring(0, fileName1.lastIndexOf("."));

            //重新生成文件名
            fileName = UUID.randomUUID() + suffixName;

            file.transferTo(new File(path + fileName));
            product.setUrl(path + fileName);
        }
        product.setStatus(1);
        return product;
    }

    @Override
    public Integer download(Integer productId) {
        Product product = productDao.findByIdAndStatus(productId, 1);
        product.setDownloadTimes(product.getDownloadTimes()+1);
        Product product1 = productDao.save(product);

        ProductSeries productSeries = productSeriesDao.findByIdAndStatus(product.getProductSeriesId(), 1);
        productSeries.setTotalDownloads(productSeries.getTotalDownloads()+1);
        productSeriesDao.save(productSeries);
        return product1.getDownloadTimes();
    }
}
