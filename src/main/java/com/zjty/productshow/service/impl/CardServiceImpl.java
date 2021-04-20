package com.zjty.productshow.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.wenhao.jpa.Specifications;
import com.zjty.productshow.dao.CardDao;
import com.zjty.productshow.dao.ProductSeriesDao;
import com.zjty.productshow.dao.UserInfoDao;
import com.zjty.productshow.entity.Card;
import com.zjty.productshow.entity.ProductSeries;
import com.zjty.productshow.entity.UserInfo;
import com.zjty.productshow.entity.vo.CardSelectVo;
import com.zjty.productshow.entity.vo.GenerateCardVo;
import com.zjty.productshow.entity.vo.PageResponse;
import com.zjty.productshow.service.CardService;
import com.zjty.productshow.util.UtilId;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.alibaba.druid.util.StringUtils;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardDao cardDao;
    @Autowired
    private ProductSeriesDao productSeriesDao;
    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 多条件查询 卡号信息
     *
     * @param cardSelectVo
     * @return
     */
    public PageResponse<Card> select(CardSelectVo cardSelectVo) {
        Date startTime = cardSelectVo.getStartTime();
        Date finishTime = cardSelectVo.getFinishTime();
        String productSeriesName = cardSelectVo.getProductSeriesName();
        String applicant = cardSelectVo.getApplicant();
        int page = cardSelectVo.getPage();
        int size = cardSelectVo.getSize();
        Specification<Card> specification = Specifications.<Card>and()
                /*.eq(startTime!=null, "status",status);*/
                .gt(startTime != null, "applicationTime", startTime)
                .lt(finishTime != null, "applicationTime", finishTime)
                .like(!StringUtils.isEmpty(productSeriesName), "productSeriesName", "%" + productSeriesName + "%")
                .like(!StringUtils.isEmpty(applicant), "applicant", "%" + applicant + "%")
                .build();
        if (page <= 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "applicationTime");
        List<Card> cardList = cardDao.findAll(specification, pageable).getContent();
        long count = cardDao.count(specification);
        return new PageResponse<>(page, size, count, cardList);

    }


    /**
     * 生成卡
     *
     * @param generateCardVo
     * @return
     */
    @Override
    public Card generate(GenerateCardVo generateCardVo) {

        Card card = new Card();


        BeanUtils.copyProperties(generateCardVo, card);
        card.setOperator(card.getOperator());

        //卡号

        if (card.getType() != null) {
            if (card.getType() == 0) {
                ProductSeries productSeries = productSeriesDao.findById(generateCardVo.getProductSeriesId()).get();
                if (productSeries != null) {
                    card.setProductSeriesName(productSeries.getChName());
                }
            } else if (card.getType() == 1) {
                card.setProductSeriesName("通用");
            }
        }

        card.setRemainingTimes(card.getOriginalTimes());



        card.setCardNo(UtilId.getGUID());

        card.setApplicationTime(new Date());

        Card card1 = cardDao.save(card);

        return card1;
    }


    @Override
    public List<String> getApplicant() {
        ArrayList<String> list = new ArrayList<>();
        List<UserInfo> all = userInfoDao.findAll();
        for (UserInfo userInfo : all) {
            list.add(userInfo.getUsername());
        }
        if (list != null){
            return list;
        }
        return null;

/*
        HashMap<String, Object> map = new HashMap<>();
        //准备转成jsonObject
        map.put("tableName", "personnelManager_people_ex");  //将对方需要传的必须参数进行添加到map集合

        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 要调用的接口方法
        String url = "http://192.168.100.246:8090/opt/data/query"; //请求对方的路径地址
        HttpPost post = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            StringEntity s = new StringEntity(JSONObject.toJSONString(map)); //JSONObject.toJSONString(map)将map集合转成JSONObject
            s.setContentEncoding("UTF-8");  //字符编码
            s.setContentType("application/json");
            post.setEntity(s);

            post.addHeader("jwt", "1");
            HttpResponse res = httpClient.execute(post);
            String response = EntityUtils.toString(res.getEntity()); //将请求回来的json转成String
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                jsonObject = JSONObject.parseObject(response);
                if (jsonObject != null) {
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (Object datum : data) {
                        list.add(JSONObject.parseObject(datum
                        .toString()).getString("name"));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;*/
    }


}
