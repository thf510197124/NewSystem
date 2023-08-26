package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.EmployeeDao;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.customer.Employee;
import com.taiquan.domain.customer.PhoneNumber;
import com.taiquan.domain.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.taiquan.utils.PrintUtil.println;

@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDaoHibernate5<Employee> implements EmployeeDao {
    @Override
    public List<Employee> getEmployeeByCustomer(int customerId) {
        return (List<Employee>) sqlQueryList("select * from employee where customerId = " + customerId);

    }

    @Override
    public List<Employee> getEmployeeBySupplier(int suppliserId) {
        return (List<Employee>)sqlQueryList("select * from employee where supplierId = " + suppliserId);
    }


}
