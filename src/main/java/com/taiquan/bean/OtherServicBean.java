package com.taiquan.bean;

import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.*;
import com.taiquan.domain.order.enums.goodDetailType.OtherServiceType;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.goods.OtherServic;
import com.taiquan.utils.SpringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherServicBean {
    private int otherId;
    private int       amount;
    private UnitType amountUnit;
    private float       salPrice;
    private float       sumOfSalsMoney;
    private float buyPrice;
    private float sumOfBuyMoney;
    private String others;
    private float profit;
    private String supplierName;
    private OtherServiceType otherServiceType;

    public int getOtherId() {
        return otherId;
    }

    public void setOtherId(int otherId) {
        this.otherId = otherId;
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
    public OtherServiceType getOtherServiceType() {
        return otherServiceType;
    }
    public void setOtherServiceType(OtherServiceType otherServiceType) {
        this.otherServiceType = otherServiceType;
    }

    public UnitType getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(UnitType amountUnit) {
        this.amountUnit = amountUnit;
    }

    public OtherServic getOtherServic(OtherServicBean otherServicBean){
        OtherServic otherServic = new OtherServic();
        otherServic.setGoodId(otherServicBean.getOtherId());
        otherServic.setAmount(otherServicBean.getAmount());
        otherServic.setBuyPrice(otherServicBean.getBuyPrice());
        otherServic.setOtherServiceType(otherServicBean.getOtherServiceType());
        otherServic.setGoodType(GoodType.OTHER);
        otherServic.setOthers(otherServicBean.getOthers());
        otherServic.setProfit(otherServicBean.getProfit());
        otherServic.setSalPrice(otherServicBean.getSalPrice());
        otherServic.setSumOfBuyMoney(otherServicBean.getSumOfBuyMoney());
        otherServic.setSumOfSalsMoney(otherServicBean.getSumOfSalsMoney());
        if (!otherServicBean.getSupplierName().equals("") && otherServicBean.getSupplierName() != null) {
            SupplierDao supplierDao = (SupplierDao) SpringUtils.getBean("supplierDao");
            Supplier supplier = supplierDao.getSupplierBySimpleName(otherServicBean.getSupplierName().trim());
            otherServic.setSupplier(supplier != null ? supplier : supplierDao.getSupplierBySupplierName(otherServicBean.getSupplierName().trim()));
        }
        otherServic.setBuyUnit(otherServicBean.getAmountUnit().name());
        otherServic.setSalUnit(otherServicBean.getAmountUnit().name());
        otherServic.setUnitType(UnitType.valueOf(otherServicBean.getAmountUnit().name()));
        otherServic.setTextureType(TextureType.Others);
        return otherServic;
    }
    public static OtherServicBean otherServToOtherBean(OtherServic goodFrom){
        OtherServicBean good = new OtherServicBean();
        good.setOtherId(goodFrom.getGoodId());
        good.setAmount(goodFrom.getAmount());
        good.setAmountUnit(goodFrom.getUnitType());
        good.setBuyPrice(goodFrom.getBuyPrice());
        good.setOthers(goodFrom.getOthers());
        good.setOtherServiceType(goodFrom.getOtherServiceType());
        good.setProfit(goodFrom.getProfit());
        good.setSalPrice(goodFrom.getSalPrice());
        good.setSumOfBuyMoney(goodFrom.getSumOfBuyMoney());
        good.setSumOfSalsMoney(goodFrom.getSumOfSalsMoney());
        if (goodFrom.getSupplier() != null){
            good.setSupplierName(goodFrom.getSupplier().getSimpleName());
        }
        return good;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OtherServicBean that = (OtherServicBean) o;

        return new EqualsBuilder()
                .append(amount, that.amount)
                .append(salPrice, that.salPrice)
                .append(sumOfSalsMoney, that.sumOfSalsMoney)
                .append(buyPrice, that.buyPrice)
                .append(sumOfBuyMoney, that.sumOfBuyMoney)
                .append(profit, that.profit)
                .append(amountUnit, that.amountUnit)
                .append(others, that.others)
                .append(supplierName, that.supplierName)
                .append(otherServiceType, that.otherServiceType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amount)
                .append(amountUnit)
                .append(salPrice)
                .append(sumOfSalsMoney)
                .append(buyPrice)
                .append(sumOfBuyMoney)
                .append(others)
                .append(profit)
                .append(supplierName)
                .append(otherServiceType)
                .toHashCode();
    }
}
