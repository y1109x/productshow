package com.zjty.productshow.controller;

import com.sun.istack.NotNull;
import com.zjty.productshow.entity.Card;
import com.zjty.productshow.entity.ServerResponse;
import com.zjty.productshow.entity.vo.CardSelectVo;
import com.zjty.productshow.entity.vo.GenerateCardVo;
import com.zjty.productshow.entity.vo.PageResponse;
import com.zjty.productshow.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/card")
@Api(value = "卡号管理接口",description = "卡号的生成、查看")
public class CardController {
    @Autowired
    private CardService cardService;

    @ApiOperation("生成卡")
    @PostMapping("/generate")
    public ServerResponse generate(@RequestBody @NotNull GenerateCardVo generateCardVo){
        Card card = cardService.generate(generateCardVo);
        if (card != null){
            return ServerResponse.ok(card);
        }
        return null;
    }


    @ApiOperation("根据生成时间、产品系列名称、申请人 多条件查询卡号信息")
    @PostMapping("/cardList")
    public ServerResponse selectVo(@RequestBody @NotNull CardSelectVo cardSelectVo){
        PageResponse<Card> pageResponse = cardService.select(cardSelectVo);
        if (pageResponse!= null){
            return ServerResponse.ok(pageResponse);
        }
        return ServerResponse.error();
    }


    @ApiOperation("获取所有人列表")
    @GetMapping("/getApplicant")
    public ServerResponse getApplicant(){
        List<String> applicant = cardService.getApplicant();
        return ServerResponse.ok(applicant);
    }

}
