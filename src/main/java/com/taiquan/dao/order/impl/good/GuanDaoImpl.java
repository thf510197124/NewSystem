package com.taiquan.dao.order.impl.good;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.goods.GuanDao;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.goods.Guan;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("guanDao")
public class GuanDaoImpl extends BaseDaoHibernate5<Guan> implements GuanDao {
    @Override
    public List<Guan> getGuanByThickAndDiameterAndTextureType(float thick, float diameter, @Nullable TextureType textureType) {
        String hql = "from Guan guan where guan.thick = :thick and guan.diameter = :diameter";
        Map<String,Object> map = new HashMap<>();
        map.put("thick",thick);
        map.put("diameter",diameter);
        if (textureType != null){
            hql = hql + " and guan.textureType = :textureType";
            map.put("textureType",textureType);
        }
        return namedParamFind(hql,map);
    }
}
