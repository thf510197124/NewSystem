package com.taiquan.service.impl;

import com.taiquan.dao.order.GoodDao;
import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.enums.goodDetailType.GoodDetailType;
import com.taiquan.exception.NoSuchEnumException;
import com.taiquan.service.SupplyService;
import com.taiquan.utils.Page;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.taiquan.utils.PrintUtil.println;

@Service("supplyService")
public class SupplyServiceImpl implements SupplyService {
    @Autowired
    SupplierDao supplierDao;
    @Autowired
    GoodDao goodDao;
    @Override
    public Supplier getSupplierBySimpleName(String supplier) {
        return supplierDao.getSupplierBySimpleName(supplier);
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        supplierDao.update(supplier);
        return supplier;
    }

    @Override
    public Supplier updateOrSaveSupplier(Supplier supplier) {
        supplierDao.saveOrUpdate(supplier);
        return supplier;
    }

    @Override
    public Page<Supplier> getSupplierByKeys(String key,int pageNo,int pageSize) {
        List<Supplier> suppliers = supplierDao.getSupplierByKey(key);
        Page<Supplier> supplierPage = new Page<>(pageNo,pageSize,suppliers);
        return supplierPage;
    }

    @Override
    public Page<Supplier> getAllSuppliers(int pageNo, int pageSize) {
        return supplierDao.getAllSuppliers(pageNo,pageSize);
    }

    @Override
    public Supplier getSupplierById(int supplierId) {
        return supplierDao.get(supplierId);
    }

    @Override
    public void deleteSupplier(int supplierId) {
        Supplier supplier = supplierDao.get(supplierId);
        supplierDao.remove(supplier);
    }

    @Override
    public List<String> getSupplierNameList(String nameKey) {
        return supplierDao.getSupplierNameList(nameKey);
    }

    @Override
    public boolean supplierIsExisted(String name) {
        Supplier supplier = supplierDao.getSupplierBySupplierName(name.trim());
        return supplier != null;
    }

    @Override
    public boolean supplierSimpleIsExisted(String name) {
        Supplier supplier = supplierDao.getSupplierBySimpleName(name.trim());
        return supplier != null;
    }

    @Override
    public Page<Supplier> getSupplierByType(String goodDetailType, String textureType, int pageNo, int pageSize) throws NoSuchEnumException {
        GoodDetailType goodDetailType1 ;
        TextureType  textureType1;
        try {
            goodDetailType1 = GoodDetailType.getGoodDetailType(goodDetailType);
            textureType1 = TextureType.getTexture(textureType);
        }catch (NoSuchEnumException e){
            throw new NoSuchEnumException("您输入的产品或者材质不正确");
        }

        return supplierDao.getSuppliersByGoodAndTextureType(goodDetailType1,textureType1,pageNo,pageSize);
    }
    @Override
    public List<Good> getGoodsBySupplier(Supplier supplier){
        return goodDao.getGoodsBySupplier(supplier);
    }
}
