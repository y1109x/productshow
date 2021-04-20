//package com.zjty.productshow.util;
//
//import com.zjty.productshow.entity.UserDetail;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
///**
// * 用户实体相关工具类
// *
// * @author HuangXiahao
// * @version V1.0
// * @class AuthenticationUtils
// * @packageName com.example.personnelmanager.common.utils
// * @data 2020/5/14
// **/
//public class AuthenticationUtils {
//
//    public static String ANONYMOUS_USER_STRING = "anonymousUser";
//    /***
//     * 获取当前登录用户
//     *
//     * @Return : com.example.personnelmanager.entity.UserDetail
//    */
//    public static UserDetail getAuthentication(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (ANONYMOUS_USER_STRING.equals(authentication.getPrincipal())){
//            throw new UsernameNotFoundException("当前无登录用户");
//        }
//        return (UserDetail) authentication.getPrincipal();
//    }
//
//}
