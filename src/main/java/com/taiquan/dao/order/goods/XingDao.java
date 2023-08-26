package com.taiquan.dao.order.goods;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.enums.goodDetailType.XingCaiType;
import com.taiquan.domain.order.goods.Xing;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface XingDao extends BaseDao<Xing> {
    public List<Xing> getXingByXingTypeAndTextureType(XingCaiType xingCaiType, @Nullable TextureType textureType);
}
