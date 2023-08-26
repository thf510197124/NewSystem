package com.taiquan.dao.order.goods;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.goods.Guan;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface GuanDao extends BaseDao<Guan> {
    public List<Guan> getGuanByThickAndDiameterAndTextureType(float thick, float diameter, @Nullable TextureType textureType);
}
