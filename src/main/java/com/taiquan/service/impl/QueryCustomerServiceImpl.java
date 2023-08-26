package com.taiquan.service.impl;

import com.taiquan.bean.CompanyBean;
import com.taiquan.bean.DataCompany;
import com.taiquan.dao.customer.*;
import com.taiquan.dao.user.UserDao;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.customerEnums.*;
import com.taiquan.domain.users.Manager;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.exception.NoSuchPositionException;
import com.taiquan.service.QueryCustomerService;
import com.taiquan.utils.Page;
import com.taiquan.utils.PhoneNumberFormat;
import com.taiquan.utils.ReadExcel;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.taiquan.utils.PrintUtil.println;

@Service("queryCustomerService")
public class QueryCustomerServiceImpl implements QueryCustomerService {

    private CustomerDao    customerDao;
    private ContactPlanDao contactPlanDao;
    private AddressDao     addressDao;
    private CompanyDao     companyDao;
    private EmployeeDao employeeDao;
    private CompanyLogDao companyLogDao;
    private ContactDetailDao contactDetailDao;
    private ContactsDao contactsDao;
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ContactsDao getContactsDao() {
        return contactsDao;
    }

    @Autowired
    public void setContactsDao(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public ContactPlanDao getContactPlanDao() {
        return contactPlanDao;
    }

    @Autowired
    public void setContactPlanDao(ContactPlanDao contactPlanDao) {
        this.contactPlanDao = contactPlanDao;
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return customerDao.get(customerId);
    }

    @Override
    public List<Employee> getEmployeeByCustomer(int customerId) {
        List<Employee> employees = employeeDao.getEmployeeByCustomer(customerId);
        Collections.sort(employees);
        return employees;
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

    @Autowired
    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public CompanyLogDao getCompanyLogDao() {
        return companyLogDao;
    }

    @Autowired
    public void setCompanyLogDao(CompanyLogDao companyLogDao) {
        this.companyLogDao = companyLogDao;
    }

    public ContactDetailDao getContactDetailDao() {
        return contactDetailDao;
    }

    @Autowired
    public void setContactDetailDao(ContactDetailDao contactDetailDao) {
        this.contactDetailDao = contactDetailDao;
    }

    @Override
    public Page<Customer> getCustomerByContactPlanAll(@Nullable CustomerType customerType,
                                                   Date nextDate,boolean isOnlyDate,
                                                   @Nullable Date updateDate,boolean onlyUpdate,
                                                   User user,boolean onlyMe,
                                                   int pageNo, int pageSize) {
        return customerDao.getCustomerByContactPlanPage(customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,user,onlyMe,pageNo,pageSize);
    }
    @Override
    public Page<Customer> getCustomerByContactPlan(@Nullable CustomerType customerType,
                                                   Date nextDate,boolean isOnlyDate,
                                                   @Nullable Date updateDate,boolean onlyUpdate,
                                                   User user,boolean onlyMe,
                                                   int pageNo, int pageSize) {
        return customerDao.getCustomerByContactPlanPage(customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,user,onlyMe,pageNo,pageSize);
    }

    @Override
    public Page<Customer> getCustomerByAddress(String addressKey,User user,int pageNo,int pageSize) {
        List<Address> addresses = addressDao.getAddressByKeyWord(addressKey);
        Set<Customer> customers = new HashSet<>();
        for (Address address : addresses){
            customers.add(address.getCompany().getCustomer());
        }
        return new Page<>(pageNo,pageSize, new ArrayList<>(customers));
    }

    @Override
    public Page<Customer> getCustomerByAddressAround(String addressKeyWord, int miles,int pageNo,int pageSize) throws NoSuchPositionException{
        List<Address> addresses = addressDao.getAddressByAround(addressKeyWord,miles);
        Set<Customer> customers = new HashSet<>();
        for (Address address : addresses){
            customers.add(address.getCompany().getCustomer());
        }
        return new Page<>(pageNo,pageSize, new ArrayList<>(customers));
    }

    @Override
    public Page<Customer> getCustomerByPhoneNumber(String phoneNumber,User user,int pageNo,int pageSize) throws NoSuchObjectException {
        List<Customer> customers = customerDao.getCustomerByPhoneNumber(phoneNumber,user);
        return new Page<>(pageNo,pageSize,customers);
    }

    @Override
    public Page<Customer> getCustomersByEmployeeName(String name, User user,int pageNo,int pageSize) throws NoSuchObjectException {
        List<Customer> customers = customerDao.getCustomersByEmployeeName(name,user);
        return new Page<Customer>(pageNo,pageSize,customers);
    }

    @Override
    public Page<Customer> getCustomersByEmployeePhoneNumber(String phoneNumber,User user,int pageNo,int pageSize) throws NoSuchObjectException{
        List<Contacts> contactsList = contactsDao.getContactsByPhoneNumber(phoneNumber);
        List<Employee> employees = new ArrayList<>();
        if (contactsList != null && contactsList.size() !=0){
            for (Contacts contacts : contactsList){
                employees.add(contacts.getEmployee());
            }
        }
        Set<Customer> customers = new HashSet<>();
        if (employees.size() != 0){
            for (Employee employee : employees) {
                customers.addAll(customerDao.getCustomerByEmployee(employee));
            }
        }
        //把用户不匹配的剔除
        if (customers.size() != 0){
            Iterator it = customers.iterator();
            while (it.hasNext()){
                Customer customer = (Customer) it.next();
                if (user.isMgr() && (customer.getUser().getManager() == null
                                ? customer.getUser().getUserId() !=  user.getUserId()//客户的User是管理员，判断管理员是否是一个人
                                : customer.getUser().getManager().getUserId() != user.getUserId())//客户的user不是管理员，判断user的管理员是不是一个人
                        || (!user.isMgr() && customer.getUser() != user) ){
                    customers.remove(customer);
                }
            }
        }
        return new Page<Customer>(pageNo,pageSize,new ArrayList<>(customers));
    }

    @Override
    public Page<Customer> getAllCustomer(User user,int pageNo,int pageSize) {
        //List<Customer> customers = customerDao.getCustomerByUser(user,pageNo,pageSize);
        return customerDao.getCustomerByUser(user,pageNo,pageSize);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        return employeeDao.get(empId);
    }

    @Override
    public boolean companyIsExisted(String customerName) {
        return companyDao.companyIsExisted(customerName);
    }

    @Override
    public Page<Customer> getCustomerByKeyWord(String keyWord, User user) {
        return customerDao.getCustomersByKeyWord(keyWord,user);
    }

    @Override
    public boolean batchImport(String name, MultipartFile file, User user, HttpServletRequest request) {
        boolean b = false;
        ReadExcel readExcel = new ReadExcel();
        //println("***********************************     Multipart file is " + file.getOriginalFilename());
        List<DataCompany> dataList = readExcel.getExcelInfo(name,file,request);
        if (dataList != null) {
            b = true;
        } else {
            return false;
        }
        for (DataCompany dCompany : dataList){
            if (!companyIsExisted(dCompany.name)){
                Customer customer = new Customer();
                Company company = new Company();
                company.setCompanyName(dCompany.name);
                if (dCompany.business.length() > 499) {
                    company.setBusinesses(dCompany.business.substring(0,495));
                }else{
                    company.setBusinesses(dCompany.business);
                }
                company.setCapital(dCompany.money);
                try {
                    if (dCompany.moneyUnit == null || dCompany.moneyUnit.trim().equals("万人民币")) {
                        company.setCapitalType(CapitalType.万元);
                    }else {
                        company.setCapitalType(CapitalType.valueOf(dCompany.moneyUnit));
                    }
                }catch (IllegalArgumentException e){
                    company.setCapitalType(CapitalType.其他);
                }
                company.setEstablishedDate(dCompany.esDate);
                company.setOwner(dCompany.owner);
                if (dCompany.webAdd.contains("http://")){
                    company.setWeb(dCompany.webAdd.substring(8));
                }else if (dCompany.webAdd.trim().length() <4){
                    company.setWeb(null);
                }else {
                    company.setWeb(dCompany.webAdd);
                }
                Address address = null;
                if (dCompany.address!=null) {
                    address = new Address(AddressType.注册地址,dCompany.address);
                    //在部分添加失败的情形中，company的address如果为Address的话，会出现无法转化的问题，不知道是不是地址转化不成功的情形，明天再测试一下！
                    address.setCompany(company);
                    Set<Address> addresses = new HashSet<>();
                    addresses.add(address);
                    company.setAddresses(addresses);
                }
                company.setCompanyLog(new CompanyLog());
                List<PhoneNumber> phones = dCompany.city.length() > 0 ? PhoneNumberFormat.formatPhone(dCompany.city,dCompany.phoneNumber)
                        : PhoneNumberFormat.formatPhone(address,dCompany.phoneNumber);
                company.setPhoneNumber(phones.size() > 0? phones.get(0) : null);
                //先保存company
                company = companyDao.saveCompany(company);

                //保存customer，以获取customerId
                ContactPlan contactPlan = new ContactPlan();
                if (company.getCapital() < 501) {
                    contactPlan.setCustomerType(CustomerType.尚未联系);
                }else{
                    contactPlan.setCustomerType(CustomerType.暂不联系);
                }
                contactPlan.setNextDate(new Date(2050-1900,Calendar.DECEMBER,31));
                contactPlan.setUpdateTime(new Date());
                customer.setContactPlan(contactPlan);
                customer.setCompany(company);
                customer.setUser(user);
                //保存customer，方便保存employees
                customer = customerDao.save(customer);


                Set<Employee> employees = new HashSet<>();
                Employee employee = new Employee();
                List<Contacts> contactes = new ArrayList<>();
                if (phones.size() > 1){
                    for (int i = 1;i<phones.size();i++){
                        Contacts contacts = new Contacts();
                        contacts.setPhoneNumber(phones.get(i));
                        if (phones.get(i).getNumbers().startsWith("1")){
                            contacts.setPhoneType(PhoneType.手机);
                        }else {
                            contacts.setPhoneType(PhoneType.固话);
                        }
                        contacts.setEmployee(employee);
                        contactes.add(contacts);
                    }
                    employee.setContacts(contactes);
                    employee.setName("注册电话");
                    //email处理
                    if (dCompany.email.trim().length() >6) {
                        employee.setEmail(dCompany.email);
                    }
                    employee.setOthers("历年注册电话");
                    employee.setGendarMale(false);
                    employee.setAddDate(new Date());
                    employee.setAge("30~50岁");
                    employee.setPositionType(PositionType.未知);
                    employee.setCustomer(customer);
                    employee = employeeDao.save(employee);
                }
                employees.add(employee);
                customer.setEmployees(employees);
                println("######################## Custoemr need to Save " + customer);
                customerDao.update(customer);
            }
        }
        return true;
    }

}
