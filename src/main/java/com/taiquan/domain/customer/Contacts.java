package com.taiquan.domain.customer;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customerEnums.PhoneType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contacts extends BaseDomain implements Comparable<Contacts> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int         contactsId;
    @Enumerated(EnumType.ORDINAL)
    private PhoneType   phoneType;
    @Embedded
    private PhoneNumber phoneNumber;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Employee.class)
    @JoinColumn(name = "employeeId",referencedColumnName = "employeeId",nullable = false)
    private Employee    employee;

    public int getContactsId() {
        return contactsId;
    }

    public void setContactsId(int contactsId) {
        this.contactsId = contactsId;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacts)) return false;

        Contacts contacts = (Contacts) o;

        if (getContactsId() != contacts.getContactsId()) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(contacts.getPhoneNumber()) : contacts.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getContactsId();
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contacts{" + "contactsId=" + contactsId + ", phoneType=" + phoneType + ", phoneNumber=" + phoneNumber + '}';
    }

    @Override
    public int compareTo(@NotNull Contacts o) {
        return o.getPhoneType() != getPhoneType() ? getOrderFromPhoneType(phoneType) - getOrderFromPhoneType(o.phoneType)
                : o.getPhoneNumber().compareTo(getPhoneNumber());
    }
    public static int getOrderFromPhoneType(PhoneType phoneType){
        if (phoneType==null){
            return 20;
        }
        switch (phoneType){
            case 联系中:
                return 0;
            case 手机:
                return 1;
            case 固话:
                return 2;
            case 未联系的电话:
                return 3;
            case 总机:
                return 4;
            case 工作:
                return 5;
            case 家庭:
                return 6;
            case 微信:
                return 7;
            case QQ:
                return 8;
            case 传真:
                return 9;
            case 无用电话:
                return 10;
            case 空号:
                return 11;
        }
        return 20;
    }
}
