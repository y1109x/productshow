package com.zjty.productshow.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;

/**
 * 描述：标准返回类
 *
 * @author HuangXiahao
 * @version V1.0
 * @class ResultObj
 * @packageName com.example.demo.entity
 * @description
 * @data 2020/4/7
 **/

@Data
public class ResultObj {

    public ResultObj(String msg) {
        this.result =  true;
        this.msg = msg;
    }

    public ResultObj(Boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    //返回内容
    @ApiModelProperty(value = "返回内容")
    private Boolean result;

    //提示信息
    @ApiModelProperty(value = "提示信息")
    private String msg;


}
