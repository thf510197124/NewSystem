package com.taiquan.bean;

import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.*;
import com.taiquan.domain.order.enums.goodDetailType.LingBuJianType;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.goods.Ling;
import com.taiquan.utils.SpringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LingBean {
    private int lingId;
    private int       amount;
    private UnitType amountUnit;
    private float       salPrice;
    private float       sumOfSalsMoney;
    private float buyPrice;
    private float sumOfBuyMoney;
    private String others;
    private float profit;
    private String supplierName;
    private TextureType textureType;
    private String spec;
    private LingBuJianType lingBuJianType;

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

    public LingBuJianType getLingBuJianType() {
        return lingBuJianType;
    }

    public void setLingBuJianType(LingBuJianType lingBuJianType) {
        this.lingBuJianType = lingBuJianType;
    }

    public int getLingId() {
        return lingId;
    }

    public void setLingId(int lingId) {
        this.lingId = lingId;
    }

    public Ling getLing(LingBean lingBean){
        Ling ling = new Ling();
        ling.setGoodId(lingBean.getLingId());
        ling.setAmount(lingBean.getAmount());
        ling.setBuyPrice(lingBean.getBuyPrice());
        ling.setOthers(lingBean.getOthers());
        ling.setProfit(lingBean.getProfit());
        ling.setSalPrice(lingBean.getSalPrice());
        ling.setSpec(lingBean.getSpec());
        ling.setSumOfBuyMoney(lingBean.getSumOfBuyMoney());
        ling.setSumOfSalsMoney(lingBean.getSumOfSalsMoney());
        if (!lingBean.getSupplierName().equals("") && lingBean.getSupplierName() != null) {
            SupplierDao supplierDao = (SupplierDao) SpringUtils.getBean("supplierDao");
            Supplier supplier = supplierDao.getSupplierBySimpleName(lingBean.getSupplierName().trim());
            ling.setSupplier(supplier != null ? supplier : supplierDao.getSupplierBySupplierName(lingBean.getSupplierName().trim()));
        }
        ling.setTextureType(lingBean.getTextureType());
        ling.setSalUnit(lingBean.getAmountUnit().name());
        ling.setBuyUnit(lingBean.getAmountUnit().name());
        ling.setUnitType(UnitType.valueOf(lingBean.getAmountUnit().name()));
        ling.setLingBuJianType(lingBean.getLingBuJianType());
        ling.setGoodType(GoodType.LING);
        return ling;
    }
    public static LingBean lingToLingBean(Ling goodFrom){
        LingBean good = new LingBean();
        good.setLingId(goodFrom.getGoodId());
        good.setAmount(goodFrom.getAmount());
        good.setAmountUnit(goodFrom.getUnitType());
        good.setBuyPrice(goodFrom.getBuyPrice());
        good.setOthers(goodFrom.getOthers());
        good.setLingBuJianType(goodFrom.getLingBuJianType());
        good.setProfit(goodFrom.getProfit());
        good.setSalPrice(goodFrom.getSalPrice());
        good.setSumOfBuyMoney(goodFrom.getSumOfBuyMoney());
        good.setSumOfSalsMoney(goodFrom.getSumOfSalsMoney());
        good.setTextureType(goodFrom.getTextureType());
        if (goodFrom.getSupplier() != null){
            good.setSupplierName(goodFrom.getSupplier().getSimpleName());
        }
        good.setSpec(goodFrom.getSpec());
        return good;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LingBean lingBean = (LingBean) o;

        return new EqualsBuilder()
                .append(amount, lingBean.amount)
                .append(salPrice, lingBean.salPrice)
                .append(sumOfSalsMoney, lingBean.sumOfSalsMoney)
                .append(buyPrice, lingBean.buyPrice)
                .append(sumOfBuyMoney, lingBean.sumOfBuyMoney)
                .append(profit, lingBean.profit)
                .append(amountUnit, lingBean.amountUnit)
                .append(others, lingBean.others)
                .append(supplierName, lingBean.supplierName)
                .append(textureType, lingBean.textureType)
                .append(spec, lingBean.spec)
                .append(lingBuJianType, lingBean.lingBuJianType)
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
                .append(textureType)
                .append(spec)
                .append(lingBuJianType)
                .toHashCode();
    }
}
