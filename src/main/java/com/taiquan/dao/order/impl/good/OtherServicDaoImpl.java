package com.taiquan.dao.order.impl.good;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.goods.OtherServicDao;
import com.taiquan.domain.order.enums.goodDetailType.OtherServiceType;
import com.taiquan.domain.order.goods.OtherServic;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("otherServicDao")
public class OtherServicDaoImpl extends BaseDaoHibernate5<OtherServic> implements OtherServicDao {
    @Override
    public List<OtherServic> getOtherServiceByOtherServiceType(OtherServiceType otherServiceType) {
        String hql = "from OtherServic oth where oth.otherServiceType = :otherServiceType";
        return namedParamFindOne(hql,"otherServiceType",otherServiceType);
    }
}
