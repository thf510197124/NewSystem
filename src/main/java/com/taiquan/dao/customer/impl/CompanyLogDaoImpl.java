package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.CompanyLogDao;
import com.taiquan.domain.customer.CompanyLog;
import com.taiquan.domain.users.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository("companyLogDao")
public class CompanyLogDaoImpl extends BaseDaoHibernate5<CompanyLog> implements CompanyLogDao {
}
