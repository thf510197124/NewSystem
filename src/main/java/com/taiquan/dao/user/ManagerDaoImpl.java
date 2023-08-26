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

@Repository("managerDao")
public class ManagerDaoImpl extends BaseDaoHibernate5<Manager> implements ManagerDao {

    @Override
    public Manager getUserByName(String name) throws NoSuchObjectException {
        return findOne("from Manager man where man.name like '%"+name+ "%'");
    }

    @Override
    public Manager getUserByUsername(String username) throws NoSuchObjectException {
        String hql= "from Manager man where man.username = :username";
        println("username = ?" + username);
        /*String hql = "select man from Manager as man where man.username = :username";*/
        /*return findOne("from Manager man where man.username = ?0",username);*/
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username",username);
        return namedParamFindOne(hql,map);
    }

    @Override
    public List<Manager> getManagers() {
        return loadAll();
    }
}
