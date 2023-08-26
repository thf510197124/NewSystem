package com.taiquan.service.impl;

import com.taiquan.dao.user.ManagerDao;
import com.taiquan.dao.user.UserDao;
import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.taiquan.utils.PrintUtil.println;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    ManagerDao managerDao;
    UserDao userDao;

    public ManagerDao getManagerDao() {
        return managerDao;
    }

    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void deleteUser(User user) {
        userDao.remove(user);
    }

    @Override
    public User getUserByName(String name) throws NoSuchObjectException{
        return userDao.getUserByName(name);
    }

    @Override
    public Manager getManagerByName(String name) throws NoSuchObjectException {
        return managerDao.getUserByName(name);
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.get(userId);
    }

    @Override
    public Manager getManagerByUsername(String username) throws NoSuchObjectException {
        return managerDao.getUserByUsername(username);
    }


    @Override
    public void passUser(User user) {
        user.setPassed(true);
        userDao.update(user);
    }

    @Override
    public Manager login(User manager) throws NoSuchObjectException{
        Manager mana1 = managerDao.getUserByUsername(manager.getUsername());
        if (mana1 == null){
            throw new NoSuchObjectException("用户名错误");
        }
        if (manager.getPassword().equals(mana1.getPassword())){
            return mana1;
        }else{
            throw new NoSuchObjectException("密码错误");
        }
    }

    @Override
    public Manager registerMrg(Manager manager) {
        manager.setPassed(true);
        manager.setMgr(true);
        managerDao.save(manager);
        return manager;
    }

    @Override
    public List<User> getUsers(Manager manager) {
        return userDao.getUserByManager(manager);
    }

    @Override
    public List<Manager> allManagerNames() {
        List<Manager> managers = managerDao.getManagers();
        return managers;
    }
}
