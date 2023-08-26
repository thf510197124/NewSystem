package com.taiquan.bean;

import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.enums.unit.UnitType;
import com.taiquan.domain.order.goods.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


public class InnerGoodBean{
    private int goodId;
    private int       amount;
    private float       salPrice;
    private String salUnit;
    private float       sumOfSalsMoney;
    private float buyPrice;
    private String buyUnit;
    private float sumOfBuyMoney;
    private String others;
    private float profit;
    private String supplier;
    private UnitType unitType;
    private String textureType;
    private String spec;
    //公差
    private float realThick;
    private String  detailGoodType;
    private String chanDi;
    private float weight;
    public static InnerGoodBean goodBeanFromGood(Good good) {
        InnerGoodBean goodBean = new InnerGoodBean();
        goodBean.setGoodId(good.getGoodId());
        goodBean.setAmount(good.getAmount());
        goodBean.setSalPrice(good.getSalPrice());
        goodBean.setSalUnit(good.getSalUnit());
        goodBean.setSumOfSalsMoney(good.getSumOfSalsMoney());
        goodBean.setBuyPrice(good.getBuyPrice());
        goodBean.setBuyUnit(good.getBuyUnit());
        goodBean.setSumOfBuyMoney(good.getSumOfBuyMoney());
        goodBean.setOthers(good.getOthers());
        goodBean.setProfit(good.getProfit());
        if (good.getSupplier() != null) {
            goodBean.setSupplier(good.getSupplier().getSimpleName());
        }
        goodBean.setUnitType(good.getUnitType());
        goodBean.setTextureType(good.getTextureType().getName());
        goodBean.setSpec(good.getSpec());
        if (good.getGoodType().getName().equals("不锈钢板")) {
            BanJuan banJuan = (BanJuan) good;
            goodBean.setWeight(banJuan.getWeight());
            goodBean.setRealThick(banJuan.getRealThick());
            goodBean.setChanDi(banJuan.getChanDi());
            goodBean.setDetailGoodType(banJuan.getPlainType().toString());
        } else if (good.getGoodType().getName().equals("不锈钢管")) {
            Guan guan = (Guan) good;
            goodBean.setWeight(guan.getWeight());
            goodBean.setDetailGoodType(guan.getPupeType().toString());
            if (guan.getThick() != guan.getRealThick()) {
                goodBean.setRealThick(guan.getRealThick());
            }
        } else if (good.getGoodType().getName().equals("不锈钢型材")) {
            Xing xing = (Xing) good;
            goodBean.setWeight(xing.getWeight());
            goodBean.setDetailGoodType(xing.getXingCaiType().toString());
        }else if (good.getGoodType().getName().equals("不锈钢零部件")){
            Ling ling = (Ling)good;
            goodBean.setDetailGoodType(ling.getLingBuJianType().toString());
        }else if (good.getGoodType().getName().equals("加工")){
            JiaGong jiaGong = (JiaGong)good;
            goodBean.setDetailGoodType(jiaGong.getJiaGongType().toString());
        }else if (good.getGoodType().getName().equals("其他")){
            OtherServic otherService = (OtherServic)good;
            goodBean.setDetailGoodType(otherService.getOtherServiceType().toString());
        }
        return goodBean;
    }
    public int getGoodId() {
        return goodId;
    }
    public void setGoodId(int goodId) {
        this.goodId = goodId;
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
    public String getSalUnit() {
        return salUnit;
    }
    public void setSalUnit(String salUnit) {
        this.salUnit = salUnit;
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
    public String getBuyUnit() {
        return buyUnit;
    }
    public void setBuyUnit(String buyUnit) {
        this.buyUnit = buyUnit;
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
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public UnitType getUnitType() {
        return unitType;
    }
    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public String getTextureType() {
        return textureType;
    }

    public void setTextureType(String textureType) {
        this.textureType = textureType;
    }

    public String getSpec() {
        return spec;
    }
    public void setSpec(String spec) {
        this.spec = spec;
    }
    public float getRealThick() {
        return realThick;
    }
    public void setRealThick(float realThick) {
        this.realThick = realThick;
    }
    public String getDetailGoodType() {
        return detailGoodType;
    }
    public void setDetailGoodType(String detailGoodType) {
        this.detailGoodType = detailGoodType;
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

}
