package com.taiquan.domain.order;

import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.order.enums.orderStatus.OrderStatus;
import com.taiquan.domain.order.enums.OrderType;
import com.taiquan.domain.users.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/*
* 根据客户的询价单对客户的联系情况进行时期调用，让我在客户的询价单发到几日内不停的联系
* 不能使用order的数据表名，因为与数据库关键字冲突
* */
@Entity
@Table(name = "orders")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends BaseDomain implements Comparable<Order>{
    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int         orderId;
    private String orderName;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,targetEntity = Good.class,mappedBy = "order")
    private Set<Good> goods;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date        createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date updateTime;
    //订单类型
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    //毛利
    @NumberFormat(style=NumberFormat.Style.CURRENCY)
    private float       profit;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Customer.class)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    private Customer    customer;
    private String others;//订单的发货信息等
    //总重量
    @NumberFormat(pattern = "##,###.##")
    private float totalWeight;
    //总数量
    @NumberFormat(pattern = "##,###,###")
    private int totalAmount;
    //总金额
    @NumberFormat(style=NumberFormat.Style.CURRENCY)
    private float totalSums;
    @NumberFormat(style=NumberFormat.Style.CURRENCY)
    private float totalCost;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;

    //订货订单，还是出货订单
    private boolean isBuy;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setGoods(Set<Good> goods) {
        this.goods = goods;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalSums() {
        return totalSums;
    }

    public void setTotalSums(float totalSums) {
        this.totalSums = totalSums;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getOrderId() != order.getOrderId()) return false;
        if (Float.compare(order.getProfit(), getProfit()) != 0) return false;
        if (getCreateTime() != null ? !getCreateTime().equals(order.getCreateTime()) : order.getCreateTime() != null)
            return false;
        if (getUpdateTime() != null ? !getUpdateTime().equals(order.getUpdateTime()) : order.getUpdateTime() != null)
            return false;
        if (!getCustomer().equals(order.getCustomer())) return false;
        if (getOthers() != null ? !getOthers().equals(order.getOthers()) : order.getOthers() != null) return false;
        return getUser().equals(order.getUser());
    }

    @Override
    public int hashCode() {
        int result = getOrderId();
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        result = 31 * result + (getProfit() != +0.0f ? Float.floatToIntBits(getProfit()) : 0);
        result = 31 * result + (getOthers() != null ? getOthers().hashCode() : 0);
        result = 31 * result + getUser().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderId", orderId)
                .append("createTime", createTime)
                .append("updateTime", updateTime)
                .append("orderType", orderType)
                .append("profit", profit)
                .append("others", others)
                .append("totalWeight", totalWeight)
                .append("totalSums", totalSums)
                .append("totalAmount", totalAmount)
                .toString();
    }

    @Override
    public int compareTo(@NotNull Order o) {
        return createTime.compareTo(o.getCreateTime());
    }

}
