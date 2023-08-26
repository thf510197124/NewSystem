package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.ContactPlanDao;
import com.taiquan.domain.customer.ContactPlan;
import com.taiquan.domain.customerEnums.ContactsType;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.users.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository("contactPlanDao")
public class ContactPlanDaoImpl extends BaseDaoHibernate5<ContactPlan> implements ContactPlanDao {
    @Override
    public int getContactPlanId(int customerId) {
        String sql = "select contactPlanId from customer where customerId = " + customerId;
        return  (Integer) sqlQuery(sql);
    }
}
