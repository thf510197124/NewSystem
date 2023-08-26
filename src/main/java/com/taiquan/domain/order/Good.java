package com.taiquan.domain.order;
import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.order.enums.GoodType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.goods.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@Entity
@Table(name ="good" )
@Inheritance(strategy = InheritanceType.JOINED)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Good extends BaseDomain implements Comparable<Good>{
    @Id @Column(name = "goodId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodId;
    @Enumerated(EnumType.ORDINAL)
    private GoodType goodType;
    //数量
    private int       amount;
    //单价,售出
    private float       salPrice;
    private String salUnit;
    //金额，售出金额
    @NumberFormat(style=NumberFormat.Style.CURRENCY)
    private float       sumOfSalsMoney;
    //购进的价格和金额
    @NumberFormat(style=NumberFormat.Style.CURRENCY)
    private float buyPrice;
    private String buyUnit;
    @NumberFormat(style=NumberFormat.Style.CURRENCY)
    private float sumOfBuyMoney;

    private String others;
    //毛利
    @NumberFormat(style=NumberFormat.Style.CURRENCY)
    private float profit;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Order.class)
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Supplier.class)
    @JoinColumn(name = "supplierId",referencedColumnName = "supplierId")
    private Supplier supplier;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Enumerated(EnumType.STRING)
    private TextureType textureType;

    private String spec;
    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public GoodType getGoodType() {
        return goodType;
    }

    public void setGoodType(GoodType goodType) {
        this.goodType = goodType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSalUnit() {
        return salUnit;
    }

    public void setSalUnit(String salUnit) {
        this.salUnit = salUnit;
    }

    public String getBuyUnit() {
        return buyUnit;
    }

    public void setBuyUnit(String buyUnit) {
        this.buyUnit = buyUnit;
    }

    public float getSalPrice() {
        return salPrice;
    }

    public void setSalPrice(float salPrice) {
        this.salPrice = salPrice;
    }

    public float getSumOfSalsMoney() {
        return sumOfSalsMoney;
    }

    public void setSumOfSalsMoney(float sumOfSalsMoney) {
        this.sumOfSalsMoney = sumOfSalsMoney;
    }

/*    public String getProduceFrom() {
        return produceFrom;
    }

    public void setProduceFrom(String produceFrom) {
        this.produceFrom = produceFrom;
    }*/

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public float getSumOfBuyMoney() {
        return sumOfBuyMoney;
    }

    public void setSumOfBuyMoney(float sumOfBuyMoney) {
        this.sumOfBuyMoney = sumOfBuyMoney;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public TextureType getTextureType() {
        return textureType;
    }

    public void setTextureType(TextureType textureType) {
        this.textureType = textureType;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("goodId", goodId)
                .append("goodType", goodType)
                .append("amount", amount)
                .append("salPrice", salPrice)
                .append("sumOfSalsMoney", sumOfSalsMoney)
                .append("buyPrice", buyPrice)
                .append("sumOfBuyMoney", sumOfBuyMoney)
                .append("others", others)
                .append("profit", profit)
                .append("unitType", unitType)
                .append("spec", spec)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;
        if (goodId==good.goodId){
            return true;
        }
        if (order != good.getOrder()){
            return false;
        }
        return new EqualsBuilder()
                .append(amount, good.amount)
                .append(salPrice, good.salPrice)
                .append(sumOfSalsMoney, good.sumOfSalsMoney)
                .append(buyPrice, good.buyPrice)
                .append(sumOfBuyMoney, good.sumOfBuyMoney)
                .append(profit, good.profit)
                .append(goodType, good.goodType)
                .append(others, good.others)
                .append(unitType, good.unitType)
                .append(textureType, good.textureType)
                .append(spec, good.spec)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(goodId)
                .append(goodType)
                .append(amount)
                .append(salPrice)
                .append(sumOfSalsMoney)
                .append(buyPrice)
                .append(sumOfBuyMoney)
                .append(others)
                .append(profit)
                .append(unitType)
                .append(textureType)
                .append(spec)
                .toHashCode();
    }

    @Override
    public int compareTo(@NotNull Good o) {
        if (goodType == o.getGoodType()){
            switch (goodType){
                case BANJUAN:
                    return ((BanJuan)this).compareTo((BanJuan)o);
                case GUAN:
                    return ((Guan)this).compareTo((Guan)o);
                case XING:
                    return ((Xing)this).compareTo((Xing)o);
                case LING:
                    return ((Ling)this).compareTo((Ling)o);
                case JIAGONG:
                    return ((JiaGong)this).compareTo((JiaGong)o);
                case OTHER:
                    return ((OtherServic)this).compareTo((OtherServic)o);
                default:
                    return 0;
            }
        }else{
            return goodType.compareTo(o.getGoodType());
        }
    }
}
