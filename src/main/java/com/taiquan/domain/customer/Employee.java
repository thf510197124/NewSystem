package com.taiquan.domain.customer;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customerEnums.PositionType;
import com.taiquan.domain.order.Supplier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

import static com.taiquan.utils.PrintUtil.println;

@Entity
@Table(name = "employee")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee extends BaseDomain implements Comparable<Employee>{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String name;
    private boolean gendarMale = true;
    //仅仅是一个年龄范围
    private String         age;
    @Enumerated(EnumType.ORDINAL)
    private PositionType   positionType;
    private String others;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "employee")
    private List<Contacts> contacts;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date addDate;

    @ManyToOne(targetEntity = Customer.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne(targetEntity = Supplier.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId",referencedColumnName = "supplierId")
    private Supplier supplier;

    public Employee(){
        contacts = new ArrayList<>();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGendarMale() {
        return gendarMale;
    }

    public void setGendarMale(boolean gendarMale) {
        this.gendarMale = gendarMale;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contacts> getContacts() {
         Collections.sort(contacts);;
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder().append(getName(), employee.getName()).append(getContacts(), employee.getContacts()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getName()).append(getContacts()).append(getOthers()).toHashCode();
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", name='" + name + '\'' + ", gendarMale=" + gendarMale + ", age='" + age + '\'' + ", positionType=" + positionType + ",others="+ others +  ", contacts=" + contacts + ", email='" + email +  '}';
    }

    @Override
    public int compareTo(@NotNull Employee o) {
        if (name!=null && (name.contains("采购") || name.contains("供应"))){
            return -1;
        }else if (o.getName() != null && !o.getName().equals("")){
            if ( o.getName().contains("采购") || o.getName().contains("供应")) {
                return 1;
            }
            if (o.getName().contains("错误")){
                return -1;
            }
        }else{
            if (this.positionType==null){
                return 1;
            }
            if(o.getPositionType() == null){
                return -1;
            }
        }
        return getOrderFromPositionType(positionType) - getOrderFromPositionType(o.positionType);
    }
    public static int getOrderFromPositionType(PositionType positionType){
        if (positionType == null)
            return 21;
        switch (positionType){
            case 采购经理:
                return 0;
            case 采购主管:
                return 1;
            case 供应:
                return 2;
            case 董事长:
                return 3;
            case 执行董事:
                return 4;
            case 老板娘:
                return 5;
            case 总经理:
                return 6;
            case 介绍人:
                return 7;
            case 厂长:
                return 8;
            case 现场:
                return 9;
            case 前台:
                return 10;
            case 监事:
                return 11;
            case 股东:
                return 12;
            case 未知:
                return 13;
            case 财务:
                return 14;
            case 后勤:
                return 15;
            case 销售总监:
                return 16;
            case 老板亲戚:
                return 17;
            case 人事:
                return 18;
            case 销售主管:
                return 19;
            case 离职采购:
                return 20;
        }
        return 21;
    }
    public static void main(String[] args){
        println(getOrderFromPositionType(null));
    }
}
