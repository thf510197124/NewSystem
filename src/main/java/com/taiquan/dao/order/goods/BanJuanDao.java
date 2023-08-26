package com.taiquan.dao.order.goods;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.enums.goodDetailType.PlainType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.goods.BanJuan;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface BanJuanDao extends BaseDao<BanJuan> {
    public List<BanJuan> getBanJuanByPlainAndTextureType(PlainType plainType,@Nullable TextureType textureType);
    public List<BanJuan> getBanJuanByThickAndTextureType(float thick,@Nullable TextureType textureType);
}
