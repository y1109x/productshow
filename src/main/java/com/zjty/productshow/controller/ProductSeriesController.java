package com.zjty.productshow.controller;

import com.alibaba.fastjson.JSON;
import com.sun.istack.NotNull;
import com.zjty.productshow.dao.ProductSeriesDao;
import com.zjty.productshow.entity.ProductSeries;
import com.zjty.productshow.entity.ServerResponse;
import com.zjty.productshow.entity.vo.*;
import com.zjty.productshow.service.ProductSeriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/projectSeries")
@Api(value = "产品管理")
public class ProductSeriesController {

    @Autowired
    private ProductSeriesService productSeriesService;
    @Autowired
    private ProductSeriesDao productSeriesDao;


    @ApiOperation("后台 生成产品系列的私有key")
    @GetMapping("/newKey")
    public ServerResponse newKey(){
        UUID uuid = UUID.randomUUID();
        if (uuid!= null){
            return ServerResponse.ok(uuid.toString());
        }
        return ServerResponse.error();
    }


    @ApiOperation("后台 新增产品系列")
    @PutMapping("/addProductSeries")
    public ServerResponse save(@NotNull @RequestBody ProductSeriesVo productSeriesVo){
        ProductSeriesVo productSeriesVo1 = productSeriesService.save(productSeriesVo);
        if (productSeriesVo1 != null) {
            return ServerResponse.ok(productSeriesVo1);
        }
        return ServerResponse.error("新增产品错误");
    }


    @ApiOperation("后台页面 分页显示所有商品")
    @PostMapping("/selectAll")
    public ServerResponse selectAll(@NotNull @RequestBody SelectAllVo selectAllVo){
        PageResponse<ProductSeries> pageResponse = productSeriesService.findAll(selectAllVo);
        if (pageResponse!=null){
            return ServerResponse.ok(pageResponse);
        }
        return null;
    }


    @ApiOperation("后台 上、下架产品系列")
    @PutMapping("/onShelf")
    public ServerResponse onShelf(@NotNull @RequestBody SelectShelfVo selectShelfVo){
        List<ProductSeries> productSeriesList = productSeriesService.operateShelf(selectShelfVo);
        if (productSeriesList!=null){
            return ServerResponse.ok(productSeriesList);
        }
        return ServerResponse.error();
    }


    @ApiOperation("显示全部已上架的产品系列")
    @GetMapping("/findByStatus")
    public ServerResponse findByStatus(){
        List<ProductSeries> productSeriesList = productSeriesService.findByStatus(1);
        if (productSeriesList!= null){
            return ServerResponse.ok(productSeriesList);
        }
        return ServerResponse.error();
    }


    @ApiOperation("前台页面 产品系列展示")
    @GetMapping("/front/show")
    public ServerResponse show(){
        List<ProductSeries> productSeriesList = productSeriesService.findByStatus(1);
        if (productSeriesList!=null){
            System.out.println(productSeriesList);
            return ServerResponse.ok(productSeriesList);
        }
        return null;
    }



    @ApiOperation("后台页面 获取所有上线产品系列")
    @GetMapping("/getProductShow")
    public ServerResponse getProductShow(){
        ArrayList<ProductSeriesVo1> productSeriesVo1s = new ArrayList<ProductSeriesVo1>();
        List<ProductSeries> productSeriesList = productSeriesService.findByStatus(1);


        if (productSeriesList!=null) {
            for (ProductSeries productSeries : productSeriesList) {
                ProductSeriesVo1 productSeriesVo1 = new ProductSeriesVo1();
                productSeriesVo1.setChName(productSeries.getChName());
                productSeriesVo1.setId(productSeries.getId());
                System.out.println("==="+JSON.toJSONString(productSeriesVo1));

                productSeriesVo1s.add(productSeriesVo1);
            }
        }
        if (productSeriesVo1s!=null){
            return ServerResponse.ok(productSeriesVo1s);
        }
        return ServerResponse.error("没有上线的产品");
    }

    @ApiOperation("前台页面 用户根据产品id 查看详情")
    @GetMapping("/front/getDetail/{productSeriesId}")
    public ServerResponse selectFrontProjectById(@PathVariable("productSeriesId") @NotNull Integer id){
        ProductSeries productSeries = productSeriesDao.findById(id).get();
        ProductSeriesVo productSeriesVo = productSeriesService.findById(id);
        if (productSeries.getStatus()==0){
            return ServerResponse.error(401,"抱歉该产品已下架");
        }

        if (productSeriesVo != null && productSeries.getStatus()==1 ){
            return ServerResponse.ok(productSeriesVo);
        }
        return ServerResponse.error("id不存在");
    }

    @ApiOperation("后台台页面 用户根据产品id 查看详情")
    @GetMapping("/getDetail/{productSeriesId}")
    public ServerResponse selectProjectById(@PathVariable("productSeriesId") @NotNull Integer id){
        ProductSeriesVo productSeriesVo = productSeriesService.findById(id);
        if (productSeriesVo != null){
            return ServerResponse.ok(productSeriesVo);
        }
        return ServerResponse.error("id不存在");
    }


}
