package com.taiquan.service;

import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Supplier;
import com.taiquan.exception.NoSuchEnumException;
import com.taiquan.utils.Page;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface SupplyService {
    Supplier getSupplierBySimpleName(String supplier);
    Supplier addSupplier(Supplier supplier);
    Supplier updateSupplier(Supplier supplier);
    Supplier updateOrSaveSupplier(Supplier supplier);
    Page<Supplier> getSupplierByKeys(String key,int pageNo,int pageSize);

    Page<Supplier> getAllSuppliers(int pageNo, int pageSize);

    Supplier getSupplierById(int supplierId);

    void deleteSupplier(int supplierId);

    List<String> getSupplierNameList(String nameKey);

    boolean supplierIsExisted(String name);

    boolean supplierSimpleIsExisted(String name);

    Page<Supplier> getSupplierByType(@Nullable String goodDetailType, @Nullable String textureType,int pageNo,int pageSize) throws NoSuchEnumException;
    public List<Good> getGoodsBySupplier(Supplier supplier);
}
