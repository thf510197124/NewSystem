package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.ContactDetailDao;
import com.taiquan.domain.customer.ContactDetail;
import com.taiquan.domain.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("contactDetailDao")
public class ContactDetailDaoImpl extends BaseDaoHibernate5<ContactDetail> implements ContactDetailDao {
}
