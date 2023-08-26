package com.taiquan.domain.customer;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customerEnums.CustomerType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contactPlan")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactPlan extends BaseDomain{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int          contactPlanId;
    @Enumerated(EnumType.ORDINAL)
    private CustomerType customerType;
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date         nextDate;
    @OneToOne(targetEntity = Customer.class,mappedBy = "contactPlan")
    private Customer customer;
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    public int getContactPlanId() {
        return contactPlanId;
    }

    public void setContactPlanId(int contactPlanId) {
        this.contactPlanId = contactPlanId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Date getNextDate() {
        return nextDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ContactPlan)) return false;

        ContactPlan that = (ContactPlan) o;

        return new EqualsBuilder().append(getContactPlanId(), that.getContactPlanId()).append(getCustomerType(),
                that.getCustomerType()).append(getNextDate(), that.getNextDate()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getContactPlanId()).append(getCustomerType())
                .append(getNextDate()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("contactPlanId", contactPlanId)
                .append("customerType", customerType)
                .append("nextDate", nextDate)
                .append("updateTime", updateTime)
                .toString();
    }
}
