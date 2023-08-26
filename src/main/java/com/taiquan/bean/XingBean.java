package com.taiquan.bean;

import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.*;
import com.taiquan.domain.order.enums.goodDetailType.XingCaiType;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.enums.unit.XingUnitType;
import com.taiquan.domain.order.goods.Xing;
import com.taiquan.utils.SpringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XingBean {
    private int xingId;
    private int       amount;
    private UnitType amountUnit;
    private float       salPrice;
    private XingUnitType salUnit;
    private float       sumOfSalsMoney;
    private float buyPrice;
    private XingUnitType buyUnit;
    private float sumOfBuyMoney;
    private String others;
    private float profit;
    private String supplierName;
    private TextureType textureType;
    private String spec;
    private XingCaiType xingCaiType;
    private Float weight;

    public int getXingId() {
        return xingId;
    }

    public void setXingId(int xingId) {
        this.xingId = xingId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public XingUnitType getSalUnit() {
        return salUnit;
    }

    public void setSalUnit(XingUnitType salUnit) {
        this.salUnit = salUnit;
    }

    public XingUnitType getBuyUnit() {
        return buyUnit;
    }

    public void setBuyUnit(XingUnitType buyUnit) {
        this.buyUnit = buyUnit;
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

    public XingCaiType getXingCaiType() {
        return xingCaiType;
    }

    public void setXingCaiType(XingCaiType xingCaiType) {
        this.xingCaiType = xingCaiType;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }


    public UnitType getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(UnitType amountUnit) {
        this.amountUnit = amountUnit;
    }
    public Xing getXing(XingBean xingBean){
        Xing xing = new Xing();
        xing.setGoodId(xingBean.getXingId());
        xing.setAmount(xingBean.getAmount());
        xing.setBuyPrice(xingBean.getBuyPrice());
        xing.setOthers(xingBean.getOthers());
        xing.setProfit(xingBean.getProfit());
        xing.setSalPrice(xingBean.getSalPrice());
        if (xingBean.getXingCaiType()==XingCaiType.光元 || xingBean.getXingCaiType()==XingCaiType.毛圆) {
            if (!xingBean.getSpec().contains("Φ"))
            xing.setSpec("Φ" + xingBean.getSpec());
        }else{
            xing.setSpec(xingBean.getSpec());
        }
        xing.setSumOfBuyMoney(xingBean.getSumOfBuyMoney());
        xing.setSumOfSalsMoney(xingBean.getSumOfSalsMoney());
        if (!xingBean.getSupplierName().equals("") && xingBean.getSupplierName() != null) {
            SupplierDao supplierDao = (SupplierDao) SpringUtils.getBean("supplierDao");
            Supplier supplier = supplierDao.getSupplierBySimpleName(xingBean.getSupplierName().trim());
            xing.setSupplier(supplier != null ? supplier : supplierDao.getSupplierBySupplierName(xingBean.getSupplierName().trim()));
        }
        xing.setTextureType(xingBean.getTextureType());
        xing.setSalUnit(xingBean.getSalUnit().name());
        xing.setBuyUnit(xingBean.getBuyUnit().name());
        xing.setUnitType(xingBean.getAmountUnit());
        xing.setXingCaiType(xingBean.getXingCaiType());
        xing.setGoodType(GoodType.XING);
        xing.setWeight(xingBean.getWeight());
        return xing;
    }

    public static XingBean xingToXingBean(Xing goodFrom){
        XingBean good = new XingBean();
        good.setXingId(goodFrom.getGoodId());
        good.setAmount(goodFrom.getAmount());
        good.setAmountUnit(goodFrom.getUnitType());
        good.setBuyPrice(goodFrom.getBuyPrice());
        good.setBuyUnit(XingUnitType.valueOf(goodFrom.getBuyUnit()));
        good.setOthers(goodFrom.getOthers());
        good.setXingCaiType(goodFrom.getXingCaiType());
        good.setProfit(goodFrom.getProfit());
        good.setSalPrice(goodFrom.getSalPrice());
        good.setSalUnit(XingUnitType.valueOf(goodFrom.getSalUnit()));
        good.setSumOfBuyMoney(goodFrom.getSumOfBuyMoney());
        good.setSumOfSalsMoney(goodFrom.getSumOfSalsMoney());
        good.setTextureType(goodFrom.getTextureType());
        good.setWeight(goodFrom.getWeight());
        if (goodFrom.getSupplier() != null){
            good.setSupplierName(goodFrom.getSupplier().getSimpleName());
        }
        if (goodFrom.getSpec() != null && goodFrom.getSpec().contains("Φ") ){
            good.setSpec(goodFrom.getSpec().substring(1));
        }else{
            good.setSpec(goodFrom.getSpec());
        }
        return good;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        XingBean xingBean = (XingBean) o;

        return new EqualsBuilder()
                .append(amount, xingBean.amount)
                .append(salPrice, xingBean.salPrice)
                .append(sumOfSalsMoney, xingBean.sumOfSalsMoney)
                .append(buyPrice, xingBean.buyPrice)
                .append(sumOfBuyMoney, xingBean.sumOfBuyMoney)
                .append(profit, xingBean.profit)
                .append(amountUnit, xingBean.amountUnit)
                .append(salUnit, xingBean.salUnit)
                .append(buyUnit, xingBean.buyUnit)
                .append(others, xingBean.others)
                .append(supplierName, xingBean.supplierName)
                .append(textureType, xingBean.textureType)
                .append(spec, xingBean.spec)
                .append(xingCaiType, xingBean.xingCaiType)
                .append(weight, xingBean.weight)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amount)
                .append(amountUnit)
                .append(salPrice)
                .append(salUnit)
                .append(sumOfSalsMoney)
                .append(buyPrice)
                .append(buyUnit)
                .append(sumOfBuyMoney)
                .append(others)
                .append(profit)
                .append(supplierName)
                .append(textureType)
                .append(spec)
                .append(xingCaiType)
                .append(weight)
                .toHashCode();
    }
}
