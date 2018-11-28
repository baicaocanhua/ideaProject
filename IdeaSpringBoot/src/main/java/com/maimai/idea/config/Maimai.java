package com.maimai.idea.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author user
 */
@ConfigurationProperties(prefix = Maimai.JEDIS_PREFIX)
public class Maimai {
    public static final String JEDIS_PREFIX = "mai";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;

    private int age;
}
