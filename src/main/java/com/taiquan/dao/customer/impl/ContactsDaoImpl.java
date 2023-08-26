package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.ContactsDao;
import com.taiquan.domain.customer.Contacts;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactsDaoImpl extends BaseDaoHibernate5<Contacts> implements ContactsDao{
    @Override
    public List<Contacts> getContactListByEmployeeId(int empId) {
        String sql = "select * from contacts where employeeId = " + empId;
        return (List<Contacts>)sqlQueryList(sql);
    }

    @Override
    public List<Contacts> getContactsByPhoneNumber(String phoneNumber) {
        String sql = "select * from contacts where numbers = " + phoneNumber;
        return (List<Contacts>) sqlQueryList(sql);
    }
}
