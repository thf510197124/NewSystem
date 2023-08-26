package com.taiquan.dao.order.impl.good;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.goods.JiaGongDao;
import com.taiquan.domain.order.enums.goodDetailType.JiaGongType;
import com.taiquan.domain.order.goods.JiaGong;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("jiaGongDao")
public class JiaGongDaoImpl extends BaseDaoHibernate5<JiaGong> implements JiaGongDao {
    @Override
    public List<JiaGong> getJiaGongByJiaGongType(JiaGongType jiaGongType) {
        String hql = "from JiaGong jia where jia.jiaGongType = :jiaGongType";
        Map<String,Object> map  = new HashMap<>();
        map.put("jiaGongType",jiaGongType);
        return namedParamFind(hql,map);
    }
}
