package com.taiquan.service;

import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;

public interface UserService {
    //用户更新自己的资料
    public User updateUser(User user);
    //登录,并判定是manager还是普通user
    public User login(User user) throws NoSuchObjectException;
    //注册用户
    public User register(User user);
    public User getUserByUsername(String username) throws NoSuchObjectException;
    public User getUser(int userId);
}
