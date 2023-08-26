package com.taiquan.dao.order.impl.good;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.goods.BanJuanDao;
import com.taiquan.domain.order.enums.goodDetailType.PlainType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.goods.BanJuan;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("banJuanDao")
public class BanJuanDaoImpl extends BaseDaoHibernate5<BanJuan> implements BanJuanDao {
    @Override
    public List<BanJuan> getBanJuanByPlainAndTextureType(PlainType plainType, @Nullable TextureType textureType) {
        String hql = "from BanJuan ban where ban.plainType = :plainTpe";
        Map<String,Object> map = new HashMap<>();
        map.put("plainType",plainType);
        if (textureType != null){
            hql = "from BanJuan ban where ban.plainType = :plainTpe and ban.textureType = : textureType";
            map.put("textureType",textureType);
        }
        return namedParamFind(hql,map);
    }

    @Override
    public List<BanJuan> getBanJuanByThickAndTextureType(float thick, @Nullable TextureType textureType) {
        String hql = "from BanJuan ban where ban.thick = :thick";
        Map<String,Object> map = new HashMap<>();
        map.put("thick",thick);
        if (textureType != null){
            hql = "from BanJuan ban where ban.thick = :thick and ban.textureType = :textureType";
            map.put("textureType",textureType);
        }
        return namedParamFind(hql,map);
    }
}
