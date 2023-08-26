package com.taiquan.dao.user;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;

import java.util.List;

public interface UserDao extends BaseDao<User>{
    public User getUserByName(String name) throws NoSuchObjectException;
    public User getUserByUsername(String username) throws NoSuchObjectException;
    public List<User> getUserByManager(Manager manager);
}
