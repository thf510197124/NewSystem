package com.taiquan.bean;

import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.goodDetailType.GoodDetailType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.exception.NoSuchEnumException;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashSet;
import java.util.Set;

public class SupplierBean {
    private int supplierId;
    private String supplierName;
    private String simpleName;
    private String workAddress;
    private String wallAddress;
    private String kaiPingAddress;
    private String[] textures;
    private String[] goodType;
    private String others;

    public static Supplier getSupplier(SupplierBean bean) throws NoSuchEnumException {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(bean.getSupplierId());
        supplier.setSupplierName(bean.getSupplierName());
        supplier.setSimpleName(bean.getSimpleName());
        supplier.setWallAddress(bean.getWallAddress());
        supplier.setWorkAddress(bean.getWorkAddress());
        supplier.setKaiPingAddress(bean.getKaiPingAddress());
        Set<TextureType> textureTypes = new HashSet<>();
        for (String text : bean.getTextures()){
            textureTypes.add(TextureType.getTexture(text));
        }
        supplier.setTextureTypes(textureTypes);
        Set<GoodDetailType> goodDetailTypes = new HashSet<>();
        for (String goodTy : bean.getGoodType()){
            goodDetailTypes.add(GoodDetailType.valueOf(goodTy));
        }
        supplier.setGoodType(goodDetailTypes);
        supplier.setOthers(bean.getOthers());
        return supplier;
    }
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

    public String[] getTextures() {
        return textures;
    }

    public void setTextures(String[] textures) {
        this.textures = textures;
    }

    public String[] getGoodType() {
        return goodType;
    }

    public void setGoodType(String[] goodType) {
        this.goodType = goodType;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("supplierId", supplierId)
                .append("supplierName", supplierName)
                .append("simpleName", simpleName)
                .append("workAddress", workAddress)
                .append("wallAddress", wallAddress)
                .append("kaiPingAddress", kaiPingAddress)
                .append("textures", textures)
                .append("goodType", goodType)
                .toString();
    }
}
