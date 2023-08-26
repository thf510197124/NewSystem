package com.taiquan.dao.order.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.SupplierDao;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.GoodType;
import com.taiquan.domain.order.enums.goodDetailType.GoodDetailType;
import com.taiquan.domain.order.enums.goodDetailType.JiaGongType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.utils.Page;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

@Repository("supplierDao")
public class SupplierDaoImpl extends BaseDaoHibernate5<Supplier> implements SupplierDao {
    @Override
    public void remove(Supplier supplier){
        super.remove(supplier);
    }
    @Override
    public Supplier getSupplierBySupplierName(String supplierName) {
        String hql = "from Supplier sup where sup.supplierName = :supplierName";
        try {
            return namedParamFindOne(hql,"supplierName",supplierName).get(0);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Page<Supplier> getSuppliersByGoodAndTextureType(@Nullable GoodDetailType goodType, @Nullable TextureType textureType,int pageNo,int pageSize) {
        Map<String,Object> map = new HashMap<>();
        Page<Supplier> supplierPage=null;
        List<Supplier> suppliers1 = new ArrayList<>();
        List<Supplier> suppliers2 = new ArrayList<>();
        if (goodType != null && textureType == null) {
            String hql = new String("select distinct sup from Supplier sup join sup.goodType good where good = :goodType");
            map.put("goodType", goodType);
            supplierPage = pagedQuery(hql,pageNo,pageSize,map);
        }else if (textureType != null && goodType == null ){
            String hql = new String("select distinct sup from Supplier sup join sup.textureTypes text where text = :textureType");
            map.put("textureType",textureType);
            supplierPage = pagedQuery(hql,pageNo,pageSize,map);

        }else if (textureType != null){
            String hql = new String("select distinct sup from Supplier sup  join sup.textureTypes text " +
                    "  join sup.goodType good where (text = :textureType and good = :goodType)");
            map.put("goodType",goodType);
            map.put("textureType",textureType);
            supplierPage = pagedQuery(hql,pageNo,pageSize,map);
        }
        return supplierPage;
    }

    @Override
    public Supplier getSupplierBySimpleName(String supplier) {
        String hql = "from Supplier sup where sup.simpleName = :supplier";
        try {
            return namedParamFindOne(hql,"supplier",supplier).get(0);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Page<Supplier> getAllSuppliers(int pageNo, int pageSize) {
        String hql = "from Supplier";
        Map map = null;
        return pagedQuery(hql,pageNo,pageSize,map);
    }

    @Override
    public List<String> getSupplierNameList(String nameKey) {
        String sql ="select distinct supplierName from supplier where supplierName like '%" + nameKey + "%' or simpleName like '%" + nameKey + "%' limit 10" ;
        return sqlQueryForField(sql);
    }

    @Override
    public List<Supplier> getSupplierByKey(String nameKey) {
        String hql = "from Supplier sup where sup.supplierName like :nameKey or others like :nameKey";
        Map<String,Object> map = new HashMap<>();
        map.put("nameKey","%"+ nameKey + "%");
        return namedParamFind(hql,map);
    }


}
