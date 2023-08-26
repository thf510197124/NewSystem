package com.taiquan.domain.customer;

import com.taiquan.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contactDetail")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactDetail extends BaseDomain implements Comparable<ContactDetail>{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactDetailId;
    private String contactDetails;
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date   addDate;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Customer.class)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    private Customer customer;

    public int getContactDetailId() {
        return contactDetailId;
    }

    public void setContactDetailId(int contactDetailId) {
        this.contactDetailId = contactDetailId;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ContactDetail)) return false;

        ContactDetail that = (ContactDetail) o;

        return new EqualsBuilder().append(getContactDetailId(), that.getContactDetailId()).append(getContactDetails(),
                that.getContactDetails()).append(getAddDate(), that.getAddDate()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getContactDetailId()).append(getContactDetails())
                .append(getAddDate()).toHashCode();
    }

    @Override
    public int compareTo(@NotNull ContactDetail o) {
        return o.getAddDate().compareTo(getAddDate());
        //出现的结果是新的出现在下面
        //return this.getAddDate().compareTo(o.getAddDate());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("contactDetailId", contactDetailId)
                .append("contactDetails", contactDetails)
                .append("addDate", addDate)
                .toString();
    }
}
