package com.maimai.idea;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IdeaApplicationTests {

    private static final String KEY_ALGORITHM = "DES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";//默认的加密算法


    @Test
    public void contextLoads() throws Exception {

        String DATA = "maimai";
        byte[] desKey = initKey();
        System.out.println("DES KEY : " + Base64.encodeBase64String(desKey));
        byte[] desResult = encrypt(DATA.getBytes(), desKey);
        String bst = Base64.encodeBase64String(desResult);
        System.out.println(DATA + ">>>DES 加密结果>>>" + bst);

        byte[] desPlain = decrypt(desResult, desKey);
        System.out.println(DATA + ">>>DES 解密结果>>>" + new String(desPlain));


        byte[] ss = Base64.decodeBase64(bst);

        byte[] desPlain1 = decrypt(ss, desKey);
        System.out.println(DATA + ">>>DES 解密结果>>>" + new String(desPlain1));


        String content = "hello,您好";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        System.out.println("content:" + content);
        String s1 = encrypt(content, key);
        System.out.println("s1:" + s1);
        System.out.println("s2:" + decrypt(s1, key));


        String createKey = createKey();

        String s = enCreate("买买买买", createKey);
        String d = deCreate(s, createKey);

        System.out.println("s:" + s);
        System.out.println("d:" + d);


    }


    /*
     * 生成密钥
     */
    public byte[] initKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    private String createKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.encodeBase64String(secretKey.getEncoded());
    }


    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //DES 要求密钥长度为 56
            kg.init(56, new SecureRandom(key.getBytes()));

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为DES专用密钥
        } catch (NoSuchAlgorithmException ex) {

        }

        return null;
    }

    /*
     * DES 加密
     */
    public byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }

    private String enCreate(String content, String createKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] key = Base64.decodeBase64(createKey);
        SecretKey secretKey = new SecretKeySpec(key, "DES");
        byte[] data = content.getBytes();
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return Base64.encodeBase64String(cipherBytes);
    }

    /*
     * DES 解密
     */
    public byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }


    private String deCreate(String content, String createKey) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] data = Base64.decodeBase64(content);

        byte[] key = Base64.decodeBase64(createKey);
        SecretKey secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return new String(plainBytes);
    }


    /**
     * DES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception ex) {

        }

        return null;
    }

    /**
     * DES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {

        }

        return null;
    }


}
