package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.CompanyDao;
import com.taiquan.domain.customer.Company;
import com.taiquan.domain.customer.CompanyLog;
import com.taiquan.utils.JSONUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.sql.Date;
import static com.taiquan.utils.PrintUtil.println;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoHibernate5<Company> implements CompanyDao {

    @Override
    public boolean companyIsExisted(String customerName) {
        customerName = JSONUtils.removeQuot(customerName);
        String sql ="select count(*) from Company com where com.companyName like '%" + customerName + "%'";
        List count = find(sql);
        /*println("count = " + count);*/
        return (long)count.get(0) > 0;
    }

    @Override
    public List<Company> getCompanyByName(String customerName) {
        String hql = "select com from Company com where com.companyName like '%" + customerName + "%'";
        return find(hql);
    }

    @Override
    @CachePut(value="fixedRegion",key="#company.companyId")
    public Company saveCompany(Company company) {
        CompanyLog companyLog = new CompanyLog();
        companyLog.setAddTime(new Date(System.currentTimeMillis()));
        companyLog.setUpdateTime(new Date(System.currentTimeMillis()));
        company.setCompanyLog(companyLog);
        company = save(company);
        return company;
    }

    @Override
    public List<String> getCompanyNameList(String companyName) {
        String sql ="select distinct companyName from company where companyName like '%" + companyName + "%' or others like '%" + companyName + "%' limit 10" ;
        return sqlQueryForField(sql);
    }

}
