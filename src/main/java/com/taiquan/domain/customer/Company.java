package com.taiquan.domain.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customerEnums.CapitalType;
import com.taiquan.domain.users.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

//创建一个公司，同时创建相关联的客户
//另一个方式是创建一个CustomerBean，类似于company
//也可以在前台传入一个company，而后台创建一个Customer；
//该客户在表现上更像是一个Bean
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Company extends BaseDomain{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int          companyId;
    @Column(name = "companyName",unique = true)
    private String       companyName;
    private String       owner;
    //在后台将String的phone转换成phoneNumber
    @Embedded
    private PhoneNumber  phoneNumber;
    private float        capital;
    @Enumerated(EnumType.ORDINAL)
    private CapitalType capitalType;
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date        establishedDate;
    private String      web;
    private String      businesses;
    //添加关于客户的说明，已经联系电话的情况等
    private String      others;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "companyLogId",referencedColumnName = "companyLogId")
    private CompanyLog   companyLog;
    @OneToOne(targetEntity = Customer.class,mappedBy = "company",fetch = FetchType.EAGER)
    private Customer     customer;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = Address.class,mappedBy = "company")
    private Set<Address> addresses;


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public float getCapital() {
        return capital;
    }

    public void setCapital(float capital) {
        this.capital = capital;
    }

    public CapitalType getCapitalType() {
        return capitalType;
    }

    public void setCapitalType(CapitalType capitalType) {
        this.capitalType = capitalType;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBusinesses() {
        return businesses;
    }

    public void setBusinesses(String businesses) {
        this.businesses = businesses;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public CompanyLog getCompanyLog() {
        return companyLog;
    }

    public void setCompanyLog(CompanyLog companyLog) {
        this.companyLog = companyLog;
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

        if (!(o instanceof Company)) return false;

        Company that = (Company) o;

        return new EqualsBuilder().append(getCompanyName(), that.getCompanyName()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getCompanyName()).toHashCode();
    }

    @Override
    public String toString() {
        return "Company{" + "companyId=" + companyId + ", companyName='" + companyName + '\'' + ", owner='" + owner + '\'' + ", phoneNumber=" + phoneNumber + ", capital=" + capital + ", capitalType=" + capitalType + ", establishedDate=" + establishedDate + ", web='" + web + '\'' + ", businesses='" + businesses + '\'' + ", others='" + others + '\'' + '}';
    }
}
