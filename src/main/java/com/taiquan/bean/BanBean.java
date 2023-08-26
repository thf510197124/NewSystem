package com.taiquan.bean;

import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.*;
import com.taiquan.domain.order.enums.goodDetailType.PlainType;
import com.taiquan.domain.order.enums.unit.BanJuanUnitType;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.goods.BanJuan;
import com.taiquan.utils.SpringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.taiquan.utils.PrintUtil.println;

@Component
public class BanBean {
    private int banId;
    private float       banAmount;
    private UnitType amountUnit;
    private float       salPrice;
    private BanJuanUnitType salUnit;
    private float       sumOfSalsMoney;
    private float buyPrice;
    private BanJuanUnitType buyUnit;
    private float sumOfBuyMoney;
    private String others;
    private float profit;
    private String supplierName;
    private TextureType textureType;
    private float thick;
    private int width;
    private String length;
    private float realThick;
    private PlainType plainType;
    private String chanDi;
    private float weight;

    public int getBanId() {
        return banId;
    }

    public void setBanId(int banId) {
        this.banId = banId;
    }

    public float getBanAmount() {
        return banAmount;
    }

    public void setBanAmount(float banAmount) {
        this.banAmount = banAmount;
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

    public BanJuanUnitType getSalUnit() {
        return salUnit;
    }

    public void setSalUnit(BanJuanUnitType salUnit) {
        this.salUnit = salUnit;
    }

    public BanJuanUnitType getBuyUnit() {
        return buyUnit;
    }

    public void setBuyUnit(BanJuanUnitType buyUnit) {
        this.buyUnit = buyUnit;
    }

    public TextureType getTextureType() {
        return textureType;
    }

    public void setTextureType(TextureType textureType) {
        this.textureType = textureType;
    }


    public float getThick() {
        return thick;
    }

    public void setThick(float thick) {
        this.thick = thick;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public float getRealThick() {
        return realThick;
    }

    public void setRealThick(float realThick) {
        this.realThick = realThick;
    }

    public PlainType getPlainType() {
        return plainType;
    }

    public void setPlainType(PlainType plainType) {
        this.plainType = plainType;
    }

    public String getChanDi() {
        return chanDi;
    }

    public void setChanDi(String chanDi) {
        this.chanDi = chanDi;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public UnitType getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(UnitType amountUnit) {
        this.amountUnit = amountUnit;
    }

    public BanJuan banBeanToBan(BanBean banBean){
        BanJuan banJuan = new BanJuan();
        banJuan.setGoodId(banBean.getBanId());
        banJuan.setChanDi(banBean.getChanDi());
        try{
            banJuan.setLength(Integer.parseInt(banBean.getLength()));
        }catch (NumberFormatException e){
            banJuan.setLength(0);
        }
        banJuan.setPlainType(banBean.getPlainType());
        banJuan.setGoodType(GoodType.BANJUAN);
        banJuan.setRealThick(banBean.getRealThick());
        banJuan.setThick(banBean.getThick());
        banJuan.setWidth(banBean.getWidth());
        banJuan.setWeight(banBean.getWeight());
        banJuan.setAmount((int)banBean.getBanAmount());
        banJuan.setBuyPrice(banBean.getBuyPrice());
        banJuan.setOthers(banBean.getOthers());
        banJuan.setProfit(banBean.getProfit());
        banJuan.setSalPrice(banBean.getSalPrice());
        banJuan.setSumOfBuyMoney(banBean.getSumOfBuyMoney());
        banJuan.setBuyUnit(banBean.getBuyUnit().name());
        banJuan.setSalUnit(banBean.getSalUnit().name());
        banJuan.setTextureType(banBean.getTextureType());
        banJuan.setUnitType(banBean.getAmountUnit());
        if (!banBean.getSupplierName().equals("") && banBean.getSupplierName() != null) {
            SupplierDao supplierDao = (SupplierDao) SpringUtils.getBean("supplierDao");
            Supplier supplier = supplierDao.getSupplierBySimpleName(banBean.getSupplierName().trim());
            banJuan.setSupplier(supplier != null ? supplier : supplierDao.getSupplierBySupplierName(banBean.getSupplierName().trim()));
        }
        banJuan.setSumOfSalsMoney(banBean.getSumOfSalsMoney());
        banJuan.setChanDi(banBean.getChanDi());
        if (banJuan.getLength()!= 0){
            banJuan.setSpec(banBean.getThick() + "×" + banJuan.getWidth() + "×" + banJuan.getLength());
        }else{
            banJuan.setSpec(banBean.getThick() + "×" + banJuan.getWidth() + "×" + "C");
        }
        return banJuan;
    }
    public static BanBean banJuanToBanBean(BanJuan goodFrom){
        BanBean good = new BanBean();
        good.setBanId(goodFrom.getGoodId());
        good.setBanAmount(goodFrom.getAmount());
        good.setLength(String.valueOf(goodFrom.getLength()));
        good.setAmountUnit(goodFrom.getUnitType());
        good.setBuyPrice(goodFrom.getBuyPrice());
        good.setBuyUnit(BanJuanUnitType.valueOf(goodFrom.getBuyUnit()));
        good.setChanDi(goodFrom.getChanDi());
        good.setOthers(goodFrom.getOthers());
        good.setPlainType(goodFrom.getPlainType());
        good.setProfit(goodFrom.getProfit());
        good.setThick(goodFrom.getThick());
        good.setRealThick(goodFrom.getRealThick());
        good.setSalPrice(goodFrom.getSalPrice());
        good.setSalUnit(BanJuanUnitType.valueOf(goodFrom.getSalUnit()));
        good.setSumOfBuyMoney(goodFrom.getSumOfBuyMoney());
        good.setSumOfSalsMoney(goodFrom.getSumOfSalsMoney());
        good.setTextureType(goodFrom.getTextureType());
        good.setWidth(goodFrom.getWidth());
        good.setWeight(goodFrom.getWeight());
        if (goodFrom.getSupplier() != null){
            good.setSupplierName(goodFrom.getSupplier().getSimpleName());
        }

        return good;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BanBean banBean = (BanBean) o;

        return new EqualsBuilder()
                .append(banAmount, banBean.banAmount)
                .append(salPrice, banBean.salPrice)
                .append(sumOfSalsMoney, banBean.sumOfSalsMoney)
                .append(buyPrice, banBean.buyPrice)
                .append(sumOfBuyMoney, banBean.sumOfBuyMoney)
                .append(profit, banBean.profit)
                .append(thick, banBean.thick)
                .append(width, banBean.width)
                .append(realThick, banBean.realThick)
                .append(weight, banBean.weight)
                .append(amountUnit, banBean.amountUnit)
                .append(salUnit, banBean.salUnit)
                .append(buyUnit, banBean.buyUnit)
                .append(others, banBean.others)
                .append(supplierName, banBean.supplierName)
                .append(textureType, banBean.textureType)
                .append(length, banBean.length)
                .append(plainType, banBean.plainType)
                .append(chanDi, banBean.chanDi)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(banAmount)
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
                .append(thick)
                .append(width)
                .append(length)
                .append(realThick)
                .append(plainType)
                .append(chanDi)
                .append(weight)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("amount", banAmount)
                .append("amountUnit", amountUnit)
                .append("salPrice", salPrice)
                .append("salUnit", salUnit)
                .append("sumOfSalsMoney", sumOfSalsMoney)
                .append("buyPrice", buyPrice)
                .append("buyUnit", buyUnit)
                .append("sumOfBuyMoney", sumOfBuyMoney)
                .append("others", others)
                .append("profit", profit)
                .append("supplierName", supplierName)
                .append("textureType", textureType)
                .append("thick", thick)
                .append("width", width)
                .append("length", length)
                .append("realThick", realThick)
                .append("plainType", plainType)
                .append("chanDi", chanDi)
                .append("weight", weight)
                .toString();
    }
}
