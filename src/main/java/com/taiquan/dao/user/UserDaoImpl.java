package com.taiquan.dao.user;


import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoHibernate5<User> implements UserDao {

    @Override
    public User getUserByName(String name) throws NoSuchObjectException {
        return find("from User user where user.name like '%" + name + "%'").get(0);
    }

    @Override
    public User getUserByUsername(String username) throws NoSuchObjectException {
        //return findOne("from User user where user.username = ?0",username);
        /*return find("from User user where user.username=" + username).get(0);*/
        String hql= "from User user where user.username = :username";
        //println("username = ?" + username);
        /*String hql = "select man from Manager as man where man.username = :username";*/
        /*return findOne("from Manager man where man.username = ?0",username);*/
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username",username);
        return namedParamFindOne(hql,map);
    }

    @Override
    public List<User> getUserByManager(Manager manager) {
        String hql = "select user from User user where user.manager = :manager";
        Map<String,Object> map = new HashMap<>();
        map.put("manager",manager);
        return namedParamFind(hql,map);
    }


}
