package com.taiquan.dao.order.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.OrderDao;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.order.enums.OrderType;
import com.taiquan.domain.users.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoHibernate5<Order> implements OrderDao{
    @Override
    public List<Order> getOrderByAddTime(User user, Date fromTime, Date toTime) {
        String hql = "from Order ord where ord.user = :user and ord.createTime between :toTime and :toTime";
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("fromTime",fromTime);
        map.put("toTime",toTime);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Order> getOrderByOrderTypeAndTime(User user, OrderType orderType, Date fromTime, Date toTime) {
        String hql = "from Order ord where ord.user = :user and ord.orderType" +
                " = :orderType and ord.createTime between :fromTime and :toTime";
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        map.put("orderType",orderType);
        map.put("fromTime",fromTime);
        map.put("toTime",toTime);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Order> getOrderByCustomer(Customer customer) {
        String hql = "from Order order where order.customer = :customer";
        Map<String,Object> map = new HashMap<>();
        map.put("customer",customer);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        String hql = "from Order order where order.user = :user";
        Map<String,Object> maps = new HashMap<>();
        maps.put("user",user);
        return namedParamFind(hql,maps);
    }

    @Override
    public Order getOrderByGoodId(int goodId) {
        String sql = "select orderId from good where goodId = " + goodId;
        int orderId = (int) sqlQuery(sql);
        return get(orderId);
    }

}
