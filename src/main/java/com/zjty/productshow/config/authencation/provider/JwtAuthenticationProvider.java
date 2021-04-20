/*
package com.zjty.productshow.config.authencation.provider;

import com.zjty.productshow.config.authencation.checks.DefaultPreAuthenticationChecks;
import com.zjty.productshow.config.authencation.token.JwtAuthencationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

*/
/**
 * 自定义SpringSecurity的用户处理类类
 * 当用户通过统一登录平台访问本系统时由该类进行用户验证
 *
 * @author HuangXiahao
 * @version V1.0
 * @class JWTAuthenticationProvider
 * @packageName com.example.personnelmanager.common.SpringSecurityProvider
 * @data 2020/6/13
 **//*

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    */
/**
     * 用户可用性检查类
     *//*

    private final UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();

    public JwtAuthenticationProvider() {
    }

    */
/***
     * 验证用户
     *
     * @param authentication
     * @Return : org.springframework.security.core.Authentication
    *//*

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(JwtAuthencationToken.class, authentication,
                "错误的凭证");
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
                : ((UserDetails)authentication.getPrincipal()).getUsername();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        preAuthenticationChecks.check(user);
        return createSuccessAuthentication(user,authentication,user);
    }

    */
/***
     * 返回True则由该对象进行用户验证
     *
     * @param authentication
     * @Return : boolean
    *//*

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthencationToken.class.isAssignableFrom(authentication));
    }



    */
/***
     * 创建一个已经通过验证的用户实例
     * 该方法由SpringSecurity源码魔改得到
     * @param principal
     * @param authentication
     * @param user
     * @Return : org.springframework.security.core.Authentication
     *//*

    protected Authentication createSuccessAuthentication(Object principal,
                                                         Authentication authentication, UserDetails user) {
        JwtAuthencationToken result = new JwtAuthencationToken(principal,
                user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

}
*/
