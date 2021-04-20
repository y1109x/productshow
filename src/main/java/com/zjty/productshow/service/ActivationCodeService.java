package com.zjty.productshow.service;

import com.zjty.productshow.entity.ActivationCode;
import com.zjty.productshow.entity.vo.ActivationCodeBean;
import com.zjty.productshow.entity.vo.CodeSelectVo;
import com.zjty.productshow.entity.vo.PageResponse;

import java.util.List;

public interface ActivationCodeService {

    List<ActivationCodeBean> findBySn(String sn);

    PageResponse<ActivationCode> selectByConditions(CodeSelectVo codeSelectVo);

    List<ActivationCode> findByOrderId(String orderId);
}
