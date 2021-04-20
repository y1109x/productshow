//package com.zjty.productshow.config.authencation.filter;
//
//import com.zjty.productshow.config.authencation.token.JwtAuthencationToken;
//import com.zjty.productshow.entity.UserDetail;
//import com.zjty.productshow.util.CipherUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//
///**
// * @author HuangXiahao
// * @version V1.0
// * @class JWTAuthorizationFilter
// * @packageName com.example.personnelmanager.common.authencation.filter
// **/
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//    public static final String JWT_KEY = "jwt";
//
//    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        UserDetail userByJwt;
//        JwtAuthencationToken authRequest ;
//        //如果请求头中没有jwt凭证的话说明不应该使用该类进行验证，直接报错
//        if (!StringUtils.isEmpty(request.getHeader(JWT_KEY))) {
//            //通过请求头获取jwt凭证中的用户信息
//            userByJwt = getuserbyjwt(request);
//            authRequest = new JwtAuthencationToken(
//                    userByJwt);
//            SecurityContextHolder.getContext().setAuthentication(
//                    authRequest);
//            //进行验证
//        }
//        // 如果请求头中有token，则进行解析，并且设置认证信息
//        super.doFilterInternal(request, response, chain);
//    }
//
//    /***
//     * 通过请求头获取请求头中的用户信息
//     *
//     * @param request
//     * @Return : com.example.personnelmanager.entity.User
//     */
//    public UserDetail getuserbyjwt(HttpServletRequest request) {
//        String rsaPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ1KuMw0iX64XtL4yPkH5dtHOBoUK6sSLe6MQKvng1slgjpxteUVlzRl6hygoqkfqeU6NFwZjRAFmoPFuJFD4qcg5UPj2ZDrwIn86t4TPUTIDVX0xDmfpQpPuG+2+mAlgzFhoSlSEPU5zd5m7dgl6BbnPhoeDIHAVaYe5mEHbE+3AgMBAAECgYAZ+I18wiv1rXGSuhtM6x3rz92WiMARFgZZffTrrWVF9nKDPeU/twnpT+6Q3YEeFalSvQKKNnnRLGuv2ES6T5Arr/9s13XVeQCCEykC/ShIX6yHe3n5Nt2Ye+Eydtq4wfD9oavi4Oo+TIW4bEGbf7NMCPfHKHad1+XKvDKmM+N8GQJBAMv7JGdUycnhijr9uP1QZb3KVXef2rA3j3yb/ykhO35GFFqdQqUepocBDWm7jehRaRIUaRcKPgVfcSl0Hj0IKqMCQQDFZ30sceycKIBwirOeisYbG7n3ECacLkIVDXrYDtr3rk1MD1CSeH0k4IZHULg3zBq5CcxGfOAMWlW/o75Lc4vdAkAlFCTc56u6l9XxvTdITLoB0urQUO2GxMK/avfp+RGAdaqzggygST8KIWXXx3EXPt0znSQxPPpjLaM7XtE0VOyzAkAeq1NxQknmgQxjb8k0So84yvf7gJDfFvjBWJTLWJOAg54SwRZdi0a2IgP6tHnKgXVtZi5MjT4p7ScBXSlFg7ApAkBJ6FshzBpBpaPvX8F9Yej5o8gGq8YaGuUbKSWn13g3I8oF+F7jlh0EC/RQ2kQGf0J6+7vkS5CDEoSvn6cuEYDB";
//        String signPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCp7l8FMiUwUbyeRVvEsNp0ByyLkspuwKBmJZgdXHEPDAHINytHPbxLrhW57OtGsTJB8Vvb0cK5+GTWbMVajj5Px0IQbvXoyWoL4QWIZXwqPI3aLgeB2bGsKSLrKppam9nnWX93aMj8WO7KoHYAZYrguZWRxThwxz/VfW7BhQPjAQIDAQAB";
//        String jwt = request.getHeader("jwt");
//        try {
//            String decrypt = CipherUtil.decrypt(jwt, CipherUtil.string2PrivateKey(rsaPrivateKey));
//            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(CipherUtil.string2PublicKey(signPublicKey)).parseClaimsJws(decrypt);
//            logger.info("接收到的用户信息为：" + claimsJws.getBody());
//            UserDetail userRight = new UserDetail();
//            Claims body = claimsJws.getBody();
//            userRight.setUsername((String) body.get("username"));
//            userRight.setPhone((String) body.get("phone"));
//            userRight.setName((String) body.get("name"));
//            return userRight;
//        } catch (Exception e) {
//            logger.error("用户凭证无效");
//            throw new UsernameNotFoundException("用户凭证无效");
//        }
//    }
//
//}
