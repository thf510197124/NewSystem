package com.taiquan.dao.customer;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.customerEnums.ContactsType;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.utils.Page;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

public interface CustomerDao extends BaseDao<Customer>{
    public Page<Customer> getCustomerByUser(User user, int pageNo, int pageSize);
    public Customer getCustomerByCompany(Company company) throws NoSuchObjectException;
    public Page<Customer> getCustomerByContactPlanPage(@Nullable CustomerType customerType,
                                                   @Nullable Date nextDate, boolean isOnlyDate,
                                                   @Nullable Date updateTime,boolean onlyUpdate,
                                                   User user,boolean onlyMe,int pageNo,int pageSize);
    public List<Customer> getCustomerByCompanyName(String companyName,User user) throws NoSuchObjectException;
    public List<Customer> getCustomerByCompanyOwner(String owner,User user) throws NoSuchObjectException;
    public List<Customer> getCustomerByCompanyBusiness(String business,User user) throws NoSuchObjectException;
    public List<Customer> getCustomerByPhoneNumber(String phoneNumber,User user) throws NoSuchObjectException;
    public List<Customer> getCustomerByCapital(float low, float heigh,User user) throws NoSuchObjectException;
    public List<Customer>  getCustomerByOthers(String other,User user) throws NoSuchObjectException;
    public List<Customer> getCustomerByCompanyAddTime(Date addDate, boolean onlyDate, User user) throws NoSuchObjectException;
    public List<Customer> getCustomersByEmployeeName(String name, User user) throws NoSuchObjectException;
    public List<Customer> getCustomersByNameKeyWord(String nameKeyWord,User user) throws NoSuchObjectException;
    Page<Customer> getCustomersByKeyWord(String keyWord, User user);
    public List<Customer> getCustomerByEmployee(Employee employee);
}
