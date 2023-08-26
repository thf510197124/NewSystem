package com.taiquan.service.impl;

import com.taiquan.bean.DataCompany;
import com.taiquan.dao.customer.*;
import com.taiquan.dao.order.OrderDao;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoAhuthrityException;
import com.taiquan.service.AUDCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.ReadPendingException;
import java.util.*;

@Service("aUDCutomerService")
public class AUDCustomerServiceImpl implements AUDCustomerService {
    private CustomerDao customerDao;
    private EmployeeDao employeeDao;
    private ContactDetailDao contactDetailDao;
    private OrderDao orderDao;
    private CompanyDao companyDao;
    private ContactPlanDao contactPlanDao;
    private ContactsDao contactsDao;
    private AddressDao addressDao;
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public ContactDetailDao getContactDetailDao() {
        return contactDetailDao;
    }

    @Autowired
    public void setContactDetailDao(ContactDetailDao contactDetailDao) {
        this.contactDetailDao = contactDetailDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public ContactsDao getContactsDao() {
        return contactsDao;
    }

    @Autowired
    public void setContactsDao(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public Customer addCustomer(Company company, ContactPlan contactPlan,User user) {
        company = companyDao.saveCompany(company);//可以不先保存，如果不先保存的话，需要添加CompanyLog;
        Customer customer = new Customer();
        customer.setCompany(company);
        customer.setUser(user);
        customer.setContactPlan(contactPlan);
        customerDao.save(customer);
        return customer;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeDao.save(employee);
        return employee;
    }

    @Override
    public ContactDetail addContactDetail(ContactDetail contactDetail) {
        contactDetailDao.save(contactDetail);
        return contactDetail;
    }

    @Override
    public Address addAddress(Address add) {
        addressDao.save(add);
        return add;
    }

    @Override
    public Contacts addContacts(Contacts contacts) {
        return contactsDao.save(contacts);
    }

    @Override
    public Company getComapnyByCompanyId(int companyId) {
        return companyDao.get(companyId);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customerDao.update(customer);
        return customer;
    }

    public ContactPlanDao getContactPlanDao() {
        return contactPlanDao;
    }

    @Autowired
    public void setContactPlanDao(ContactPlanDao contactPlanDao) {
        this.contactPlanDao = contactPlanDao;
    }

    @Override
    public ContactPlan updateContactPlan(ContactPlan contactPlan, int customerId) {
        int contactPlanId = contactPlanDao.getContactPlanId(customerId);
        contactPlan.setContactPlanId(contactPlanId);
        contactPlanDao.update(contactPlan);
        return contactPlan;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employeeDao.update(employee);
        return employee;
    }

    @Override
    public Contacts updateContacts(Contacts contacts) {
        contactsDao.update(contacts);
        return contacts;
    }

    @Override
    public void deleteCustomer(int customerId,User user) throws NoAhuthrityException{
        Customer customer = customerDao.get(customerId);
        if (customer.getUser().equals(user)) {
            /*println("打印customer");
            println(customerDao.get(customerId));*/
            customerDao.remove(customer);
        }else {
            throw new NoAhuthrityException("不是你的客户");
        }
    }

    @Override
    public void deleteContactDetail(int contactDetail) {
        contactDetailDao.remove(contactDetailDao.get(contactDetail));
    }


    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeDao.get(employeeId);
        employeeDao.remove(employee);
    }

    @Override
    public void deleteAddress(int addressId) {
        Address address  = addressDao.get(addressId);
        addressDao.remove(address);
    }

    @Override
    public void deleteContacts(Contacts contacts) {
        contacts = contactsDao.get(contacts.getContactsId());
        contactsDao.remove(contacts);
    }

    @Override
    public void updateCompany(Company company) {
        Company company1 = companyDao.get(company.getCompanyId());
        CompanyLog companyLog = company1.getCompanyLog();
        if (companyLog==null){
            companyLog = new CompanyLog();
            companyLog.setAddTime(new Date(System.currentTimeMillis()));
        }
        companyLog.setUpdateTime(new Date(System.currentTimeMillis()));
        company.setCompanyLog(companyLog);
        companyDao.update(company);
    }

    @Override
    public List<String> getCompanyNameList(String companyName) {
        return companyDao.getCompanyNameList(companyName);
    }


}
