package com.taiquan.dao.customer;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.customer.ContactPlan;
import com.taiquan.domain.customerEnums.ContactsType;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.users.User;

import java.util.Date;
import java.util.List;

public interface ContactPlanDao extends BaseDao<ContactPlan>{
    public int getContactPlanId(int customerId);
}
