package com.zjty.productshow.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;


@Data

public class ServerResponse<T> implements Serializable {
    /**
     * 错误码
     */
    @JSONField(ordinal = 1)
    private Integer code;

    /**
     * 提示信息
     */
    @JSONField(ordinal = 2)
    private String msg;

    /**
     * 具体的内容
     */
    @JSONField(ordinal = 3)
    private T data;

    public ServerResponse(Integer code, String msg, T t){
        this.code=code;
        this.data=t;
        this.msg=msg;
    }

    public ServerResponse(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }


    public static <T> ServerResponse error(int status,String data){
        return new ServerResponse(status,"请求失败",data);
    }
    public static <T> ServerResponse error(Object data){
        return new ServerResponse(400,"请求失败",data);
    }
    public static <T> ServerResponse noAuthority(){
        return new ServerResponse(403,"没有权限","");
    }
    public static <T> ServerResponse badRequest(){
        return new ServerResponse(400,"请求失败","");
    }
    public static <T> ServerResponse<T> ok(T data) {
        return new ServerResponse(200,"成功",data);
    }
    public static <T> ServerResponse error(){
        return new ServerResponse(500,"服务器出错","");
    }
}
