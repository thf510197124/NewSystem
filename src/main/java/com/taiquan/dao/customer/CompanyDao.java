package com.taiquan.dao.customer;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.customer.Address;
import com.taiquan.domain.customer.Company;
import com.taiquan.domain.customer.CompanyLog;
import com.taiquan.domain.users.User;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

public interface CompanyDao extends BaseDao<Company> {
    public boolean companyIsExisted(String customerName);
    public List<Company> getCompanyByName(String customerName);
    public Company saveCompany(Company company);
    List<String> getCompanyNameList(String companyName);
}
