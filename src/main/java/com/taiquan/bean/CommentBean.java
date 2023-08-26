package com.taiquan.bean;

import com.taiquan.domain.BaseDomain;

public class CommentBean extends BaseDomain{
    private String details;
    private Integer customerId;
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
