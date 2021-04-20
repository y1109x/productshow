package com.zjty.productshow.controller;

import com.sun.istack.NotNull;
import com.zjty.productshow.entity.ActivationCode;
import com.zjty.productshow.entity.ServerResponse;

import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.CodeSelectVo;
import com.zjty.productshow.entity.vo.PageResponse;
import com.zjty.productshow.service.ActivationCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/code")
@Api(value = "激活码管理接口",description = "激活码的生成、查看")
public class ActivationCodeController {
    @Autowired
    private ActivationCodeService activationCodeService;


    @ApiOperation("后台 根据生成时间、产品系列名称、申请人查询卡号信息")
    @PostMapping("/codeList")
    public ServerResponse selectByConditions(@RequestBody @NotNull CodeSelectVo codeSelectVo){
        PageResponse<ActivationCode> pageResponse = activationCodeService.selectByConditions(codeSelectVo);
        if (pageResponse!= null){
            return ServerResponse.ok(pageResponse);
        }
        return ServerResponse.error();
    }


    @ApiOperation("前台 根据SN查询激活码")
    @GetMapping("/front/{sn}")
    public ServerResponse selectProjectById(@PathVariable("sn") @NotNull String  sn){
        List<ActivationCodeBean> codeBeans = activationCodeService.findBySn(sn);
        if (codeBeans != null){
            return ServerResponse.ok(codeBeans);
        }
    return ServerResponse.error("请输入正确的sn");

    }








}
