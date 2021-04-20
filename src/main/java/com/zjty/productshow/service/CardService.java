package com.zjty.productshow.service;

import com.zjty.productshow.entity.Card;
import com.zjty.productshow.entity.vo.CardSelectVo;
import com.zjty.productshow.entity.vo.GenerateCardVo;
import com.zjty.productshow.entity.vo.PageResponse;

import java.util.List;

public interface CardService {

    PageResponse<Card> select(CardSelectVo cardSelectVo);

    Card generate(GenerateCardVo generateCardVo);

    List<String> getApplicant();


}
