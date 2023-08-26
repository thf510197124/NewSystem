package com.taiquan.domain.users;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.customer.PhoneNumber;
import com.taiquan.domain.order.Order;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@DiscriminatorColumn(name = "user",discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseDomain{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int           userId;
    private String        username;
    private String        password;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Customer.class,mappedBy = "user")
    private Set<Customer> customerSet;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Order.class,mappedBy = "user")
    private Set<Order>    orders;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Manager.class)
    //这里要特别注意，引入的是“userId”，而不是managerId
    @JoinColumn(name = "managerId",referencedColumnName = "userId")
    private Manager       manager;
    private String        name;
    @Embedded
    private PhoneNumber   phoneNumber;
    private Boolean passed;
    private boolean mgr;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMgr() {
        return mgr;
    }

    public void setMgr(boolean mgr) {
        this.mgr = mgr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder().append(getUsername(), user.getUsername()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getUsername()).append(getPassword()).toHashCode();
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username='" + username + '\'' + ", password='" +
                password + '\'' + ", manager=" + manager + ", name='" + name + '\'' + ", phoneNumber="
                + phoneNumber + ", passed=" + passed + ", mgr=" + mgr + '}';
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
