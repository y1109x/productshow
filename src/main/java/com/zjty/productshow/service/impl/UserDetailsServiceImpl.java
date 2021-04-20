//package com.zjty.productshow.service.impl;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//
///**
// * 自定义用户详情Service
// * @author HuangXiahao
// * @version V1.0
// * @class UserDetailsServiceImpl
// * @packageName com.example.personnelmanager.common.config
// * @data 2020/5/20
// **/
//@Service
//@Slf4j
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserDe userServer;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //如果用户存在数据库中，则直接返回用户
//        //如果用户不存在数据库中则为用户新建一个用户，并返回
//        UserDetail userDetail = userServer.selectByUserName(username);
//        if (userDetail==null){
//            throw new UsernameNotFoundException("用户名或者密码错误");
//        }
//        return  userDetail;
//    }
//
//    public UserDetails loadUserByUsername(String username,String phone) throws UsernameNotFoundException {
//        //如果用户存在数据库中，则直接返回用户
//        //如果用户不存在数据库中则为用户新建一个用户，并返回
//        UserDetail userDetail = userServer.selectByUserName(username);
//        if (userDetail==null){
//            User user = new User();
//            user.setUsername(username);
//            user.setCreateDate(Instant.now());
//            Enterprise enterprise = new Enterprise();
//            enterprise.setEnterpriseId(1L);
//            user.setEnterprise(enterprise);
//            user.setName(username);
//            user.setPassword(passwordEncoder.encode("qwer1234"));
//            user.setPhone(phone);
//            log.info("用户为:"+user);
//            User user1 = userServer.addEntity(user);
//            userDetail = new UserDetail();
//            BeanUtils.copyProperties(user1,userDetail);
//            return userDetail;
//        }
//        return  userDetail;
//    }
//}
