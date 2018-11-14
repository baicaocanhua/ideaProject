package com.maimai.idea.dao;

import com.maimai.idea.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT id,username,age,phone,email FROM USERS WHERE AGE=#{age}")
    List<User> getUser(int age);
}
