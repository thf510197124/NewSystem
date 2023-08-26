package com.taiquan.domain.customer;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.users.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer extends BaseDomain{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int                customerId;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId",referencedColumnName ="companyId")
    private Company            company;

    @OneToMany(targetEntity = Employee.class,mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Employee>      employees;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "contactPlanId",referencedColumnName = "contactPlanId")
    private ContactPlan        contactPlan;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = ContactDetail.class,
            mappedBy = "customer")
    private Set<ContactDetail> contactDetails;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Order.class,mappedBy = "customer")
    private Set<Order>   orders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User         user;

    public Customer() {
        this.employees = new HashSet<Employee>();
        ContactPlan contactPlan = new ContactPlan();
        contactPlan.setCustomerType(CustomerType.尚未联系);
        contactPlan.setNextDate(new Date(2050,12,31));
        this.contactPlan = contactPlan;
        employees = new HashSet<Employee>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public ContactPlan getContactPlan() {
        return contactPlan;
    }

    public void setContactPlan(ContactPlan contactPlan) {
        this.contactPlan = contactPlan;
    }

    public Set<ContactDetail> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Set<ContactDetail> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Customer)) return false;

        Customer that = (Customer) o;

        return new EqualsBuilder().append(getCompany(), that.getCompany()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getCompany()).toHashCode();
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", company=" + company + ", contactPlan=" + contactPlan + ", contactDetails=" + contactDetails + '}';
    }
}
