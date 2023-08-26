package com.taiquan.bean;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.goods.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.taiquan.utils.PrintUtil.println;

public class CustomerGoodBean extends BaseDomain {
    private int goodId;
    private String goodType;
    private String texture;
    private String spec;
    private String amountAndUnit;
    private String realThickChandi;
    private float weight;
    private String priceAndUnit;
    private float sums;
    private String others;

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getAmountAndUnit() {
        return amountAndUnit;
    }

    public void setAmountAndUnit(String amountAndUnit) {
        this.amountAndUnit = amountAndUnit;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPriceAndUnit() {
        return priceAndUnit;
    }

    public void setPriceAndUnit(String priceAndUnit) {
        this.priceAndUnit = priceAndUnit;
    }

    public float getSums() {
        return sums;
    }

    public void setSums(float sums) {
        this.sums = sums;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getRealThickChandi() {
        return realThickChandi;
    }

    public void setRealThickChandi(String realThickChandi) {
        this.realThickChandi = realThickChandi;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public static CustomerGoodBean getGoodBeanFromGood(Good good){
        CustomerGoodBean customerGoodBean = new CustomerGoodBean();
        //customerGoodBean.setGoodType(good.getGoodType().getName());
        customerGoodBean.setGoodId(good.getGoodId());
        if (good.getUnitType()!=null && !good.getUnitType().name().equals("null")) {
            customerGoodBean.setAmountAndUnit(good.getAmount() + " " + good.getUnitType());
        }else {
            customerGoodBean.setAmountAndUnit(good.getAmount()+"");
        }
        customerGoodBean.setOthers(good.getOthers());
        if (good.getSalUnit()!=null && !good.getSalUnit().equals("null")){
            customerGoodBean.setPriceAndUnit(good.getSalPrice() + "/" + good.getSalUnit());
        }else{
            customerGoodBean.setPriceAndUnit(good.getSalPrice() + "");
        }

        customerGoodBean.setSpec(good.getSpec());
        customerGoodBean.setSums(good.getSumOfSalsMoney());
        customerGoodBean.setTexture(good.getTextureType().getName());
        if (good.getGoodType().getName().equals("不锈钢板")){
            BanJuan banJuan = (BanJuan) good;
            customerGoodBean.setWeight(banJuan.getWeight());
            customerGoodBean.setGoodType(banJuan.getPlainType().toString());
            customerGoodBean.setRealThickChandi(banJuan.getChanDi() + banJuan.getRealThick());
        }else if (good.getGoodType().getName().equals("不锈钢管")){
            Guan guan = (Guan) good;
            customerGoodBean.setWeight(guan.getWeight());
            customerGoodBean.setGoodType(guan.getPupeType().toString());
            if (guan.getThick() != guan.getRealThick()){
                customerGoodBean.setRealThickChandi(guan.getRealThick()+"");
            }else{
                customerGoodBean.setRealThickChandi("");
            }
        }else if (good.getGoodType().getName().equals("不锈钢型材")){
            Xing xing = (Xing)good;
            customerGoodBean.setGoodType(xing.getXingCaiType().toString());
            customerGoodBean.setWeight(xing.getWeight());
        }else if (good.getGoodType().getName().equals("不锈钢零部件")){
            Ling ling = (Ling)good;
            customerGoodBean.setGoodType(ling.getLingBuJianType().toString());
        }else if (good.getGoodType().getName().equals("加工")){
            JiaGong jiaGong = (JiaGong)good;
            customerGoodBean.setGoodType(jiaGong.getJiaGongType().toString());
        }else if (good.getGoodType().getName().equals("其他")){
            OtherServic otherService = (OtherServic)good;
            customerGoodBean.setGoodType(otherService.getOtherServiceType().toString());
        }
        return customerGoodBean;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("goodType", goodType)
                .append("texture", texture)
                .append("spec", spec)
                .append("amountAndUnit", amountAndUnit)
                .append("weight", weight)
                .append("priceAndUnit", priceAndUnit)
                .append("sums", sums)
                .append("others", others)
                .toString();
    }
}
