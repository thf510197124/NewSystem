package com.taiquan.domain.order;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customer.Employee;
import com.taiquan.domain.order.enums.goodDetailType.GoodDetailType;
import com.taiquan.domain.order.enums.TextureType;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "supplier")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Supplier extends BaseDomain {
    @Id @Column(name = "supplierId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;
    @Column(name = "supplierName",unique = true)
    private String supplierName;
    @Column(name = "simpleName",unique = true)
    private String simpleName;
    private String workAddress;
    private String wallAddress;
    private String kaiPingAddress;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "supply_goodDetailsType",joinColumns = @JoinColumn(name = "supplierId",nullable = false))
    @Column(name = "goodDetailsType")
    @Enumerated(EnumType.STRING)
    private Set<GoodDetailType> goodType;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "supply_texture",joinColumns = @JoinColumn(name = "supplierId",nullable = false))
    @Column(name = "textureType")
    @Enumerated(EnumType.STRING)
    private Set<TextureType> textureTypes;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = Employee.class,mappedBy = "supplier")
    private List<Employee> employees;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = Good.class,mappedBy = "supplier")
    private Set<Good> goods;
    private String others;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Set<GoodDetailType> getGoodType() {
        return goodType;
    }

    public void setGoodType(Set<GoodDetailType> goodType) {
        this.goodType = goodType;
    }

    public Set<TextureType> getTextureTypes() {
        return textureTypes;
    }

    public void setTextureTypes(Set<TextureType> textureTypes) {
        this.textureTypes = textureTypes;
    }

    public List<Employee> getEmployees() {
        if (employees.size() > 1) {
            Collections.sort(employees);
        }
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Set<Good> getGoods() {
        return goods;
    }

    public void setGoods(Set<Good> goods) {
        this.goods = goods;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWallAddress() {
        return wallAddress;
    }

    public void setWallAddress(String wallAddress) {
        this.wallAddress = wallAddress;
    }

    public String getKaiPingAddress() {
        return kaiPingAddress;
    }

    public void setKaiPingAddress(String kaiPingAddress) {
        this.kaiPingAddress = kaiPingAddress;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
