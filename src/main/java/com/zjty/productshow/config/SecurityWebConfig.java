//package com.zjty.productshow.config;
//
//import com.google.gson.Gson;
//import com.zjty.productshow.config.authencation.filter.JWTAuthorizationFilter;
//import com.zjty.productshow.config.authencation.provider.JwtAuthenticationProvider;
//import com.zjty.productshow.entity.ResultObj;
//import com.zjty.productshow.util.AuthenticationUtils;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * 描述：sprinSecurity安全框架配置
// * @author HuangXiahao
// * @version V1.0
// * @class SecurityWebConfig
// * @packageName com.example.personnelmanager.common.config
// * @data 2020/5/20
// **/
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     *自定义Jwt用户验证类
//     **/
//    final
//    JwtAuthenticationProvider jwtAuthenticationProvider;
//
//    /**
//     * jwt公钥存储路径
//     **/
////    @Value("${file.jwtFilePath}")
////    String jwtFilePath;
//
//    public SecurityWebConfig(JwtAuthenticationProvider jwtAuthenticationProvider) {
//        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
//    }
//
//    /**
//     * SpringSecurity配置
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and()
//                .authorizeRequests()
//                //接受公钥接口权限开放
//                .antMatchers("/acceptSignature").permitAll()
//                //登陆接口权限开发
//                .antMatchers("/api/user/login").permitAll()
//                .antMatchers("/**/front/**").permitAll()
//                .antMatchers("/file/**").permitAll()
//
//                //swagger权限开放
//                .antMatchers("/v2/api-docs",
//                        "/swagger-resources/configuration/ui",
//                        "/swagger-resources",
//                        "/swagger-resources/configuration/security",
//                        "/swagger-ui.html",
//                        "/web/**",
//                        "/static/**",
//                        "/webjars/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .logout()
//                //登出接口权限开放
//                .logoutUrl("/api/user/logout").permitAll()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf().disable();
//        http.exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//
//        //添加自定义用户验证拦截器至UsernamePasswordAuthenticationFilter的位置
//        http.addFilterAt(new JWTAuthorizationFilter(authenticationManager()), BasicAuthenticationFilter.class);
//    }
//
//
//
//    /***
//     * 验证管理器配置
//     *
//     * @param auth
//     * @Return : void
//    */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(jwtAuthenticationProvider);
//    }
//
//    //
//
//
//    /***
//     * 登录成功后干些啥
//     */
//    public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//        @Override
//        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//                                            Authentication authentication) throws IOException {
//            httpServletResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild");
//            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
//            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//            Map<String, Object> result = new HashMap<>(1);
//            result.put("msg","登录成功");
//            result.put("username", AuthenticationUtils.getAuthentication().getUsername());
//            result.put("name",AuthenticationUtils.getAuthentication().getName());
//            servletOutputStream.write(new Gson().toJson(result).getBytes(StandardCharsets.UTF_8));
//            servletOutputStream.flush();
//            servletOutputStream.close();
//        }
//    }
//
//    /**
//     * 登录失败了干些啥
//     */
//    public static class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//
//        @Override
//        public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//                                            AuthenticationException e) throws IOException {
//            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
//            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//            ResultObj resultObj = new ResultObj(e.getMessage());
//            servletOutputStream.write(new Gson().toJson(resultObj).getBytes(StandardCharsets.UTF_8));
//            servletOutputStream.flush();
//            servletOutputStream.close();
//        }
//    }
//
//    /**
//     * 没有登录干些啥
//     */
//    public static class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//        @Override
//        public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//                             AuthenticationException e) {
//            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//        }
//    }
//
//}
