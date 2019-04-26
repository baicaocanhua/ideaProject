package com.maimai.idea.common;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class AesUtil {
    public static String aesEncrypt(String src, String encodeRules) {

        try {
            // 生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // key的转换s
            Key key = new SecretKeySpec(keyBytes, "AES");

            // 加密
            // AES/工作模式/填充方式
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk aes encrypt : " + Base64.encodeBase64String(result));
            return Base64.encodeBase64String(result);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String aesDecrypt(String src, String encodeRules) {
        byte[] result = Base64.decodeBase64(src);
        try {
            // 生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(encodeRules.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // key的转换
            Key key = new SecretKeySpec(keyBytes, "AES");
            // AES/工作模式/填充方式
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result1 = cipher.doFinal(result);
            System.out.println("jdk aes decrypt : " + new String(result1));
            return new String(result1);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
