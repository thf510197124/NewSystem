package com.taiquan.dao.order.impl.good;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.goods.XingDao;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.enums.goodDetailType.XingCaiType;
import com.taiquan.domain.order.goods.Xing;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("xingDao")
public class XingDaoImpl extends BaseDaoHibernate5<Xing> implements XingDao {
    @Override
    public List<Xing> getXingByXingTypeAndTextureType(XingCaiType xingCaiType, @Nullable TextureType textureType) {
        String hql = "from XingCai xing where xing.xingCaiType = :xingCaiType";
        Map<String,Object> map = new HashMap<>();
        map.put("xingCaiType",xingCaiType);
        if (textureType != null){
            hql = hql + " xing.textureType = :textureType";
            map.put("textureType",textureType);
        }
        return namedParamFind(hql,map);
    }
}
