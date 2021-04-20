//package com.zjty.productshow.util;
//
//import com.zjty.productshow.exception.GlobalException;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.security.*;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//
///**
// * @author sy
// * @version V1.0
// **/
//@Slf4j
//public class CipherUtil {
//
//    /**
//     * RSA最大解密密文大小
//     */
//    private static final int MAX_DECRYPT_BLOCK = 128;
//
//
//    /**
//     * 获取私钥
//     *
//     * @param str 私钥字符串
//     * @return kk
//     */
//    public static PrivateKey string2PrivateKey(String str) {
//        try {
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] decodedKey = Base64.getDecoder().decode(str.getBytes());
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
//            return keyFactory.generatePrivate(keySpec);
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new GlobalException("解析私钥错误");
//        }
//    }
//
//    /**
//     * @param str 公钥字符串
//     * @return key
//     */
//    public static PublicKey string2PublicKey(String str) {
//        try {
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] decodedKey = Base64.getDecoder().decode(str.getBytes());
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
//            return keyFactory.generatePublic(keySpec);
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new GlobalException("解析公钥错误");
//        }
//    }
//
//    /**
//     * RSA解密
//     *
//     * @param data       待解密数据
//     * @param privateKey 私钥
//     * @return 11
//     */
//    public static String decrypt(String data, PrivateKey privateKey) {
//        try {
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//            byte[] dataBytes = Base64.getDecoder().decode(data);
//            int inputLen = dataBytes.length;
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            int offset = 0;
//            byte[] cache;
//            int i = 0;
//            // 对数据分段解密
//            while (inputLen - offset > 0) {
//                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
//                    cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
//                } else {
//                    cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
//                }
//                out.write(cache);
//                i++;
//                offset = i * MAX_DECRYPT_BLOCK;
//            }
//            byte[] decryptedData = out.toByteArray();
//            out.close();
//            // 解密后的内容
//            return new String(decryptedData, StandardCharsets.UTF_8);
//        } catch (NoSuchAlgorithmException | IOException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
//            throw new GlobalException("解密密失败");
//        }
//    }
//
//}
