package com.taiquan.dao.order;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.order.enums.OrderType;
import com.taiquan.domain.users.User;

import java.util.Date;
import java.util.List;

public interface OrderDao extends BaseDao<Order> {
    public List<Order> getOrderByAddTime(User user,Date from, Date to);
    public List<Order> getOrderByOrderTypeAndTime(User user,OrderType orderType,Date from,Date to);
    public List<Order> getOrderByCustomer(Customer customer);
    public List<Order> getOrderByUser(User user);

    Order getOrderByGoodId(int goodId);
}
