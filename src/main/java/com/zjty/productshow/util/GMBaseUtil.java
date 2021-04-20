package com.zjty.productshow.util;

/**
 * <h4>Description : </h4>
 *
 * @Author czq
 * @Date 2/3/21 10:39 AM
 * @Version 1.0
 */

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;

public class GMBaseUtil {
    static {
        Security.addProvider((Provider)new BouncyCastleProvider());
    }
}
