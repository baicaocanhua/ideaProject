package com.maimai.idea;

import com.maimai.idea.common.AesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AesUtilTest {


    private static String src = "abc12332222222222222222222222222222222222223333232323232323232323asdagsdgdgdsgsgdsgsgsgsgsgsgggggggggggggggggggggggggg";
    private static String key = "woshihshsihshssssss";
    @Test
    public  void  testAes(){

        String en = AesUtil.aesEncrypt(src, key);
        System.out.println(en);
        String de = AesUtil.aesDecrypt(en, key);
        System.out.println(de);


    }
}
