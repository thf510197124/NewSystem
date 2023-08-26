package com.taiquan.bean;

import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.order.enums.OrderType;
import com.taiquan.domain.order.enums.orderStatus.OrderStatus;
import com.taiquan.domain.users.User;
import java.util.Date;
import java.util.Set;

public class SupplierOrderBean {
    private int         orderId;
    private String orderName;
    private Set<InnerGoodBean> goods;
    private Date createTime;
    private Date updateTime;
    //订单类型
    private OrderType orderType;
    private OrderStatus orderStatus;
    private float       profit;
    private Customer    customer;
    private String others;//订单的发货信息等
    private float totalWeight;
    private int totalAmount;
    private float totalSums;
    private float totalCost;
    private User user;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Set<InnerGoodBean> getGoods() {
        return goods;
    }

    public void setGoods(Set<InnerGoodBean> goods) {
        this.goods = goods;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalSums() {
        return totalSums;
    }

    public void setTotalSums(float totalSums) {
        this.totalSums = totalSums;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public static SupplierOrderBean getSupplierOrderBean(Order order){
        SupplierOrderBean bean = new SupplierOrderBean();
        bean.setOrderId(order.getOrderId());
        bean.setCreateTime(order.getCreateTime());
        bean.setCustomer(order.getCustomer());
        bean.setOrderName(order.getOrderName());
        bean.setOrderStatus(order.getOrderStatus());
        bean.setOrderType(order.getOrderType());
        bean.setOthers(order.getOthers());
        bean.setUpdateTime(order.getUpdateTime());
        bean.setUser(order.getUser());
        return bean;
    }
}
