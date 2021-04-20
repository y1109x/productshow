//package com.zjty.productshow.controller;
//
//
//import com.zjty.productshow.entity.ServerResponse;
//import com.zjty.productshow.entity.UserDetail;
//import com.zjty.productshow.util.CipherUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * @Author: sy
// * @Date: 2020/12/22 10:28
// */
//@Api(tags = "获取外部数据")
//@Slf4j
//@RestController
//@RequestMapping("/outSys")
//public class OutSysController {
//
//    @ApiOperation("/获取外部信息并转换为用户")
//    @RequestMapping(value = "/getOutSysTokenToPerson", method = RequestMethod.GET)
//    public ServerResponse getOutSysTokenToPerson(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String rsaPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ1KuMw0iX64XtL4yPkH5dtHOBoUK6sSLe6MQKvng1slgjpxteUVlzRl6hygoqkfqeU6NFwZjRAFmoPFuJFD4qcg5UPj2ZDrwIn86t4TPUTIDVX0xDmfpQpPuG+2+mAlgzFhoSlSEPU5zd5m7dgl6BbnPhoeDIHAVaYe5mEHbE+3AgMBAAECgYAZ+I18wiv1rXGSuhtM6x3rz92WiMARFgZZffTrrWVF9nKDPeU/twnpT+6Q3YEeFalSvQKKNnnRLGuv2ES6T5Arr/9s13XVeQCCEykC/ShIX6yHe3n5Nt2Ye+Eydtq4wfD9oavi4Oo+TIW4bEGbf7NMCPfHKHad1+XKvDKmM+N8GQJBAMv7JGdUycnhijr9uP1QZb3KVXef2rA3j3yb/ykhO35GFFqdQqUepocBDWm7jehRaRIUaRcKPgVfcSl0Hj0IKqMCQQDFZ30sceycKIBwirOeisYbG7n3ECacLkIVDXrYDtr3rk1MD1CSeH0k4IZHULg3zBq5CcxGfOAMWlW/o75Lc4vdAkAlFCTc56u6l9XxvTdITLoB0urQUO2GxMK/avfp+RGAdaqzggygST8KIWXXx3EXPt0znSQxPPpjLaM7XtE0VOyzAkAeq1NxQknmgQxjb8k0So84yvf7gJDfFvjBWJTLWJOAg54SwRZdi0a2IgP6tHnKgXVtZi5MjT4p7ScBXSlFg7ApAkBJ6FshzBpBpaPvX8F9Yej5o8gGq8YaGuUbKSWn13g3I8oF+F7jlh0EC/RQ2kQGf0J6+7vkS5CDEoSvn6cuEYDB";
//        String signPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCp7l8FMiUwUbyeRVvEsNp0ByyLkspuwKBmJZgdXHEPDAHINytHPbxLrhW57OtGsTJB8Vvb0cK5+GTWbMVajj5Px0IQbvXoyWoL4QWIZXwqPI3aLgeB2bGsKSLrKppam9nnWX93aMj8WO7KoHYAZYrguZWRxThwxz/VfW7BhQPjAQIDAQAB";
//        try {
//            String jwt = request.getHeader("jwt");
//            String decrypt = CipherUtil.decrypt(jwt, CipherUtil.string2PrivateKey(rsaPrivateKey));
//            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(CipherUtil.string2PublicKey(signPublicKey)).parseClaimsJws(decrypt);
//            log.info("CONTROLLER 接收到的用户信息为："+claimsJws.getBody());
//            UserDetail userRight = new UserDetail();
//            Claims body = claimsJws.getBody();
//            userRight.setUsername((String) body.get("username"));
//            userRight.setPhone((String) body.get("phone"));
//            userRight.setName((String) body.get("name"));
//            return ServerResponse.ok(userRight);
//        } catch (Exception e) {
//            throw new Exception("用户凭证无效");
//        }
//    }
//}
//
