package com.taiquan.dao.order;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.GoodType;
import com.taiquan.domain.order.enums.goodDetailType.GoodDetailType;
import com.taiquan.domain.order.enums.goodDetailType.JiaGongType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.utils.Page;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface SupplierDao extends BaseDao<Supplier> {
    @Override
    public void remove(Supplier supplier);
    public Supplier getSupplierBySupplierName(String supplierName);
    public Page<Supplier> getSuppliersByGoodAndTextureType(@Nullable GoodDetailType goodType, @Nullable TextureType textureType,int pageNo,int pageSize);

    Supplier getSupplierBySimpleName(String supplier);

    Page<Supplier> getAllSuppliers(int pageNo, int pageSize);

    List<String> getSupplierNameList(String nameKey);
    List<Supplier> getSupplierByKey(String nameKey);

}
