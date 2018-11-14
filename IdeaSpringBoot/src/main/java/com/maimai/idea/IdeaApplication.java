package com.maimai.idea;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author user
 */
@SpringBootApplication
public class IdeaApplication {

    private static final Logger log = LoggerFactory.getLogger(IdeaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(IdeaApplication.class, args);
        //region 自己的代码
        log.info("IdeaApplication启动完成");
        //endregion
    }
}
