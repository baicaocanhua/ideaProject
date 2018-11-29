package com.maimai.idea;

import com.maimai.idea.common.DesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesUtilTest {
    @Test
    public void  test(){
        String content = "hello,您好";
        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        System.out.println("content:" + content);
        String s1 = DesUtil.encrypt(content, key);
        System.out.println("s1:" + s1);
        System.out.println("s2:"+ DesUtil.decrypt(s1, key));

    }
}
