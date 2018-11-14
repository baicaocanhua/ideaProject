package com.maimai.idea.service;

import com.maimai.idea.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUser(int age);
}
