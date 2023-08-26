package com.taiquan.controller.customer;

import com.taiquan.bean.*;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoAhuthrityException;
import com.taiquan.exception.NoSuchCustomerException;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.service.AUDCustomerService;
import com.taiquan.service.OrderService;
import com.taiquan.service.QueryCustomerService;
import com.taiquan.service.UserService;
import com.taiquan.utils.Page;
import com.taiquan.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static com.taiquan.utils.PrintUtil.println;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    AUDCustomerService audCustomerService;
    @Autowired
    QueryCustomerService queryCustomerService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @RequestMapping(path = "/addCompany",method = RequestMethod.GET)
    public ModelAndView addCompany(){
        //处理经过拦截的请求
        ModelAndView mm = new ModelAndView();
        CompanyBean companyBean = new CompanyBean();
        mm.addObject("companyBean",companyBean);
        mm.setViewName("/addCompany");
        return mm;
    }

    //涉及到User问题
    @RequestMapping(path = "/addCompany",method = RequestMethod.POST)
    public String addCompany(CompanyBean companyBean,HttpSession session) throws NoSuchObjectException {
        User user = userService.getUserByUsername((String) session.getAttribute("username"));
        Company company = companyBean.toCompany(companyBean);
        //为customer设置contactPlan
        ContactPlan contactPlan = new ContactPlan();
        contactPlan.setCustomerType(companyBean.getCustomerType());
        contactPlan.setNextDate(companyBean.getNextDate());
        if (contactPlan.getCustomerType()==null){
            contactPlan.setCustomerType(CustomerType.尚未联系);
        }
        if (contactPlan.getNextDate() ==null){
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate localDate = LocalDate.of(2050,12,31);
            ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
            contactPlan.setNextDate(Date.from(zdt.toInstant()));
        }
        contactPlan.setUpdateTime(new Date(System.currentTimeMillis()));

        Customer customer = audCustomerService.addCustomer(company,contactPlan,user);
        /*return "redirect:/company/customerDetails/" + customer.getCustomerId()+".html";*/
        return "redirect:/company/addCompany.html";
    }
    @RequestMapping(path = "/updateCompany/{companyId}",method = RequestMethod.GET)
    public ModelAndView updateCompany(@PathVariable int companyId,HttpSession session){
        //requestBody可以自动把json对象字符串转化成对象，要求json必须以字符串形式传送对象
        //json对象以{}外包，内部是属性
        Company company = audCustomerService.getComapnyByCompanyId(companyId);
        Customer customer = company.getCustomer();
        return getCustomerDetails(customer.getCustomerId(),session);
    }
    @RequestMapping(path = "/updateCompany/{companyId}",method = RequestMethod.POST)
    public ResponseEntity updateCompany(@RequestBody Company company,@PathVariable int companyId){
        //requestBody可以自动把json对象字符串转化成对象，要求json必须以字符串形式传送对象
        //json对象以{}外包，内部是属性
        audCustomerService.updateCompany(company);
        return new ResponseEntity(HttpStatus.OK);
    }
    //以下的requestMapping无法拦截不带pageNo和pageSize的请求

    @RequestMapping(path = "/allCustomerList/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public String allCustomerList(HttpSession session,@PathVariable int pageNo,@PathVariable int pageSize) throws NoSuchObjectException {
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        Page<Customer> customerPage = queryCustomerService.getAllCustomer(user,pageNo,pageSize);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",customerPage.getPageNo());
        session.setAttribute("pageSize",customerPage.getPageSize());
        session.setAttribute("thisView","allCustomerList");
        session.setAttribute("customerListSize",customerPage.getTotalCount());
        session.setAttribute("controller","company");
        return "/customerList";
    }
    @RequestMapping(path = "/allCustomerList",method = RequestMethod.GET)
    public String allCustomerList(HttpSession session) throws NoSuchObjectException {
        return "redirect:/company/allCustomerList/" + 1 + "/" + PageUtil.PAGE_SIZE + ".html";
    }
    @RequestMapping(path = "/allCustomerList/{pageNo}",method = RequestMethod.GET)
    public String allCustomerList(HttpSession session,@PathVariable int pageNo) throws NoSuchObjectException {
        return "redirect:/company/allCustomerList/" + pageNo + "/" + PageUtil.PAGE_SIZE + ".html";
    }
    @RequestMapping(value = "/otherPage/{pageNo}")
    public String getOtherPage(@PathVariable int pageNo,HttpSession session) throws NoSuchObjectException {
        Page<Customer> customerPage = (Page<Customer>) session.getAttribute("customerPage");
        session.setAttribute("customerPage",new Page<Customer>(customerPage,pageNo));
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        session.setAttribute("username",user.getUsername());
        return "/customerList";
    }
    @RequestMapping(path = "/customerDetails/{customerId}/{currentEleInd}",method = RequestMethod.GET)
    public ModelAndView customerDetails(@PathVariable int customerId,@PathVariable int currentEleInd,HttpSession session) throws NoSuchObjectException, ParseException {
        ModelAndView mav = new ModelAndView();
        /*Customer customer1 = queryCustomerService.getCustomerById(customerId);*/
        Customer customer = null;
        int customerListSize = 0;
        Page<Customer>   customerPage = (Page<Customer>) session.getAttribute("customerPage");
        customerListSize = (customerPage.getAllData() == null ? customerPage.getTotalCount() : customerPage.getAllData().size());
        List<Customer> customers = customerPage.getAllData() == null ? customerPage.getThisPageList(): customerPage.getAllData();
        int pageNo = customerPage.getPageNo();
        int pageSize = customerPage.getPageSize();
        //如果取的是最后一个，那就回到开头
        if (currentEleInd -1 > customerListSize){
            customer = customers.get(0);
            currentEleInd=0;
        }else {
            if (currentEleInd >= 10 && customers.size() >10){
                //这里针对一次查询到很多行数据时
                customer = customers.get(currentEleInd);
                customer = queryCustomerService.getCustomerById(customer.getCustomerId());
            }else if ((pageNo - 1) * pageSize <= currentEleInd
                    //这句成立的前提条件是，每次都是分页查询的，而不是一次查询所有数据
                    && currentEleInd < (pageNo-1) * pageSize + customers.size()
                    && currentEleInd < pageNo * pageSize)
            {
                //针对一次行查询多页的情形
                customer = customers.size() > 10 ? customers.get(currentEleInd) : customers.get(currentEleInd % pageSize);
                //其实对于分页在数据库获取数据的情况，除了第一页，其他的每一页都每次取数据都会刷新取数据
                //上面的步骤是取到一个简单的customer，这里取的更详细的客户信息
                customer = queryCustomerService.getCustomerById(customer.getCustomerId());
            }else{
                String username = (String) session.getAttribute("username");
                User user = userService.getUserByUsername(username);
                Date date = new Date(System.currentTimeMillis());
                pageNo = currentEleInd / pageSize + 1;
                //查询全部数据的情况
                if (session.getAttribute("thisView").equals("allCustomerList")){
                    //如果是取全部客户的话，那么就获取当前currentEleId 所在的当前页；
                    customerPage = queryCustomerService.getAllCustomer(user,pageNo,pageSize);
                }
                //查询当日应该联系的客户
                if (session.getAttribute("thisView").equals("needTodayContact")){
                    //如果是取全部客户的话，那么就获取当前currentEleId 所在的当前页；
                    customerPage = queryCustomerService.getCustomerByContactPlan(null,date,false,null,false,
                            user,true,pageNo,pageSize);
                    session.setAttribute("customerPage",customerPage);
                }

                //查询当日已经联系的客户；
                if(session.getAttribute("thisView").equals("todayContacted")){
                    customerPage = queryCustomerService.getCustomerByContactPlan(null,null,false,date,true,
                            user,false,pageNo,pageSize);
                    session.setAttribute("customerPage",customerPage);
                }
                //查询的是todayPlanList
                if(session.getAttribute("thisView").equals("todayPlanList")){
                    String paramView = (String) session.getAttribute("paramView");
                    String[] paramViews = paramView.split("&");
                    CustomerType customerType = CustomerType.valueOf(paramViews[1]);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date nextDate = sdf.parse(paramViews[2]);
                    boolean isOnlyDate = Boolean.getBoolean(paramViews[3]);
                    Date updateDate;
                    if (paramViews[4].equals("null")){
                        updateDate = null;
                    }else{
                        updateDate = sdf.parse(paramViews[4]);
                    }
                    boolean onlyUpdate = Boolean.getBoolean(paramViews[5]);
                    boolean onlyMe = Boolean.getBoolean(paramViews[6]);
                    customerPage = queryCustomerService.getCustomerByContactPlanAll(customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,
                            user,onlyMe,pageNo,pageSize);
                    session.setAttribute("customerPage",customerPage);
                }
                //地址是一次性查询多页数据，不用分页处理
                /* if (session.getAttribute("thisView").equals("queryByAddress")){
                    String paramView = (String) session.getAttribute("paramView");
                    String[] paramViews = paramView.split("&");
                    String formattedAddress = paramViews[1];
                    customerPage = queryCustomerService.getCustomerByAddress(formattedAddress,user,pageNo,pageSize);
                    session.setAttribute("customerPage",customerPage);

                }*/

                if (customerListSize > customerPage.getTotalCount() && currentEleInd % 10 ==0){
                    currentEleInd = currentEleInd + customerPage.getTotalCount() - customerListSize ;
                    return customerDetails(customerId,currentEleInd,session);
                }
                customerListSize = customerPage.getTotalCount();
                customer = customerPage.getThisPageList().get(currentEleInd % pageSize);
            }
        }

        //理论上不会用到，因为所有可以使用上一项和下一项的，都是放在获取customerPage类的页面中
        customer = queryCustomerService.getCustomerById( customer != null ? customer.getCustomerId() : customerId);
        //显示employees，addresses，contactDetails
        Set<Address> addresses = customer.getCompany().getAddresses();
        List<Employee> employees = queryCustomerService.getEmployeeByCustomer(customer.getCustomerId());
        mav.addObject("employees",employees);
        mav.addObject("addresses",addresses);
        List<ContactDetail> contactDetails = new ArrayList<>(customer.getContactDetails());
        Collections.sort(contactDetails);
        mav.addObject("contactDetails", contactDetails);
        mav.addObject("company",customer.getCompany());
        int orderSize = orderService.getOrderNumbersByCustomer(customer);
        mav.addObject("orderSize",orderSize);
        //为了添加contactDetailsBean，addressBean，employee
        ContactDetailBean contactDetailBean = new ContactDetailBean();
        contactDetailBean.setNextDate(new Date());
        contactDetailBean.setCustomerType(customer.getContactPlan().getCustomerType().ordinal());
        mav.addObject("customerType",customer.getContactPlan().getCustomerType().ordinal());
        mav.addObject("contDetailsBean",contactDetailBean);
        mav.addObject("addressBean",new AddressBean());
        mav.addObject("employeeBean",new EmployeeBean());
        mav.addObject("customer",customer);
        mav.addObject("index",currentEleInd);
        mav.addObject("customerListSize",customerListSize);
        mav.addObject("empController","customer");
        /*session.setAttribute("thisCurrentId",currentEleInd);*/
        session.setAttribute("customer",customer);
        //为了添加address使用获得companyId
        session.setAttribute("company",customer.getCompany());
        session.setAttribute("customerPage",customerPage);
        mav.setViewName("/customerDetails");
        return mav;
    }

    @RequestMapping(path = "/customerDetails/{customerId}",method = RequestMethod.GET)
    public ModelAndView getCustomerDetails(@PathVariable int customerId,HttpSession session){
        ModelAndView mav = new ModelAndView();
        Customer customer = queryCustomerService.getCustomerById(customerId);
        Set<Address> addresses = customer.getCompany().getAddresses();
        //显示employees，addresses，contactDetails
        List<Employee> employees = queryCustomerService.getEmployeeByCustomer(customerId);
        mav.addObject("employees",employees);
        mav.addObject("addresses",customer.getCompany().getAddresses());
        int orderSize = orderService.getOrderNumbersByCustomer(customer);
        mav.addObject("orderSize",orderSize);
        List<ContactDetail> contactDetails = new ArrayList<>(customer.getContactDetails());
        Collections.sort(contactDetails);
        mav.addObject("contactDetails", contactDetails);
        mav.addObject("company",customer.getCompany());
        //为了添加contactDetailsBean，addressBean，employee
        //为了让contactDetailsBean显示过去的记录，便于修改
        ContactDetailBean contactDetailBean = new ContactDetailBean();
        if (customer.getContactPlan()!=null) {
            contactDetailBean.setNextDate(new Date());
            if (customer.getContactPlan().getCustomerType()!=null) {
                contactDetailBean.setCustomerType(customer.getContactPlan().getCustomerType().ordinal());
            }
        }
        mav.addObject("customerType",customer.getContactPlan().getCustomerType().ordinal());
        mav.addObject("contDetailsBean",contactDetailBean);
        mav.addObject("addressBean",new AddressBean());
        mav.addObject("employeeBean",new EmployeeBean());
        mav.addObject("empController","customer");
        mav.addObject("customer",customer);
        session.setAttribute("customer",customer);
        session.setAttribute("company",customer.getCompany());
        mav.setViewName("/customerDetails");
        return mav;
    }

    @RequestMapping(path = "/getCustomerByName",method = RequestMethod.POST)
    public String getCustomerByName(@RequestParam String companyName,HttpSession session)
            throws NoSuchObjectException, NoAhuthrityException, NoSuchCustomerException {
        User user = userService.getUserByUsername((String) session.getAttribute("username"));
        /*Page<Customer> customerPage = queryCustomerService.getCustomerByName(WebUtils.trimJsonStr(companyName),user,1,PageUtil.PAGE_SIZE);*/
        Page<Customer> customerPage = queryCustomerService.getCustomerByKeyWord(WebUtils.trimJsonStr(companyName),user);
        //List<String> nameList = audCustomerService.getCompanyNameList(WebUtils.trimJsonStr(companyName));
        if (customerPage.getAllData().size() == 0){
            throw new NoSuchCustomerException("客户不存在，请添加客户");
        }
        if (customerPage.getAllData().size() == 1){
            Customer customer = customerPage.getAllData().get(0);
            return "redirect:/company/customerDetails/" + customer.getCustomerId()+ ".html";
        }else{
            session.setAttribute("customerPage",customerPage);
            session.setAttribute("username",user.getUsername());
            session.setAttribute("pageNo",customerPage.getPageNo());
            session.setAttribute("pageSize",customerPage.getPageSize());
            session.setAttribute("thisView","getCustomerByName");
            session.setAttribute("customerListSize",customerPage.getTotalCount());
            session.setAttribute("controller","company");
            return "/customerList";
        }
    }
    @RequestMapping(path = "/addAddress",method = RequestMethod.POST)
    public ResponseEntity<Address> addAddress(@RequestBody AddressBean addressbean, HttpSession session){
        Company company = (Company) session.getAttribute("company");
        Address address = AddressBean.toAddress(addressbean);
        address.setCompany(company);
        audCustomerService.addAddress(address);
        address.setCompany(null);
        return new ResponseEntity<>(address,HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAddress/{addressId}",method = RequestMethod.GET)
    public ResponseEntity deleteAddress(@PathVariable int addressId){
        audCustomerService.deleteAddress(addressId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/nameList",method = RequestMethod.POST)
    public ResponseEntity<List<String>> getCompanyNameList(@RequestBody String name){
        List<String> nameList = audCustomerService.getCompanyNameList(WebUtils.trimJsonStr(name));
        return new ResponseEntity<List<String>>(nameList,HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    public ModelAndView handlerNoSuchCustomer(NoSuchCustomerException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",e.getMessage());
        modelAndView.addObject("companyBean",new CompanyBean());
        modelAndView.setViewName("/addCompany");
        return modelAndView;
    }
    @ExceptionHandler(NoAhuthrityException.class)
    public ModelAndView handlerNotAhuthrityException(NoAhuthrityException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg",e.getMessage());
        modelAndView.addObject("companyName",e.getCompanyName());
        modelAndView.addObject("userName",e.getUserName());
        modelAndView.setViewName("/customerExisted");
        return modelAndView;
    }
    @ExceptionHandler(NullPointerException.class)
    public String handlerNullPointerException(NullPointerException e){
        return "redirect:/user/login.html";
    }
   
    public static void handlerPostInterceptor(HttpServletRequest request,HttpSession session){
        Map<String,String[]> params = (Map<String, String[]>) session.getAttribute("paramMap");
        for (Map.Entry<String,String[]> param : params.entrySet()){
            request.setAttribute(param.getKey(),param.getValue());
        }
        session.removeAttribute("paramMap");
    }
    public static Page<CustomerBean> handlerCustomerPage(Page<Customer> page){
        Page<CustomerBean> customerBeanPage = new Page<>();
        customerBeanPage.setAllData(new ArrayList<>(page.getAllData().size()));
        customerBeanPage.setThisPageList(new ArrayList<>(page.getThisPageList().size()));
        customerBeanPage.setAllPageNo(page.getAllPageNo());
        customerBeanPage.setPageNo(page.getPageNo());
        customerBeanPage.setPageSize(page.getPageSize());
        customerBeanPage.setTotalCount(page.getTotalCount());
        page.getAllData().forEach(customer -> customerBeanPage.getAllData().add(CustomerBean.getBeanByCustomer(customer)));
        page.getThisPageList().forEach(customer -> customerBeanPage.getThisPageList().add(CustomerBean.getBeanByCustomer(customer)));
        return  customerBeanPage;
    }

    public static void main(String[] args){
        /*Employee employee = new Employee();
        Contacts contacts = new Contacts();
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumbers("12345677");
        contacts.setPhoneNumber(phoneNumber);
        //这样是不行的，出现空指针错误；
        employee.getContactses().add(contacts);
        println(employee);*/
        Customer customer = new Customer();
        println(customer);
    }
}
