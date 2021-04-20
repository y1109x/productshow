package com.zjty.productshow.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.wenhao.jpa.Specifications;
import com.zjty.productshow.dao.ActivationCodeDao;
import com.zjty.productshow.entity.ActivationCode;
import com.zjty.productshow.entity.Card;
import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.CodeSelectVo;
import com.zjty.productshow.entity.vo.PageResponse;
import com.zjty.productshow.service.ActivationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {
    @Autowired
    private ActivationCodeDao activationCodeDao;


    @Override
    public List<ActivationCodeBean> findBySn(String sn) {

        ArrayList<ActivationCodeBean> codeBeans = new ArrayList<>();

        List<ActivationCode> codeList = activationCodeDao.findBySn(sn);

        if (codeList!=null){
            for (ActivationCode activationCode : codeList) {
                ActivationCodeBean activationCodeBean = new ActivationCodeBean();
                if (activationCode.getActivationCodeNo()!=null && activationCode.getActivationCodeNo().trim().length()!=0) {
                    activationCodeBean.setActivationCodeNo(activationCode.getActivationCodeNo());
                    activationCodeBean.setProductSeriesName(activationCode.getProductName());
                    codeBeans.add(activationCodeBean);
                }

            }
            return codeBeans;
        }
        return null;
    }

    public PageResponse<ActivationCode> selectByConditions(CodeSelectVo codeSelectVo){
        Date startTime = codeSelectVo.getStartTime();
        Date finishTime = codeSelectVo.getFinishTime();
        String sn = codeSelectVo.getSn();
        String productName = codeSelectVo.getProductName();
        int page = codeSelectVo.getPage();
        int size = codeSelectVo.getSize();
        Specification<ActivationCode> specification = Specifications.<ActivationCode>and()
                .gt(startTime!=null,"activationTime",startTime)
                .lt(finishTime!=null,"activationTime",finishTime)
                .like(!StringUtils.isEmpty(sn), "sn", "%" + sn + "%")
                .like(!StringUtils.isEmpty(productName), "productName", "%" + productName + "%")
                .build();
        if (page <= 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "activationTime");
        List<ActivationCode> activationCodes = activationCodeDao.findAll(specification, pageable).getContent();
        long count = activationCodeDao.count(specification);
        return new PageResponse<>(page, size, count, activationCodes);

    }

    @Override
    public List<ActivationCode> findByOrderId(String orderId) {
        List<ActivationCode> codeList = activationCodeDao.findByOrderId(orderId);
        if (codeList != null){
            return codeList;
        }
        return null;
    }
}
