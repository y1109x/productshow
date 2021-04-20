/*
package com.zjty.productshow.config.authencation.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

*/
/**
 * 描述：统一登录平台用户凭证
 * @author HuangXiahao
 * @version V1.0
 * @class JwtAuthencationToken
 * @packageName com.example.personnelmanager.common.authencationToken
 * @data 2020/6/13
 **//*

public class JwtAuthencationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    */
/**
     * 构造一个待验证的Jwt用户凭证
     * @param principal
     *//*

    public JwtAuthencationToken(Object principal) {
        super(null);
        this.principal = principal;
        this.credentials = null;
        setAuthenticated(false);
    }

    */
/**
     * 构造一个已经通过验证的Jwt用户凭证
     * @param principal
     *//*

    public JwtAuthencationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = null;
        super.setAuthenticated(true);

    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    */
/***
     * 设置构造是否成功标记
     *
     * @param isAuthenticated
     * @Return : void
    *//*

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }



}
*/
