package com.maimai.idea;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author user
 */
@SpringBootApplication
public class IdeaApplication {

    private static final Logger log = LoggerFactory.getLogger(IdeaApplication.class);



    public static void main(String[] args) {
        System.setProperties("key","maimaikey");
        SpringApplication.run(IdeaApplication.class, args);
        //region 自己的代码

        log.info("IdeaApplication启动完成");
        System.out.println(Arrays.toString(args));//获取所有 包含--server.port
        //endregion


        //region 测试
        //ObjectMapper mapper = new ObjectMapper();
        //String jsonlist = mapper.writeValueAsString(users);
        // User user = mapper.readValue(json, User.class);
        /*
        String json = "[{\"name\":\"zhangsan\",\"age\":20,\"birthday\":844099200000,\"email\":\"zhangsan@163.com\"}]";
        List<User> beanList = mapper.readValue(json, new TypeReference<List<User>>() {});
        System.out.println(beanList);
         */
        //mapper.readTree();
        //endregion
    }
}
