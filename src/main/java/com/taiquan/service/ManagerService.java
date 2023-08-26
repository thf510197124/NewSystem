package com.taiquan.service;

import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;

import java.util.List;

public interface ManagerService {
    //管理员用来删除用户
    public void deleteUser(User user);
    public User getUserByName(String name) throws NoSuchObjectException;
    //是不是管理员需要用管理员方法查找
    public Manager getManagerByName(String name) throws NoSuchObjectException;
    public User getUserByUserId(int userId);
    public Manager getManagerByUsername(String username) throws NoSuchObjectException;
    //是否批准客户注册
    //为什么不设置管理员，在用户注册时，选择管理员
    public void passUser(User user);
    public Manager login(User manager) throws NoSuchObjectException;
    public Manager registerMrg(Manager manager);
    public List<User> getUsers(Manager manager);

    List<Manager> allManagerNames();
}
