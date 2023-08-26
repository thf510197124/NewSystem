package com.taiquan.dao.order.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.GoodDao;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.GoodType;
import com.taiquan.domain.order.enums.TextureType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("goodDao")
public class GoodDaoImpl extends BaseDaoHibernate5<Good> implements GoodDao{

    @Override
    public List<Good> getGoodByGoodType(GoodType goodType) {
        String hql = "from Good good where good.goodType = :goodType";
        return namedParamFindOne(hql,"goodType",goodType);
    }

    @Override
    public List<Good> getGoodByTextureType(TextureType textureType) {
        String hql = "from Good good where good.textureType = :textureType";
        return namedParamFindOne(hql,"textureType",textureType);
    }

    @Override
    public List<Good> getGoodByTextureTypeAndGoodType(GoodType goodType, TextureType textureType) {
        String hql = "from Good good where good.goodType = :goodType and good.textureType = :textureType";
        Map<String,Object> map = new HashMap<>();
        map.put("goodType",goodType);
        map.put("textureType",textureType);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Good> getGoodByAddTime(Date fromTime, Date toTime) {
        String hql = "from Good good where good.addTime between :fromTime and :toTime";
        Map<String,Object> map = new HashMap<>();
        map.put("fromTime",fromTime);
        map.put("toTime",toTime);
        return namedParamFind(hql,map);
    }

    @Override
    public int getGoodSizeBySupplier(Supplier supplier) {
        return getGoodsBySupplier(supplier).size();
    }

    @Override
    public List<Good> getGoodsBySupplier(Supplier supplier) {
        String hql = "select good from Good good left join good.order ord where good.supplier = :supplier order by ord.orderId desc";
        Map<String,Object> map = new HashMap<>();
        map.put("supplier",supplier);
        return namedParamFind(hql,map);
    }
}
