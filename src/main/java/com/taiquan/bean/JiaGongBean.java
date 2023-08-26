package com.taiquan.bean;

import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.*;
import com.taiquan.domain.order.enums.goodDetailType.JiaGongType;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.goods.JiaGong;
import com.taiquan.utils.SpringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JiaGongBean {
    private int jiaId;
    private int      amount;
    private float       salPrice;
    private UnitType amountUnit;
    private float       sumOfSalsMoney;
    private float buyPrice;
    private float sumOfBuyMoney;
    private String others;
    private float profit;
    private String supplierName;
    private JiaGongType jiaGongType;

    public int getJiaId() {
        return jiaId;
    }

    public void setJiaId(int jiaId) {
        this.jiaId = jiaId;
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

    public UnitType getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(UnitType amountUnit) {
        this.amountUnit = amountUnit;
    }

    public JiaGongType getJiaGongType() {
        return jiaGongType;
    }

    public void setJiaGongType(JiaGongType jiaGongType) {
        this.jiaGongType = jiaGongType;
    }

    public JiaGong getJiaGong(JiaGongBean jiaGongBean){
        JiaGong jiaGong = new JiaGong();
        jiaGong.setGoodId(jiaGongBean.getJiaId());
        jiaGong.setAmount(jiaGongBean.getAmount());
        jiaGong.setBuyPrice(jiaGongBean.getBuyPrice());
        jiaGong.setOthers(jiaGongBean.getOthers());
        jiaGong.setProfit(jiaGongBean.getProfit());
        jiaGong.setSalPrice(jiaGongBean.getSalPrice());
        jiaGong.setSumOfBuyMoney(jiaGongBean.getSumOfBuyMoney());
        jiaGong.setSumOfSalsMoney(jiaGongBean.getSumOfSalsMoney());
        if (!jiaGongBean.getSupplierName().equals("") && jiaGongBean.getSupplierName() != null) {
            SupplierDao supplierDao = (SupplierDao) SpringUtils.getBean("supplierDao");
            Supplier supplier = supplierDao.getSupplierBySimpleName(jiaGongBean.getSupplierName().trim());
            jiaGong.setSupplier(supplier != null ? supplier : supplierDao.getSupplierBySupplierName(jiaGongBean.getSupplierName().trim()));
        }
        jiaGong.setSalUnit(jiaGongBean.getAmountUnit().name());
        jiaGong.setBuyUnit(jiaGongBean.getAmountUnit().name());
        jiaGong.setUnitType(UnitType.valueOf(jiaGongBean.getAmountUnit().name()));
        jiaGong.setJiaGongType(jiaGongBean.getJiaGongType());
        jiaGong.setGoodType(GoodType.JIAGONG);
        jiaGong.setOthers(jiaGongBean.getOthers());
        jiaGong.setTextureType(TextureType.Others);
        return jiaGong;
    }
    public static JiaGongBean jiaGongToBean(JiaGong goodFrom){
        JiaGongBean good = new JiaGongBean();
        good.setJiaId(goodFrom.getGoodId());
        good.setAmount(goodFrom.getAmount());
        good.setAmountUnit(goodFrom.getUnitType());
        good.setBuyPrice(goodFrom.getBuyPrice());
        good.setOthers(goodFrom.getOthers());
        good.setJiaGongType(goodFrom.getJiaGongType());
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

        JiaGongBean that = (JiaGongBean) o;

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
                .append(jiaGongType, that.jiaGongType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amount)
                .append(salPrice)
                .append(amountUnit)
                .append(sumOfSalsMoney)
                .append(buyPrice)
                .append(sumOfBuyMoney)
                .append(others)
                .append(profit)
                .append(supplierName)
                .append(jiaGongType)
                .toHashCode();
    }
}
