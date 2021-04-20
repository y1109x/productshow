//package com.zjty.productshow.exception;
//
//import com.zjty.productshow.entity.ServerResponse;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.hibernate.JDBCException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageConversionException;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.io.IOException;
//
///**
// * 错误处理类
// * 所有的报错信息都会通过本层的方法向外界返回
// *
// * @author HuangXiahao
// * @version V1.0
// * @class ErrorController
// * @packageName com.example.demo.controller
// * @description
// * @data 2020/4/7
// **/
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    protected final Log logger = LogFactory.getLog(getClass());
//
//    /**
//     * 报错信息都会通过这个方法处理并通过统一的返回方式进行返回
//     * @param e  报错信息
//     * @Return : com.example.demo.entity.ServerResponse
//     * @Author : HuangXiahao
//     * @Date : 2020/4/10 15:14
//    */
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ServerResponse errorMessage(Exception e){
//        logger.error(e.getMessage(), e);
//        return new ServerResponse(500, "测试");
//    }
//
//    /**
//     * 数据合法性验证报错会通过这个方法处理并通过统一的返回方式进行返回
//     * @param e  报错信息
//     * @Return : com.example.demo.entity.ServerResponse
//     * @Author : HuangXiahao
//     * @Date : 2020/4/10 15:14
//     */
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(BindException.class)
//    public ServerResponse errorMessage(BindException e){
//        logger.error(e.getMessage(), e);
//        return new ServerResponse(500, e.getAllErrors().get(0).getDefaultMessage());
//    }
//
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ServerResponse errorMessage(MethodArgumentNotValidException e){
//        logger.error(e.getMessage(), e);
//        return new ServerResponse(500, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//    }
//
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(JDBCException.class)
//    public ServerResponse errorMessage(JDBCException e){
//        logger.error(e.getMessage(), e);
//        return new ServerResponse(500, "对数据库时出现错误");
//    }
//
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ServerResponse errorMessage(HttpMessageNotReadableException e){
//        logger.error(e.getMessage(), e);
//        return new ServerResponse(500, "上传内容格式不正确"+e.getMessage());
//    }
//
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(HttpMessageConversionException.class)
//    public ServerResponse errorMessage(HttpMessageConversionException e){
//        logger.error(e.getMessage(), e);
//        return new ServerResponse(500, "上传内容格式不正确"+e.getMessage());
//    }
//
//    @ResponseBody
//    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(IOException.class)
//    public ServerResponse errorMessage(IOException e){
//        logger.error(e.getMessage(), e);
//        return new ServerResponse(500, "文件上传失败");
//    }
//}
//
