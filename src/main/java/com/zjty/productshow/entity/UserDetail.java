//package com.zjty.productshow.entity;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.validation.constraints.NotNull;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * 描述: 用户类
// * 继承了SpringSecurity的UserDtails
// * 用于SpringSecurity框架的用户数据传输
// *
// * @author HuangXiahao
// * @version V1.0
// * @class UserDetailVo
// * @packageName com.example.personnelmanager.entity.vo
// * @data 2020/5/14
// **/
//@Data
//public class UserDetail implements UserDetails {
//
//    private String username;
//
//    private String password;
//
//    private String name;
//
//    private String phone;
//
//    @Override
//    public Collection<?
//            extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        return simpleGrantedAuthorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
