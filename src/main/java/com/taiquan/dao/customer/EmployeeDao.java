package com.taiquan.dao.customer;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.customer.Employee;
import com.taiquan.domain.customer.PhoneNumber;
import com.taiquan.domain.users.User;

import java.util.List;

public interface EmployeeDao extends BaseDao<Employee>{
    public List<Employee> getEmployeeByCustomer(int customerId) ;
    public List<Employee> getEmployeeBySupplier(int suppliserId);
}
