package com.taiquan.bean;

import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.*;
import com.taiquan.domain.order.enums.goodDetailType.PupeType;
import com.taiquan.domain.order.enums.unit.GuanUnitType;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.goods.Guan;
import com.taiquan.utils.SpringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuanBean {
    private int guanId;
    private int      amount;
    private UnitType amountUnit;
    private float       salPrice;
    private GuanUnitType salUnit;
    private float       sumOfSalsMoney;
    private float buyPrice;
    private GuanUnitType buyUnit;
    private float sumOfBuyMoney;
    private String others;
    private float profit;
    private String supplierName;
    private TextureType textureType;
    private PupeType pupeType;
    private String spec;
    private float thick;
    private float diameter;
    private float diameter2;
    private float realThick;
    private int length;
    private float weight;

    public int getGuanId() {
        return guanId;
    }

    public void setGuanId(int guanId) {
        this.guanId = guanId;
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

    public GuanUnitType getSalUnit() {
        return salUnit;
    }

    public void setSalUnit(GuanUnitType salUnit) {
        this.salUnit = salUnit;
    }

    public GuanUnitType getBuyUnit() {
        return buyUnit;
    }

    public void setBuyUnit(GuanUnitType buyUnit) {
        this.buyUnit = buyUnit;
    }

    public TextureType getTextureType() {
        return textureType;
    }

    public void setTextureType(TextureType textureType) {
        this.textureType = textureType;
    }

    public PupeType getPupeType() {
        return pupeType;
    }

    public void setPupeType(PupeType pupeType) {
        this.pupeType = pupeType;
    }

    public float getThick() {
        return thick;
    }

    public void setThick(float thick) {
        this.thick = thick;
    }

    public float getRealThick() {
        return realThick;
    }

    public void setRealThick(float realThick) {
        this.realThick = realThick;
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public float getDiameter2() {
        return diameter2;
    }

    public void setDiameter2(float diameter2) {
        this.diameter2 = diameter2;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public Guan guanBeanToGuan(GuanBean guanBean){
        Guan guan = new Guan();
        guan.setGoodId(guanBean.getGuanId());
        guan.setLength(guanBean.getLength());
        guan.setPupeType(guanBean.getPupeType());
        guan.setGoodType(GoodType.GUAN);
        guan.setRealThick(guanBean.getRealThick());
        guan.setThick(guanBean.getThick());
        guan.setWeight(guanBean.getWeight());
        guan.setAmount((int)guanBean.getAmount());
        guan.setBuyPrice(guanBean.getBuyPrice());
        guan.setOthers(guanBean.getOthers());
        guan.setProfit(guanBean.getProfit());
        guan.setSalPrice(guanBean.getSalPrice());
        guan.setSumOfBuyMoney(guanBean.getSumOfBuyMoney());
        guan.setSalUnit(guanBean.getSalUnit().name());
        guan.setBuyUnit(guanBean.getBuyUnit().name());
        guan.setDiameter(guanBean.getDiameter());
        guan.setDiameter2(guanBean.getDiameter2());
        guan.setTextureType(guanBean.getTextureType());
        guan.setUnitType(guanBean.getAmountUnit());
        if (!guanBean.getSupplierName().equals("") && guanBean.getSupplierName() != null) {
            SupplierDao supplierDao = (SupplierDao) SpringUtils.getBean("supplierDao");
            Supplier supplier = supplierDao.getSupplierBySimpleName(guanBean.getSupplierName().trim());
            guan.setSupplier(supplier != null ? supplier : supplierDao.getSupplierBySupplierName(guanBean.getSupplierName().trim()));
        }
        guan.setSumOfSalsMoney(guanBean.getSumOfSalsMoney());
        //为了让整数的直径和厚度，以整数形式展示
        String dia1="",thi = "",dia2="";
        if ((int)guanBean.getDiameter() == guanBean.getDiameter()){
            dia1 = dia1 + (int)guanBean.getDiameter();
        }else{dia1 = dia1 + guanBean.getDiameter(); }
        if ((int)guanBean.getThick() == guanBean.getThick()){
            thi = thi + (int)guanBean.getThick();
        }else{ thi = thi + guanBean.getThick(); }

        if(diameter2 ==0){
            guan.setSpec("Φ" + dia1 + "×" + thi);
        }else{
            if ((int)guanBean.getDiameter2() == guanBean.getDiameter2()){
                dia2 = dia2 + (int)guanBean.getDiameter2();
            }else{
                dia2=dia2 + guanBean.getDiameter2();
            }
            guan.setSpec(dia1 + "×" + dia2 + "×" + thi);
        }
        return guan;
    }
    public static GuanBean guanToGuanBean(Guan goodFrom){
        GuanBean good = new GuanBean();
        good.setGuanId(goodFrom.getGoodId());
        good.setAmount(goodFrom.getAmount());
        good.setLength(goodFrom.getLength());
        good.setAmountUnit(goodFrom.getUnitType());
        good.setBuyPrice(goodFrom.getBuyPrice());
        good.setBuyUnit(GuanUnitType.valueOf(goodFrom.getBuyUnit()));
        good.setOthers(goodFrom.getOthers());
        good.setPupeType(goodFrom.getPupeType());
        good.setProfit(goodFrom.getProfit());
        good.setThick(goodFrom.getThick());
        good.setRealThick(goodFrom.getRealThick());
        good.setDiameter(goodFrom.getDiameter());
        good.setDiameter2(goodFrom.getDiameter2());
        good.setSalPrice(goodFrom.getSalPrice());
        good.setSalUnit(GuanUnitType.valueOf(goodFrom.getSalUnit()));
        good.setSumOfBuyMoney(goodFrom.getSumOfBuyMoney());
        good.setSumOfSalsMoney(goodFrom.getSumOfSalsMoney());
        good.setTextureType(goodFrom.getTextureType());
        good.setWeight(goodFrom.getWeight());
        if (goodFrom.getSupplier() != null){
            good.setSupplierName(goodFrom.getSupplier().getSimpleName());
        }
        if(good.diameter2 ==0){
            good.setSpec(good.getDiameter() + "*" + good.getThick());
        }else{
            good.setSpec(good.getDiameter() + "*" + good.getDiameter2() + "*" + good.getThick());
        }
        return good;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GuanBean guanBean = (GuanBean) o;

        return new EqualsBuilder()
                .append(amount, guanBean.amount)
                .append(salPrice, guanBean.salPrice)
                .append(sumOfSalsMoney, guanBean.sumOfSalsMoney)
                .append(buyPrice, guanBean.buyPrice)
                .append(sumOfBuyMoney, guanBean.sumOfBuyMoney)
                .append(profit, guanBean.profit)
                .append(thick, guanBean.thick)
                .append(diameter, guanBean.diameter)
                .append(diameter2, guanBean.diameter2)
                .append(realThick, guanBean.realThick)
                .append(length, guanBean.length)
                .append(weight, guanBean.weight)
                .append(amountUnit, guanBean.amountUnit)
                .append(salUnit, guanBean.salUnit)
                .append(buyUnit, guanBean.buyUnit)
                .append(others, guanBean.others)
                .append(supplierName, guanBean.supplierName)
                .append(textureType, guanBean.textureType)
                .append(pupeType, guanBean.pupeType)
                .append(spec, guanBean.spec)
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
                .append(pupeType)
                .append(spec)
                .append(thick)
                .append(diameter)
                .append(diameter2)
                .append(realThick)
                .append(length)
                .append(weight)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("amount", amount)
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
                .append("pupeType", pupeType)
                .append("spec", spec)
                .append("thick", thick)
                .append("diameter", diameter)
                .append("diameter2", diameter2)
                .append("realThick", realThick)
                .append("length", length)
                .append("weight", weight)
                .toString();
    }
}
