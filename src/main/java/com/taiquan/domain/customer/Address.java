package com.taiquan.domain.customer;

import com.taiquan.domain.customerEnums.AddressType;
import com.taiquan.utils.AddressUtils;
import com.taiquan.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.annotations.Cache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "address")
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Address extends BaseDomain implements Comparable<Address>{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    @Enumerated(EnumType.ORDINAL)
    private AddressType addressType = AddressType.工厂地址;
    private String     simple;
    private String     formatterAddress;
    @Column(precision = 10,scale = 7)
    private BigDecimal longitude;
    @Column(precision = 10,scale = 7)
    private BigDecimal latitude;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Company.class)
    @JoinColumn(name = "companyId",referencedColumnName = "companyId")
    private Company    company;
    public Address() {
    }
    public Address(@Nullable AddressType addressType, String simple){

        this.simple = simple;
        this.formatterAddress = AddressUtils.formatAddress(simple);
        try {
            this.longitude = AddressUtils.getGeocoderAngel(simple).getLng();
            this.latitude = AddressUtils.getGeocoderAngel(simple).getLat();
        }catch (Exception e){
            this.longitude = new BigDecimal(0);
            this.latitude = new BigDecimal(0);
        }
        try{
            this.addressType = addressType;
        }catch (Exception e) {
            this.addressType = AddressType.注册地址;
        }
    }
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public String getFormatterAddress() {
        return formatterAddress;
    }

    public void setFormatterAddress(String formatterAddress) {
        this.formatterAddress = formatterAddress;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", addressType=" + addressType + ", simple='" + simple + '\'' + ", formatterAddress='" + formatterAddress + '\'' + ", longitude=" + longitude + ", latitude=" + latitude + ", company=" + company + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Address)) return false;

        Address that = (Address) o;

        return new EqualsBuilder().append(getSimple(), that.getSimple()).append(getFormatterAddress(), that.getFormatterAddress()).append(getLongitude(), that.getLongitude()).append(getLatitude(), that.getLatitude()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getSimple()).append(getFormatterAddress()).append(getLongitude()).append(getLatitude()).toHashCode();
    }

    @Override
    public int compareTo(@NotNull Address o) {
        return addressType.compareTo(o.addressType);
    }
}
