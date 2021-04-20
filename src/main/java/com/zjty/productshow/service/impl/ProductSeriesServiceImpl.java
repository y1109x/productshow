package com.zjty.productshow.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wenhao.jpa.Specifications;
import com.zjty.productshow.dao.ImageDao;
import com.zjty.productshow.dao.ProductDao;
import com.zjty.productshow.dao.ProductSeriesDao;
import com.zjty.productshow.entity.Image;
import com.zjty.productshow.entity.Product;
import com.zjty.productshow.entity.ProductSeries;
import com.zjty.productshow.entity.vo.PageResponse;
import com.zjty.productshow.entity.vo.ProductSeriesVo;
import com.zjty.productshow.entity.vo.SelectAllVo;
import com.zjty.productshow.entity.vo.SelectShelfVo;

import com.zjty.productshow.service.ProductSeriesService;
import org.hibernate.resource.jdbc.spi.JdbcSessionOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductSeriesServiceImpl implements ProductSeriesService {

    @Autowired
    private ProductSeriesDao productSeriesDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ImageDao imageDao;


    /**
     * 后台 新增产品
     * @param productSeriesvo
     * @return
     */
    @Override
    public ProductSeriesVo save(ProductSeriesVo productSeriesvo) {
        ProductSeriesVo productSeriesVo1 = new ProductSeriesVo();
        ProductSeries productSeries = productSeriesvo.getProductSeries();
        productSeries.setStatus(0);
        productSeries.setUpdateTime(new Date());
        ProductSeries productSeries1 = productSeriesDao.save(productSeries);

        /*
        *先删除之前的版本 再保存版本
        */
        List<Product> ProductSeriesList = productDao.findAllByProductSeriesIdAndStatus(productSeries.getId(), 1);
        for (Product product : ProductSeriesList) {
            productDao.delete(product);
        }

        List<Product> products = productSeriesvo.getProducts();
        products.forEach(product -> {
            product.setProductSeriesId(productSeries1.getId());
            System.out.println("====product:"+JSON.toJSONString(product.getUrl()));
            productDao.save(product);
        });

        /*
         *删除之前的图片
         */
        List<Image> imageList = imageDao.findAllByProductSeriesId(productSeries.getId());
        for (Image image : imageList) {
            imageDao.delete(image);
        }


        /*
         *保存图片
         */

        List<Image> images = productSeriesvo.getImages();
        System.out.println("======images:"+ JSON.toJSONString(images));

        images.forEach(image -> {
            image.setProductSeriesId(productSeries1.getId());
            imageDao.save(image);
        });


        productSeriesVo1.setImages(images);
        productSeriesVo1.setProductSeries(productSeries1);
        System.out.println("======="+images.toArray());
        productSeriesVo1.setProducts(products);
        return productSeriesvo;
    }

    /**
     * 后台 分页多条件查询产品系列
     * @param selectAllVo
     * @return
     */
    @Override
    public PageResponse<ProductSeries> findAll(SelectAllVo selectAllVo) {
        int page = selectAllVo.getPage();
        int size = selectAllVo.getSize();
        String chName = selectAllVo.getChName();
        Integer status = selectAllVo.getStatus();
        Specification<ProductSeries> specification = Specifications.<ProductSeries>and()
                .like(!StringUtils.isEmpty(chName), "chName", "%" + chName + "%")
                .eq(!StringUtils.isEmpty(status),"status", status)
                .build();
        if (page <= 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "updateTime");
        List<ProductSeries> productSeriesList = productSeriesDao.findAll(specification, pageable).getContent();
        long count = productSeriesDao.count(specification);
        return new PageResponse<>(page, size, count, productSeriesList);
    }


    /**
     * 后台 对商品系列进行批量 上架、下架处理
     * @param selectShelfVo
     * @return
     */
    @Override
    public List<ProductSeries> operateShelf(SelectShelfVo selectShelfVo) {
        ArrayList<ProductSeries> productSeriesList = new ArrayList<>();
        List<Integer> productSeriesIds = selectShelfVo.getProductSeriesIds();
        if (productSeriesIds != null){
            for (Integer productSeriesId : productSeriesIds) {
                ProductSeries productSeries = productSeriesDao.findById(productSeriesId).get();
                if (productSeries != null){
                    productSeries.setStatus(selectShelfVo.getType());
                    productSeries.setUpdateTime(new Date());
                    productSeriesDao.save(productSeries);
                    productSeriesList.add(productSeries);
                }
            }
            return productSeriesList;
        }
        return null;
    }

    /**
     * 前后台 根据产品系列id 查看详情
     * @param id
     * @return
     */
    @Override
    public ProductSeriesVo findById(Integer id) {
        ProductSeriesVo productSeriesVo = new ProductSeriesVo();
        ProductSeries productSeries = productSeriesDao.findById(id).get();

        if (productSeries!=null){


            productSeriesVo.setProductSeries(productSeries);

            //获取该产品系列的全部版本产品
            List<Product> productList = productDao.findAllByProductSeriesIdAndStatus(productSeries.getId(), 1);
            productSeriesVo.setProducts(productList);

           //获取该产品系列的展示图片
            List<Image> images = imageDao.findAllByProductSeriesId(productSeries.getId());

            productSeriesVo.setImages(images);
            return productSeriesVo;
        }
        return null;
    }

    @Override
    public List<ProductSeries> findByStatus(Integer status) {
        return productSeriesDao.findByStatus(1);
    }


}
