package com.taiquan.dao.order.goods;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.enums.goodDetailType.LingBuJianType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.goods.Ling;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface LingDao extends BaseDao<Ling> {
    public List<Ling> getLingByLingTypeAndTextureType(LingBuJianType lingBuJianType, @Nullable TextureType textureType);
}
