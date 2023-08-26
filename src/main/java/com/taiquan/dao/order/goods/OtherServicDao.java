package com.taiquan.dao.order.goods;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.enums.goodDetailType.OtherServiceType;
import com.taiquan.domain.order.goods.OtherServic;

import java.util.List;

public interface OtherServicDao extends BaseDao<OtherServic> {
    public List<OtherServic> getOtherServiceByOtherServiceType(OtherServiceType otherServiceType);
}
