package com.taiquan.service;

import com.taiquan.domain.customer.*;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoAhuthrityException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AUDCustomerService {
    public Customer addCustomer(Company company, ContactPlan contactPlan,User user);
    public Employee addEmployee(Employee employee);
    public ContactDetail addContactDetail(ContactDetail contactDetail);

    public Address addAddress(Address add);
    public Contacts addContacts(Contacts contacts);

    public Company getComapnyByCompanyId(int companyId);
    public Customer updateCustomer(Customer customer);
    public ContactPlan updateContactPlan(ContactPlan contactPlan, int customerId);
    public Employee updateEmployee(Employee employee);
    public Contacts updateContacts(Contacts contacts);

    //删除
    public void deleteCustomer(int customerId,User user) throws NoAhuthrityException;
    public void deleteContactDetail(int contactDetail);
    public void deleteEmployee(int employeeId);

    public void deleteAddress(int addressId);


    void deleteContacts(Contacts contacts);

    void updateCompany(Company company);

    List<String> getCompanyNameList(String name);

}
