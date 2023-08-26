package com.taiquan.dao.order.impl.good;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.goods.LingDao;
import com.taiquan.domain.order.enums.goodDetailType.LingBuJianType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.goods.Ling;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository("lingDao")
public class LingDaoImpl extends BaseDaoHibernate5<Ling> implements LingDao {
    @Override
    public List<Ling> getLingByLingTypeAndTextureType(LingBuJianType lingBuJianType, @Nullable TextureType textureType) {
        String hql = "from Ling ling where ling.lingBuJianType = :lingBuJianType";
        Map<String,Object> map = new HashMap<>();
        map.put("lingBuJianType",lingBuJianType);
        if (textureType != null){
            hql = hql + " and ling.textureType = :textureType";
            map.put("textureType",textureType);
        }
        return namedParamFind(hql,map);
    }
}
