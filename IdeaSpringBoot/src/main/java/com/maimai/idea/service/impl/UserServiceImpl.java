package com.maimai.idea.service.impl;

import com.maimai.idea.dao.UserMapper;
import com.maimai.idea.pojo.User;
import com.maimai.idea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUser(int age) {
        return userMapper.getUser(age);
    }

}
