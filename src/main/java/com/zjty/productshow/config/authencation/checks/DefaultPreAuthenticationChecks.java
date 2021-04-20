//package com.zjty.productshow.config.authencation.checks;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.security.authentication.AccountExpiredException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsChecker;
//
///**
// * 用户可用性验证类
// *
// * @author HuangXiahao
// * @version V1.0
// * @class DefaultPostAuthenticationChecks
// * @packageName com.example.personnelmanager.common.authencation.checks
// * @data 2020/6/14
// **/
//public class DefaultPreAuthenticationChecks implements UserDetailsChecker {
//
//    protected final Log logger = LogFactory.getLog(getClass());
//
//    /***
//     * 用户可用性验证
//     *
//     * @param user 需要用于验证的UserDetails
//     * @Return : void
//    */
//    @Override
//    public void check(UserDetails user) {
//        if (!user.isAccountNonLocked()) {
//            logger.debug("User account is locked");
//            throw new LockedException("User account is locked");
//        }
//        if (!user.isEnabled()) {
//            logger.debug("User account is disabled");
//            throw new DisabledException("User account is disabled");
//        }
//        if (!user.isAccountNonExpired()) {
//            logger.debug("User account is expired");
//            throw new AccountExpiredException("User account is expired");
//        }
//    }
//}
