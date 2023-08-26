package com.taiquan.dao.customer;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customer.Contacts;

import java.util.List;

public interface ContactsDao extends BaseDao<Contacts> {
    public List<Contacts> getContactListByEmployeeId(int empId);
    public List<Contacts> getContactsByPhoneNumber(String phoneNumber);
}
