package com.taiquan.dao.order;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.enums.GoodType;
import com.taiquan.domain.order.enums.TextureType;

import java.util.Date;
import java.util.List;

public interface GoodDao extends BaseDao<Good> {
    public List<Good> getGoodByGoodType(GoodType goodType);
    public List<Good> getGoodByTextureType(TextureType textureType);
    public List<Good> getGoodByTextureTypeAndGoodType(GoodType goodType,TextureType textureType);
    public List<Good> getGoodByAddTime(Date fromTime, Date toTime);

    int getGoodSizeBySupplier(Supplier supplier);
    public List<Good> getGoodsBySupplier(Supplier supplier);
}
