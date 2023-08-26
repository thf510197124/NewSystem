package com.taiquan.service;

import com.taiquan.domain.customer.*;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.exception.NoSuchPositionException;
import com.taiquan.utils.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface QueryCustomerService {
    //查
    public Customer getCustomerById(int customerId);
    public List<Employee> getEmployeeByCustomer(int customerId);
    public Page<Customer> getCustomerByContactPlan(CustomerType customerType,
                                                   Date nextDate, boolean isOnlyDate,
                                                   Date updateDate, boolean onlyUpdate,
                                                   User user, boolean onlyMe,
                                                   int pageNo, int pageSize);
    public Page<Customer> getCustomerByContactPlanAll(CustomerType customerType,
                                                   Date nextDate, boolean isOnlyDate,
                                                   Date updateDate, boolean onlyUpdate,
                                                   User user, boolean onlyMe,
                                                   int pageNo, int pageSize);
    public Page<Customer> getCustomerByAddress(String addressKey,User user,int pageNo,int pageSize);
    public Page<Customer> getCustomerByAddressAround(String addressKeyWord,int miles,int pageNo,int pageSize) throws NoSuchPositionException;
    public Page<Customer> getCustomerByPhoneNumber(String phoneNumber,User user,int pageNo,int pageSize) throws NoSuchObjectException;

    public Page<Customer> getCustomersByEmployeeName(String name,User user,int pageNo,int pageSize) throws NoSuchObjectException;
    public Page<Customer> getCustomersByEmployeePhoneNumber(String phoneNumber,User user,int pageNo,int pageSize) throws NoSuchObjectException;

    public Page<Customer> getAllCustomer(User user,int pageNo,int pageSize);
    public Employee getEmployeeById(int empId);
    public boolean companyIsExisted(String customerName);
    public Page<Customer> getCustomerByKeyWord(String keyWord,User user);

    boolean batchImport(String name, MultipartFile file,User user,HttpServletRequest request);
}
