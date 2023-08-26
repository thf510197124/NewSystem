package com.taiquan.service.impl;

import com.taiquan.dao.user.ManagerDao;
import com.taiquan.dao.user.UserDao;
import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.taiquan.utils.PrintUtil.println;

@Service("userService")
public class UserServiceImpl implements UserService {
    UserDao    userDao;
    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User updateUser(User user) {
        userDao.update(user);
        return user;
    }

    @Override
    public User login(User user) throws NoSuchObjectException {
        println("userName = " + user.getUsername());
        User user1 = userDao.getUserByUsername(user.getUsername());
        if (user1 == null){
            throw new NoSuchObjectException("用户名错误");
        }
        if(!user1.getPassed()){
            throw new NoSuchObjectException("未通过审核，请于你的经理联系");
        }
        if (user.getPassword().equals(user1.getPassword())){
            return user1;
        }else{
            throw new NoSuchObjectException("密码错误");
        }
    }
    @Override
    public User register(User user) {
        user.setPassed(false);
        user.setMgr(false);
        return userDao.save(user);
    }

    @Override
    public User getUserByUsername(String username) throws NoSuchObjectException {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUser(int userId) {
        return userDao.get(userId);
    }
}
