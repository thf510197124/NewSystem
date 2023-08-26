package com.taiquan.dao.order.goods;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.order.enums.goodDetailType.JiaGongType;
import com.taiquan.domain.order.goods.JiaGong;

import java.util.List;

public interface JiaGongDao extends BaseDao<JiaGong> {
    public List<JiaGong> getJiaGongByJiaGongType(JiaGongType jiaGongType);
}
