//package com.zjty.productshow.filter;
//
//import com.zjty.productshow.util.CipherUtil;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//@Slf4j
//@WebFilter(urlPatterns = {"/*"}, filterName = "authFilter")
//public class LoginFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//
//        String rsaPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJOHhVgQYUNJoBi2xT4MsgYW8ZC2p9QFtFwgsVVy4CwaEBeQvCQd7MU8w36gDgHhZDcDil9MmOQ/l3ItcUkhhiG12GubGKep/VrgZHDUzIOgGHxg7BaQN5gaA0tUVE0I8YVdaYr6d6VNzwmgkxU0MohGjXQI8HH+ss1rClmMvv1bAgMBAAECgYBYOzXBfFJfJrpL7Ys7V+Zkg/ZHASaQRDUg4CCOpRxy48d6AIK06vy+3V9h7KFT+JOx3zkzU6Eg6PNPQuQ8w4Lu3wS2fF84BrSXQG0A526wzptg3xT/FLf0HYS5ZYj6RrhP35PaagquXlokvsjwHNb02pw6IPhCp0L/F5ORXRjNeQJBANN2ezS7aiurLQ6/H7iQhJY1+SMQxoYiVh6pLnKOTPIEbUS+XSX+nbm018iAUmD0YSFsVzXojSKuGe3eb3zgSxcCQQCymeqyhdZKYq19LfCANr5PXySAAGJSoKjSqYJ2u9Gt2CuHM70QtIPsiOVBvAswQbOUmdrG7ptP3MP7z9xKE7pdAkEAxoII8t2ks03DxH4idw4jiScts39B/hUppyrGkX+IOAFz2f6iTtKQmg5sKqpbqux+afPcgpzGMPIjISuYmS+tDQJBAKpl/SzsUkJQGVPoQ6AgGrxhetKBC03oKIos3eNoWKnzJ7xr2YOZppBDH+/yd/5HgQ1TKQ9JUXcFmxqh+3vVjKUCQBdjUbY0jaZ5OH33eF+kAfHMUR8zgEvO+w7F+dN8Js/+OLWOTmPdFu/fNKvxRjKc+DlwV4SQcvwiS9JwI1iu8pE=";
//        String signPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdrWYWeEqv1EgV4MHGbbcZojBMunouyh6PPCtP+s0O5jBXAYusyWX80mstaojEPuNpDvfiNdda6cVFbAq8MR4e665rEnr/k3rdG5n+NIIBiQGjA4u1B9FtDkNfyrwBLKRS4ExLSmsje0pDPziOvLuhFGkHaWMoRp5rTSGQrnwimQIDAQAB";
//        String path = ((HttpServletRequest) servletRequest).getRequestURI().substring(((HttpServletRequest) servletRequest).getContextPath().length()).replaceAll("[/]+$", "");
//
//        log.info(path);
//
//            if(StringUtils.isNotBlank(path) && !StringUtils.containsAny(path, "swagger", "webjars", "favicon","api-docs","csrf", "springfox")) {
//                final String token = req.getHeader("token");
//                if (StringUtils.isBlank(token)) {
//                    try {
//                        throw new Exception("INTERNAL ERROR...");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                String decrypt = CipherUtil.decrypt(token, CipherUtil.string2PrivateKey(rsaPrivateKey));
//                Claims claimsJws = Jwts.parser().setSigningKey(CipherUtil.string2PublicKey(signPublicKey)).parseClaimsJws(decrypt).getBody();
//            }
//            filterChain.doFilter(servletRequest, servletResponse);
//
//    }
//
//}
//
