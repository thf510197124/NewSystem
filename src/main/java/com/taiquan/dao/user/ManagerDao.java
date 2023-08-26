package com.taiquan.dao.user;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;

import java.util.List;

public interface ManagerDao extends BaseDao<Manager>{
    public Manager getUserByName(String name) throws NoSuchObjectException;
    public Manager getUserByUsername(String username) throws NoSuchObjectException;
    public List<Manager> getManagers();
}
