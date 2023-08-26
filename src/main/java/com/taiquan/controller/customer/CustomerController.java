package com.taiquan.controller.customer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taiquan.bean.ContactDetailBean;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.customerEnums.PhoneType;
import com.taiquan.domain.customerEnums.PositionType;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoAhuthrityException;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.exception.NoSuchPositionException;
import com.taiquan.service.AUDCustomerService;
import com.taiquan.service.QueryCustomerService;
import com.taiquan.service.UserService;
import com.taiquan.utils.Page;
import com.taiquan.utils.PageUtil;
import com.taiquan.utils.SpringUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.taiquan.utils.PrintUtil.println;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    AUDCustomerService audCustomerService;
    QueryCustomerService queryCustomerService;
    UserService userService;

    public AUDCustomerService getAudCustomerService() {
        return audCustomerService;
    }

    @Autowired
    public void setAudCustomerService(AUDCustomerService audCustomerService) {
        this.audCustomerService = audCustomerService;
    }

    public QueryCustomerService getQueryCustomerService() {
        return queryCustomerService;
    }

    @Autowired
    public void setQueryCustomerService(QueryCustomerService queryCustomerService) {
        this.queryCustomerService = queryCustomerService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/addContactDetail",method = RequestMethod.POST)
    public ResponseEntity<String> addContactDetail(@RequestBody ContactDetailBean contDetailsBean, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        //println(customer);
        ContactPlan contactPlan = new ContactPlan();
        int integer = 0;
        contactPlan.setCustomerType(CustomerType.values()[contDetailsBean.getCustomerType()]);
        if (contDetailsBean.getNextDate() != null){
            contactPlan.setNextDate(contDetailsBean.getNextDate());
        }else {
            contactPlan.setNextDate(customer.getContactPlan().getNextDate());
        }
        contactPlan.setNextDate(contDetailsBean.getNextDate());
        contactPlan.setUpdateTime(new Date(System.currentTimeMillis()));
        if (contDetailsBean.getContactDetails()!=null && !contDetailsBean.getContactDetails().equals("")) {
            ContactDetail contactDetail = new ContactDetail();
            contactDetail.setAddDate(new Date());
            contactDetail.setContactDetails(contDetailsBean.getContactDetails());
            contactDetail.setCustomer(customer);
            contactDetail = audCustomerService.addContactDetail(contactDetail);
            integer = contactDetail.getContactDetailId();
        }
        audCustomerService.updateContactPlan(contactPlan,customer.getCustomerId());
        return new ResponseEntity<>(String.valueOf(integer),HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteContactDetails/{contactDetailId}",method = RequestMethod.GET)
    public ResponseEntity deleteContactDetails(@PathVariable int contactDetailId){
        audCustomerService.deleteContactDetail(contactDetailId);
        return new ResponseEntity(null,HttpStatus.OK);
    }
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public ResponseEntity addEmployee(@RequestBody String empstr,HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        Employee employee = employeeUpdateBasic(empstr,null);
        employee.setAddDate(new Date());
        employee.setCustomer(customer);
        Set<Contacts> contactes = getContactesFromJson(empstr);
        if (contactes.size() == 0){
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }
        employee = audCustomerService.addEmployee(employee);
        try {
            for (Contacts contacts : contactes) {
                contacts.setEmployee(employee);
                audCustomerService.addContacts(contacts);
            }
        }catch (DataIntegrityViolationException e){
            new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteEmployee/{employeeId}",method = RequestMethod.GET)
    public ResponseEntity deleteEmployee(@PathVariable int employeeId){
        audCustomerService.deleteEmployee(employeeId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(path = "/updateEmployee/{employeeId}",method = RequestMethod.POST)
    public ResponseEntity updateEmployee(@RequestBody String empstr,@PathVariable int employeeId,HttpSession session){
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = (Customer) session.getAttribute("customer");
        Employee employee = queryCustomerService.getEmployeeById(employeeId);
        employee.setCustomer(customer);
        employee = employeeUpdateBasic(empstr,employee);
        audCustomerService.updateEmployee(employee);
        employeeUpdateContacts(empstr,employee);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(path = "/todayContacted",method = RequestMethod.GET)
    public String getCustomerTodayContacted(HttpSession session) throws NoSuchObjectException {
        session.removeAttribute("thisView");
        return "redirect:/customer/todayContacted/" + 1 + "/" + PageUtil.PAGE_SIZE + ".html";
    }
    @RequestMapping(path = "/todayContacted/{pageNo}",method = RequestMethod.GET)
    public String getCustomerTodayContacted(HttpSession session,@PathVariable int pageNo) throws NoSuchObjectException {
        return "redirect:/customer/todayContacted/" + pageNo + "/" + PageUtil.PAGE_SIZE + ".html";
    }

    @RequestMapping(path = "/todayContacted/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public String getCustomerTodayContacted(HttpSession session,@PathVariable int pageNo,@PathVariable int pageSize) throws NoSuchObjectException {
        Date date = new Date(System.currentTimeMillis());
        System.out.println("-------------------------" + date + "----------------------------");
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        Page<Customer> customerPage =queryCustomerService.getCustomerByContactPlan(null,null,false,date,true,
                user,false,pageNo,pageSize);
        //验证第一次点击某个viewList时的情况,那么首先出现第一页
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",customerPage.getPageNo());
        session.setAttribute("pageSize",customerPage.getPageSize());
        session.setAttribute("thisView","todayContacted");
        session.setAttribute("customerListSize",customerPage.getTotalCount());
        session.setAttribute("controller","customer");
        return "/customerList";
    }
    @RequestMapping(path = "/needTodayContact",method = RequestMethod.GET)
    public String getCustomerNeedTodayContact(HttpSession session) throws NoSuchObjectException {
        return getCustomerNeedTodayContact(session,1,PageUtil.PAGE_SIZE);
    }
    @RequestMapping(path = "/needTodayContact/{pageNo}",method = RequestMethod.GET)
    public String getCustomerNeedTodayContact(HttpSession session,@PathVariable int pageNo) throws NoSuchObjectException {
        return getCustomerNeedTodayContact(session,pageNo,PageUtil.PAGE_SIZE);
    }
    @RequestMapping(path = "/needTodayContact/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public String getCustomerNeedTodayContact(HttpSession session,@PathVariable int pageNo,@PathVariable int pageSize) throws NoSuchObjectException {
        Date date = new Date(System.currentTimeMillis());
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        Page<Customer> customerPage = queryCustomerService.getCustomerByContactPlan(null,date,false,null,false,
                user,true,pageNo,pageSize);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",customerPage.getPageNo());
        session.setAttribute("pageSize",customerPage.getPageSize());
        session.setAttribute("thisView","needTodayContact");
        session.setAttribute("customerListSize",customerPage.getTotalCount());
        session.setAttribute("controller","customer");
        return "/customers/todayPlanList";
    }
    @RequestMapping(path = "/queryCustomer")
    public String searchCustomer(){
        return "/searchCustomer";
    }
    @RequestMapping(path = "/queryByEmployee",method = RequestMethod.POST)
    public String queryByEmployee(@RequestParam String employeeName,HttpSession session) throws NoSuchObjectException {
        return queryByEmployee(employeeName,1,PageUtil.PAGE_SIZE,session);
    }
    @RequestMapping(path = "/queryByEmployee/{pageNo}",method = RequestMethod.POST)
    public String queryByEmployee(@RequestParam String employeeName,@PathVariable int pageNo,HttpSession session) throws NoSuchObjectException {
        return queryByEmployee(employeeName,pageNo,PageUtil.PAGE_SIZE,session);
    }
    @RequestMapping(path = "/queryByEmployee/{pageNo}",method = RequestMethod.GET)
    public String queryByEmployee(@PathVariable int pageNo,HttpSession session) throws NoSuchObjectException {
        Page<Customer> customerPage = getThisViewPage("queryByEmployee", pageNo, session);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        return "/customerList";
    }
    @RequestMapping(path = "/queryByEmployee/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public String queryByEmployee(@RequestParam String employeeName,@PathVariable int pageNo,@PathVariable int pageSize,HttpSession session) throws NoSuchObjectException {
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        String paramView = "queryByEmployee" + "&" + employeeName;
        Page<Customer> customerPage = null;
        //即判断参数有没有变化，如果没有变化，就直接返回现有的customerPage；
        try {
            if (session.getAttribute("paramView").equals(paramView)) {
                customerPage = getThisViewPage("queryByEmployee", pageNo, session);
            }
        }catch (NullPointerException ignored){}
        if(customerPage == null){
            customerPage = queryCustomerService.getCustomersByEmployeeName(employeeName,user,pageNo,pageSize);
        }
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageSize",pageSize);
        session.setAttribute("thisView","queryByEmployee");
        session.setAttribute("paramView",paramView);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        session.setAttribute("employeeName",employeeName);
        session.setAttribute("controller","customer");
        return "/customerList";
    }

    @RequestMapping(path = "/queryByAddress",method = RequestMethod.POST)
    public String queryByAddress(@RequestParam String province,@RequestParam String city,@RequestParam String district,HttpSession session
                                 ) throws NoSuchObjectException {
        return queryByAddress(1,PageUtil.PAGE_SIZE,province,city,district,session);
    }
    @RequestMapping(path = "/queryByAddress/{pageNo}",method = RequestMethod.POST)
    public String queryByAddress(@RequestParam String province,@RequestParam String city,@RequestParam String district,HttpSession session,
                                 @PathVariable int pageNo) throws NoSuchObjectException {
        return queryByAddress(pageNo,PageUtil.PAGE_SIZE,province,city,district,session);
    }
    @RequestMapping(path = "/queryByAddress/{pageNo}",method = RequestMethod.GET)
    public String queryByAddress(HttpSession session, @PathVariable int pageNo) throws NoSuchObjectException {
        Page<Customer> customerPage =getThisViewPage("queryByAddress", pageNo, session);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        return "/customerList";
    }
    @RequestMapping(path = "/queryByAddress/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public String queryByAddress(@PathVariable int pageNo,@PathVariable int pageSize,@RequestParam String province,
                                 @RequestParam String city,@RequestParam String district,HttpSession session
                                 ) throws NoSuchObjectException {
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        String formatted;
        if (province.contains("--"))
            province="";
        if (city.contains("--"))
            city="";
        if (district.contains("--"))
            district="";
        if (province.equals("北京市") || province.equals("上海市") || province.equals("天津市") || province.equals("重庆市")){
            formatted = province + district;
        }else{
            formatted = province + city + district;
        }
        String paramView = "queryByAddress" + "&" + formatted;
        Page<Customer> customerPage =null;
        try {
            if (session.getAttribute("paramView").equals(paramView)) {
                customerPage = getThisViewPage("queryByAddress", pageNo, session);
            }
        }catch (NullPointerException ignored){}

        if(customerPage == null){
            customerPage = queryCustomerService.getCustomerByAddress(formatted,user,pageNo,pageSize);
        }

        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageSize",pageSize);
        session.setAttribute("thisView","queryByAddress");
        session.setAttribute("paramView",paramView);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        session.setAttribute("controller","customer");
        return "/customerList";
    }
    @RequestMapping(path = "/queryByAddressRound",method = RequestMethod.POST)
    public String queryByAddressRound(@RequestParam String address,@RequestParam int round,
                                      HttpSession session) throws NoSuchObjectException, NoSuchPositionException {
        return queryByAddressRound(1,PageUtil.PAGE_SIZE,address,round,session);
    }
    @RequestMapping(path = "/queryByAddressRound/{pageNo}",method = RequestMethod.POST)
    public String queryByAddressRound(@PathVariable int pageNo,
                                      @RequestParam String address,@RequestParam int round,
                                      HttpSession session) throws NoSuchObjectException, NoSuchPositionException {
        return queryByAddressRound(pageNo,PageUtil.PAGE_SIZE,address,round,session);
    }
    @RequestMapping(path = "/queryByAddressRound/{pageNo}",method = RequestMethod.GET)
    public String queryByAddressRound(@PathVariable int pageNo,HttpSession session) throws NoSuchObjectException, NoSuchPositionException {
        Page<Customer> customerPage = getThisViewPage("queryByAddressRound", pageNo, session);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        return "/customerList";
    }
    @RequestMapping(path = "/queryByAddressRound/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public String queryByAddressRound(@PathVariable int pageNo,@PathVariable int pageSize,
                                      @RequestParam String address,@RequestParam int round,
                                      HttpSession session) throws NoSuchObjectException, NoSuchPositionException {
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        String paramView = "queryByAddressRound" + "&" + address + "&" + round;
        Page<Customer> customerPage = null;
        try {
            if (session.getAttribute("paramView").equals(paramView)) {
                customerPage = getThisViewPage("queryByAddressRound", pageNo, session);
            }
        }catch (NullPointerException ignored){}
        if(customerPage == null){
            customerPage = queryCustomerService.getCustomerByAddressAround(address,round*1000,pageNo,pageSize);
        }
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageSize",pageSize);
        session.setAttribute("thisView","queryByAddressRound");
        session.setAttribute("paramView",paramView);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        session.setAttribute("controller","customer");
        return "/customerList";
    }

    @RequestMapping(path = "/queryByKeyWords",method = RequestMethod.POST)
    public String queryByKeyWords(@RequestParam String keywords,HttpSession session)
            throws NoSuchObjectException {
        return queryByKeyWords(1,PageUtil.PAGE_SIZE,keywords,session);
    }
    @RequestMapping(path = "/queryByKeyWords/{pageNo}",method = RequestMethod.POST)
    public String queryByKeyWords(@PathVariable int pageNo,@RequestParam String keywords,HttpSession session)
            throws NoSuchObjectException {
        return queryByKeyWords(pageNo,PageUtil.PAGE_SIZE,keywords,session);
    }
    //第二页，第三页等需要用到get
    @RequestMapping(path = "/queryByKeyWords/{pageNo}",method = RequestMethod.GET)
    public String queryByKeyWords(@PathVariable int pageNo,HttpSession session)
            throws NoSuchObjectException {
        Page<Customer>  customerPage = getThisViewPage("queryByKeyWords", pageNo, session);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        return "/customerList";
    }
    @RequestMapping(path = "/queryByKeyWords/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public String queryByKeyWords(@PathVariable int pageNo,@PathVariable int pageSize,@RequestParam String keywords,HttpSession session)
            throws NoSuchObjectException {
        List<String> keyWords = Arrays.asList(keywords.split(" "));
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        String paramView = "queryByKeyWords" + keywords.replace(" ","&");
        Page<Customer> customerPage = null;
        try {
            if (session.getAttribute("paramView").equals(paramView)) {
                customerPage = getThisViewPage("queryByKeyWords", pageNo, session);
            }
        }catch (NullPointerException ignored){}
        Set<Customer> customers = new HashSet<>();
        if (customerPage==null){
            for(String keyword:keyWords){
                customers.addAll(queryCustomerService.getCustomerByKeyWord(keyword,user).getAllData());
                /*customers.addAll(queryCustomerService.getCustomerByCompanyNameKeyWord(keyword,user));
                customers.addAll(queryCustomerService.getCustomerByOthers(keyword,user,pageNo,pageSize).getAllData());*/
            }
            customerPage = new Page<Customer>(new ArrayList<>(customers));
        }
        /*println(" CustomerNumbers = " + customerPage.getAllData().size());*/
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageSize",pageSize);
        session.setAttribute("thisView","queryByKeyWords");
        session.setAttribute("paramView",paramView);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        session.setAttribute("controller","customer");
        return "/customerList";
    }

    @RequestMapping(path = "/queryByPhoneNumber",method = RequestMethod.POST)
    public String queryByPhoneNumber(@RequestParam String phone,HttpSession session)
            throws NoSuchObjectException {
        return queryByPhoneNumber(1,PageUtil.PAGE_SIZE,phone,session);
    }
    @RequestMapping(path = "/queryByPhoneNumber/{pageNo}",method = RequestMethod.POST)
    public String queryByPhoneNumber(@PathVariable int pageNo,@RequestParam String phone,HttpSession session)
            throws NoSuchObjectException {
        return queryByPhoneNumber(pageNo,PageUtil.PAGE_SIZE,phone,session);
    }
    @RequestMapping(path = "/queryByPhoneNumber/{pageNo}",method = RequestMethod.GET)
    public String queryByPhoneNumber(@PathVariable int pageNo,HttpSession session)
            throws NoSuchObjectException {
        Page<Customer> customerPage = customerPage = getThisViewPage("queryByPhoneNumber", pageNo, session);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        return "/customerList";
    }
    @RequestMapping(path = "/queryByPhoneNumber/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public String queryByPhoneNumber(@PathVariable int pageNo,@PathVariable int pageSize,@RequestParam String phone,HttpSession session)
            throws NoSuchObjectException {
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        String paramView = "queryByPhoneNumber" + "&" + phone;
        Page<Customer> customerPage = null;
        try {
            if (session.getAttribute("paramView").equals(paramView)) {
                customerPage = getThisViewPage("queryByPhoneNumber", pageNo, session);
            }
        }catch (NullPointerException ignored){}
        Set<Customer> customers = new HashSet<>();
        if (customerPage==null){
            customers.addAll(queryCustomerService.getCustomerByPhoneNumber(phone,user,pageNo,pageSize).getAllData());
            customers.addAll(queryCustomerService.getCustomersByEmployeePhoneNumber(phone,user,pageNo,pageSize).getAllData());
            customerPage = new Page<Customer>(new ArrayList<>(customers));
        }
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageSize",pageSize);
        session.setAttribute("thisView","queryByPhoneNumber");
        session.setAttribute("paramView",paramView);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        session.setAttribute("controller","customer");
        return "/customerList";
    }

    @RequestMapping(value = "/todayPlanList",method = RequestMethod.POST)
    public String todayPlanList(HttpSession session,@RequestParam @Nullable CustomerType customerType,@RequestParam  Date nextDate,@RequestParam boolean isOnlyDate,
                                @RequestParam @Nullable Date updateDate,@RequestParam boolean onlyUpdate,
                                @RequestParam boolean onlyMe,ModelMap modelMap) throws NoSuchObjectException {
        return todayPlanList(session,customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,onlyMe,1,PageUtil.PAGE_SIZE,modelMap);
    }

    /***
     * 这个方法应该是用不上的，先注释掉
     */
    /*
    @RequestMapping(value = "/todayPlanList/{pageNo}",method = RequestMethod.POST)
    public String todayPlanList(HttpSession session,@RequestParam @Nullable CustomerType customerType,
                                @RequestParam  Date nextDate,@RequestParam boolean isOnlyDate,
                                @RequestParam @Nullable Date updateDate,@RequestParam boolean onlyUpdate,
                                @RequestParam boolean onlyMe,@PathVariable int pageNo,ModelMap modelMap) throws NoSuchObjectException {
        return todayPlanList(session,customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,onlyMe,pageNo,PageUtil.PAGE_SIZE,modelMap);
    }
    */
    @RequestMapping(value = "/todayPlanList/{pageNo}",method = RequestMethod.GET)
    public String todayPlanList(@PathVariable int pageNo,HttpSession session) throws NoSuchObjectException{
        /*Page<Customer> customerPage  = getThisViewPage("todayPlanList", pageNo, session);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        return "/customers/todayPlanList";*/
        return "redirect:/customer/todayPlanList/" + pageNo + "/" + PageUtil.PAGE_SIZE + ".html";
    }
    @RequestMapping(value = "/todayPlanList/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public String todayPlanList(@PathVariable int pageNo,@PathVariable int pageSize,HttpSession session,ModelMap modelMap)
            throws NoSuchObjectException, ParseException {
        /*Page<Customer> customerPage  = getThisViewPage("todayPlanList", pageNo, session);
        session.setAttribute("customerPage",new Page<Customer>(pageNo,pageSize,customerPage.getAllData()));
        session.setAttribute("customerListSize",customerPage.getAllData().size());
        return "/customers/todayPlanList";*/
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
        return todayPlanList(session,customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,onlyMe,pageNo,pageSize,modelMap);
    }
    @RequestMapping(value = "/todayPlanList/{pageNo}/{pageSize}",method = RequestMethod.POST)
    public String todayPlanList(HttpSession session, @RequestParam @Nullable CustomerType customerType, @RequestParam Date nextDate, @RequestParam boolean isOnlyDate,
                                @RequestParam @Nullable Date updateDate, @RequestParam boolean onlyUpdate,
                                @RequestParam boolean onlyMe, @PathVariable int pageNo, @PathVariable int pageSize,ModelMap mm) throws NoSuchObjectException {
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String updateDateStr ="";
        if (updateDate==null){
            updateDateStr = null;
        }else{
            updateDateStr = sdf.format(updateDate);
        }
        String paramView = "todayPlanList" + "&" + customerType + "&" + sdf.format(nextDate) + "&"+ isOnlyDate+ "&" + updateDateStr+"&"+onlyUpdate+"&"+onlyMe;
        /*Page<Customer> customerPage = null;
        if (session.getAttribute("paramView") !=null && session.getAttribute("paramView").equals(paramView)) {
            customerPage = getThisViewPage("todayPlanList", pageNo, session);
        }
        if(customerPage == null){
            customerPage = queryCustomerService.getCustomerByContactPlanAll(customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,
                    user,onlyMe,pageNo,pageSize);
        }*/
        Page<Customer> customerPage = queryCustomerService.getCustomerByContactPlanAll(customerType,nextDate,isOnlyDate,updateDate,onlyUpdate,
                user,onlyMe,pageNo,pageSize);
        session.setAttribute("customerPage",customerPage);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("pageNo",customerPage.getPageNo());
        session.setAttribute("pageSize",customerPage.getPageSize());
        session.setAttribute("thisView","todayPlanList");
        session.setAttribute("customerListSize",customerPage.getTotalCount());
        session.setAttribute("controller","customer");
        session.setAttribute("paramView",paramView);
        return "/customers/todayPlanList";
    }

    @RequestMapping("/deleteCustomer/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable int customerId,HttpSession session) throws NoSuchObjectException, NoAhuthrityException {
        String username = (String) session.getAttribute("username");
        User user = userService.getUserByUsername(username);
        audCustomerService.deleteCustomer(customerId,user);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping("/upLoadCustomer")
    public String upLoadCustomer(@RequestParam(value = "filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (file == null) {
            return null;
        }
        String username = (String) request.getSession().getAttribute("username");
        User user = userService.getUserByUsername(username);
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if (name == null || "".equals(name) && size == 0) return null;
        boolean b = queryCustomerService.batchImport(name,file,user,request);
        if(b){
            String msg = "批量导入EXCEL成功！";
        }else {
            String msg = "批量导入EXCEL失败";
            request.getSession().setAttribute("msg",msg);
        }
        return "redirect:/queryCustomer.html";
    }
    public static Employee employeeUpdateContacts(String empstr,Employee employee) {
        //这样是为了不添加重复的contacts
        AUDCustomerService customerService = (AUDCustomerService) SpringUtils.getBean(AUDCustomerService.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(empstr);
            JsonNode conts = jsonNode.findValue("contactes");
            int length = conts.size()/3;
            for (int i=0;i<length;i++){
                Contacts contacts = new Contacts();
                int contactId = jsonNode.findValue(i + ".contactsId").asInt();
                contacts.setContactsId(contactId);
                int index = jsonNode.findValue(i + ".phoneType").asInt();
                contacts.setPhoneType(PhoneType.getPhoneType(index));
                String number = jsonNode.findValue(i+".phoneNumber").asText();
                if (number != null && !number.equals("")){
                    PhoneNumber phoneNumber = new PhoneNumber(number);
                    contacts.setPhoneNumber(phoneNumber);
                }else {
                    if (contacts.getContactsId() == 0){
                        continue;
                    }
                }
                contacts.setEmployee(employee);
                if (contacts.getContactsId()==0){
                    customerService.addContacts(contacts);
                }else if(contacts.getContactsId() !=0 && (contacts.getPhoneNumber()==null || contacts.getPhoneNumber().getNumbers().equals(""))){
                    customerService.deleteContacts(contacts);
                }else{
                    customerService.updateContacts(contacts);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employee;
    }
    public static Employee employeeUpdateBasic(String jsonStr,Employee employee){
        ObjectMapper mapper = new ObjectMapper();
        if (employee == null){
            employee = new Employee();
        }
        try {
            JsonNode jsonNode = mapper.readTree(jsonStr);
            employee.setName(jsonNode.findValue("name").asText());
            if (employee.getName().equals("")) employee.setName(null);

            employee.setGendarMale(jsonNode.findValue("gendarMale").asBoolean());
            employee.setEmail(jsonNode.findValue("email").asText());
            if (employee.getEmail().equals("")) employee.setEmail(null);

            employee.setAge(jsonNode.findValue("age").asText());
            int posType = jsonNode.findValue("positionType").asInt();
            employee.setPositionType(PositionType.values()[posType]);
            employee.setOthers(jsonNode.findValue("others").asText());
            if (employee.getOthers().equals("")) employee.setOthers(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return employee;
    }
    public static Set<Contacts> getContactesFromJson(String jsonStr){
        ObjectMapper mapper = new ObjectMapper();
        Set<Contacts> contactes = new HashSet<>();
        try {
            JsonNode jsonNode = mapper.readTree(jsonStr);
            JsonNode conts = jsonNode.findValue("contactes");
            println(conts);
            int length = conts.size()/2;
            for (int i=0;i<length;i++){
                Contacts contacts = new Contacts();
                int index = jsonNode.findValue(i + ".phoneType").asInt();
                contacts.setPhoneType(PhoneType.getPhoneType(index));
                String phoneNumber1 = jsonNode.findValue(i+".phoneNumber").asText();
                if (phoneNumber1.equals("")){
                    continue;
                }
                PhoneNumber phoneNumber = new PhoneNumber(phoneNumber1);
                contacts.setPhoneNumber(phoneNumber);
                contactes.add(contacts);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return contactes;
    }
    //用于构造一次性查询到很多页数据的情况
    private static Page<Customer> getThisViewPage(String thisView,int pageNo,HttpSession session){
        Page<Customer> customerPage = null;
        try{
            if (session.getAttribute("thisView").equals(thisView)){
                customerPage = (Page<Customer>) session.getAttribute("customerPage");
                customerPage = new Page<>(customerPage,pageNo);
                customerPage.setPageNo(customerPage.getPageNo());
                customerPage.setPageSize(customerPage.getPageSize());
            }
        }catch (Exception e){}
        if (customerPage != null) {
            customerPage = new Page(customerPage, pageNo);
        }
        return customerPage;
    }

}
