package com.taiquan.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OrderBean {
    private String orderType;
    private String orderStatus;
    private String others;//订单的发货信息等

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orderType", orderType)
                .append("orderStatus", orderStatus)
                .append("others", others)
                .toString();
    }
}
