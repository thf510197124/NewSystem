package com.taiquan.controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taiquan.bean.EmployeeBean;
import com.taiquan.bean.InnerGoodBean;
import com.taiquan.bean.SupplierBean;
import com.taiquan.bean.SupplierOrderBean;
import com.taiquan.controller.customer.CustomerController;
import com.taiquan.controller.customer.WebUtils;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.order.Supplier;

import com.taiquan.domain.order.Texture;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchEnumException;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.service.*;
import com.taiquan.utils.Page;
import com.taiquan.utils.PageUtil;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.taiquan.utils.PrintUtil.println;

@Controller
@RequestMapping("/supply")
public class SupplierController {
    private AUDCustomerService audCustomerService;
    private QueryCustomerService queryCustomerService;
    private UserService userService;
    private OrderService orderService;
    private SupplyService supplyService;

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
    public OrderService getOrderService() {
        return orderService;
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    public SupplyService getSupplyService() {
        return supplyService;
    }
    @Autowired
    public void setSupplyService(SupplyService supplyService) {
        this.supplyService = supplyService;
    }
    @RequestMapping(path = "/allSuppliers/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public String allCustomerList(HttpSession session,@PathVariable int pageNo,@PathVariable int pageSize) throws NoSuchObjectException {
        Page<Supplier> supplierPage = supplyService.getAllSuppliers(pageNo,pageSize);
        session.setAttribute("supplierPage",supplierPage);
        session.setAttribute("pageNo",supplierPage.getPageNo());
        session.setAttribute("pageSize",supplierPage.getPageSize());
        session.setAttribute("thisView","allSuppliers");
        session.setAttribute("supplierListSize",supplierPage.getTotalCount());
        session.setAttribute("controller","supply");
        return "/order/supplierList";
    }
    @RequestMapping(path = "/allSuppliers",method = RequestMethod.GET)
    public String allCustomerList(HttpSession session) throws NoSuchObjectException {
        return "redirect:/supply/allSuppliers/" + 1 + "/" + PageUtil.PAGE_SIZE + ".html";
    }
    @RequestMapping(path = "/allSuppliers/{pageNo}",method = RequestMethod.GET)
    public String allCustomerList(HttpSession session,@PathVariable int pageNo) throws NoSuchObjectException {
        return "redirect:/supply/allSuppliers/" + pageNo + "/" + PageUtil.PAGE_SIZE + ".html";
    }
    @RequestMapping(value = "/addSupplier",method = RequestMethod.GET)
    public String addSupplier(ModelMap modelMap){
        Supplier supplier = new Supplier();
        modelMap.put("supplier",supplier);
        return "/order/addSupply";
    }
    @RequestMapping(value = "/addSupplier",method = RequestMethod.POST)
    public String addSupplier(Supplier supplier,ModelMap modelMap,HttpSession session){
        supplier = supplyService.addSupplier(supplier);
        /*Set<Employee> employees = supplier.getEmployees();
        int goodSize = orderService.getGoodSizeBySupplier(supplier);
        modelMap.put("employees",employees);
        modelMap.put("supplier",supplier);
        modelMap.put("goodSize",goodSize);
        modelMap.put("employeeBean",new EmployeeBean());
        modelMap.put("empController","supply");
        session.setAttribute("supplier",supplier);
        return "/order/supplierDetail";*/
        return "redirect:/supply/supplierDetail/"+ supplier.getSupplierId()+ ".html";
    }
    @RequestMapping(value = "/updateSupplier/{supplierId}",method = RequestMethod.POST)
    public ResponseEntity updateSupplier(@RequestBody SupplierBean supplier, ModelMap modelMap, HttpSession session) throws NoSuchEnumException {
        supplyService.updateSupplier(SupplierBean.getSupplier(supplier));
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/supplierDetail/{supplierId}",method = RequestMethod.GET)
    public String supplierDetail(@PathVariable int supplierId, ModelMap modelMap,HttpSession session){
        Supplier supplier = supplyService.getSupplierById(supplierId);
        List<Employee> employees = supplier.getEmployees();
        int goodSize = orderService.getGoodSizeBySupplier(supplier);
        modelMap.put("employees",employees);
        modelMap.put("supplier",supplier);
        modelMap.put("goodSize",goodSize);
        modelMap.put("employeeBean",new EmployeeBean());
        modelMap.put("empController","supply");
        session.setAttribute("supplier",supplier);
        return  "/order/supplierDetail";
    }
    @RequestMapping(path = "/supplierDetail/{supplierId}/{currentEleInd}",method = RequestMethod.GET)
    public ModelAndView supplierDetails(@PathVariable int supplierId, @PathVariable int currentEleInd, HttpSession session) throws NoSuchObjectException, ParseException {
        ModelAndView mav = new ModelAndView();
        /*Customer customer1 = queryCustomerService.getCustomerById(customerId);*/
        Supplier supplier = null;
        int supplierSize = 0;
        Page<Supplier>   supplierPage = (Page<Supplier>) session.getAttribute("supplierPage");
        supplierSize = (supplierPage.getAllData() == null ? supplierPage.getTotalCount() : supplierPage.getAllData().size());
        List<Supplier> suppliers = supplierPage.getAllData() == null ? supplierPage.getThisPageList(): supplierPage.getAllData();
        //如果取的是最后一个，那就回到开头
        int pageNo = supplierPage.getPageNo();
        int pageSize = supplierPage.getPageSize();
        if (currentEleInd -1 > supplierSize){
            supplier = suppliers.get(0);
            currentEleInd=0;
        }
        else {
            if ((pageNo - 1) * pageSize <= currentEleInd
                    && currentEleInd < (pageNo-1) * pageSize + suppliers.size()
                    && currentEleInd < pageNo * pageSize)
            {
                supplier = suppliers.get(currentEleInd % pageSize);
                //其实对于分页在数据库获取数据的情况，除了第一页，其他的每一页都每次取数据都会刷新取数据
                //上面的步骤是取到一个简单的customer，这里取的更详细的客户信息
                supplier = supplyService.getSupplierById(supplier.getSupplierId());
            }else{
                pageNo = currentEleInd / pageSize + 1;
                //查询全部数据的情况
                if (session.getAttribute("thisView").equals("allSuppliers")){
                    //如果是取全部客户的话，那么就获取当前currentEleId 所在的当前页；
                    supplierPage = supplyService.getAllSuppliers(pageNo,pageSize);
                }
                if (session.getAttribute("thisView").equals("queryByGoodAndTextureType")){
                    Map<String,String> paramMap = (Map<String, String>) session.getAttribute("paramMap");
                    String goodDetailType = paramMap.get("goodDetailType");
                    String textureType = paramMap.get("textureType");
                    try {
                        supplierPage = supplyService.getSupplierByType(goodDetailType,textureType,pageNo,pageSize);
                    } catch (NoSuchEnumException e) {
                        e.printStackTrace();
                    }

                }
                if (supplierSize > supplierPage.getTotalCount() && currentEleInd % 10 ==0){
                    currentEleInd = currentEleInd + supplierPage.getTotalCount() - supplierSize ;
                    return supplierDetails(supplierId,currentEleInd,session);
                }
                supplierSize = supplierPage.getTotalCount();
                supplier=supplierPage.getThisPageList().get(currentEleInd % pageSize);
            }
        }
        //理论上不会用到，因为所有可以使用上一项和下一项的，都是放在获取customerPage类的页面中
        supplier = supplyService.getSupplierById( supplier !=null ? supplier.getSupplierId() : supplierId );
        //显示employees，addresses，contactDetails
        List<Employee> employees = supplier.getEmployees();
        mav.addObject("employees",employees);
        int goodSize = orderService.getGoodSizeBySupplier(supplier);
        mav.addObject("goodSize",goodSize);
        //为了添加contactDetailsBean，addressBean，employee
        mav.addObject("employeeBean",new EmployeeBean());
        mav.addObject("supplier",supplier);
        mav.addObject("index",currentEleInd);
        mav.addObject("supplierSize",supplierSize);
        mav.addObject("controller","supply");
        mav.addObject("empController","supply");
        mav.addObject("currentEleInd",currentEleInd);
        session.setAttribute("supplier",supplier);
        //为了添加address使用获得companyId
        session.setAttribute("supplierPage",supplierPage);
        mav.setViewName("/order/supplierDetail");
        return mav;
    }
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public ResponseEntity<String> addEmployee(@RequestBody String empStr, HttpSession session){
        Supplier supplier = (Supplier) session.getAttribute("supplier");
        Employee employee = CustomerController.employeeUpdateBasic(empStr,null);
        employee.setAddDate(new Date());
        employee.setSupplier(supplier);
        Set<Contacts> contactsSet = CustomerController.getContactesFromJson(empStr);
        if (contactsSet.size()==0){
            return new ResponseEntity<>("failed",HttpStatus.BAD_GATEWAY);
        }
        employee = audCustomerService.addEmployee(employee);
        try {
            for (Contacts contacts : contactsSet) {
                contacts.setEmployee(employee);
                audCustomerService.addContacts(contacts);
            }
        }catch (DataIntegrityViolationException e){
            new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteEmployee/{employeeId}",method = RequestMethod.GET)
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
        audCustomerService.deleteEmployee(employeeId);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @RequestMapping(path = "/updateEmployee/{employeeId}",method = RequestMethod.POST)
    public ResponseEntity<String> updateEmployee(@RequestBody String empstr,@PathVariable int employeeId,HttpSession session){
        ObjectMapper mapper = new ObjectMapper();
        Supplier supplier = (Supplier) session.getAttribute("supplier");
        Employee employee = queryCustomerService.getEmployeeById(employeeId);
        employee.setSupplier(supplier);
        employee = CustomerController.employeeUpdateBasic(empstr,employee);
        audCustomerService.updateEmployee(employee);
        CustomerController.employeeUpdateContacts(empstr,employee);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteSupplier/{supplierId}",method = RequestMethod.GET)
    public ResponseEntity<String> deleteSupplier(@PathVariable int supplierId){
        supplyService.deleteSupplier(supplierId);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    @RequestMapping(value = "/nameList",method = RequestMethod.POST)
    public ResponseEntity<List<String>> getCompanyNameList(@RequestBody String name){
        List<String> nameList = supplyService.getSupplierNameList(WebUtils.trimJsonStr(name));
        return new ResponseEntity<List<String>>(nameList,HttpStatus.OK);
    }
    @RequestMapping(value = "/querySupplier",method = RequestMethod.GET)
    public String querySupplier(){
        return "/order/searchSupplier";
    }

    @RequestMapping(value = "/addTexture",method=RequestMethod.POST)
    public String addTexture(Texture texture, ModelMap mm){
        try {
            orderService.addTexture(texture);
            mm.addAttribute("addTextMsg","添加成功");
        }catch (Exception e){
            mm.put("addTextMsg","材质已经存在，请重新添加");
        }
        return "/order/searchSupplier";
    }
    @RequestMapping(value = "/queryByKeyWords",method = RequestMethod.GET)
    public String queryByKeyWords(@RequestParam("supplyName") String supplyName, HttpSession session){
        /*Page<Supplier> supplierPage = supplyService.getSupplierByKeys(supplyName,1,PageUtil.PAGE_SIZE);
        if (supplierPage.getAllData().size() ==1){
            Supplier supplier = supplierPage.getAllData().get(0);
            return "redirect:/supply/supplierDetail/" +supplier.getSupplierId() + ".html";
        }*/
        return queryByKeyWords(supplyName,1,PageUtil.PAGE_SIZE,session);
    }
    @RequestMapping(value = "/queryByKeyWords/{pageNo}",method = RequestMethod.GET)
    public String queryByKeyWords(@Nullable @RequestParam(value = "supplyName",required = false) String supplyName, @PathVariable int pageNo, HttpSession session){
        /*if (supplyName == null){
            supplyName = (String) session.getAttribute("paramView");
        }*/
        return queryByKeyWords(supplyName,pageNo,PageUtil.PAGE_SIZE,session);
    }

    @RequestMapping(value = "/queryByKeyWords/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public String queryByKeyWords(@Nullable @RequestParam(value = "supplyName",required = false) String supplyName,@PathVariable int pageNo,
                                  @PathVariable int pageSize, HttpSession session){
        Page<Supplier> supplierPage = null;
        //必须要这句，否则设置的paramView在再次翻页时就会变成null
        if (supplyName == null){
            supplyName = (String) session.getAttribute("paramView");
        }
        try{
            //如果supplyName ！= null,这时候需要重新查询，而不是使用现有的supplierPage
            if (session.getAttribute("thisView").equals("queryByKeyWords") && session.getAttribute("paramView").equals(supplyName)){
                supplierPage = getThisViewPage("queryByKeyWords",pageNo,pageSize,session);
            }
        }catch (NullPointerException e){}
        //其实是不存在supplierPage == null 并且supplyName == null的情况；
        if (supplierPage == null){
            supplierPage = supplyService.getSupplierByKeys(supplyName,pageNo,pageSize);
        }
        if (supplierPage.getAllData().size() ==1){
            Supplier supplier = supplierPage.getAllData().get(0);
            return "redirect:/supply/supplierDetail/" +supplier.getSupplierId() + ".html";
        }
        session.setAttribute("supplierPage",supplierPage);
        session.setAttribute("pageNo",supplierPage.getPageNo());
        session.setAttribute("pageSize",supplierPage.getPageSize());
        session.setAttribute("thisView","queryByKeyWords");
        session.setAttribute("paramView",supplyName);
        session.setAttribute("supplierListSize",supplierPage.getTotalCount());
        session.setAttribute("controller","supply");
        return "/order/supplierList";
    }
    @RequestMapping(value = "/queryByGoodAndTextureType",method = RequestMethod.GET)
    public String queryByGoodAndTextureType(@RequestParam("goodDetailType") String goodDetailType,
                                            @RequestParam(value = "textureType",required = false) String textureType,
                                            HttpSession session){
        return queryByGoodAndTextureType(goodDetailType,textureType,1,PageUtil.PAGE_SIZE,session);
    }
    @RequestMapping(value = "/queryByGoodAndTextureType/{pageNo}",method = RequestMethod.GET)
    public String queryByGoodAndTextureType(@Nullable @RequestParam(value = "goodDetailType",required = false) String goodDetailType,
                                            @Nullable @RequestParam(value = "textureType",required = false)  String textureType,
                                            @PathVariable int pageNo, HttpSession session) {
        //以上是不必要的，因为两个参数都没有的话，肯定是已经存在paramView
        return queryByGoodAndTextureType(goodDetailType,textureType,pageNo,PageUtil.PAGE_SIZE,session);
    }
    @RequestMapping(value = "/queryByGoodAndTextureType/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public String queryByGoodAndTextureType(@Nullable @RequestParam(value = "goodDetailType",required = false) String goodDetailType,
                                            @Nullable @RequestParam(value = "textureType",required = false)  String textureType,
                                            @PathVariable int pageNo, @PathVariable int pageSize, HttpSession session) {
        //第一次搜索，两个参数都为空
        if (pageNo == 1 && goodDetailType.equals("") && textureType.equals("")){
            return "redirect:/supply/allSuppliers";
        }
        //两个参数有一个不为空的话
        //如果是第一次搜索，则两个参数肯定不为null
        //第二次等搜索时，两个参数肯定都为null，那就会跳过，
        if (pageNo >= 1 && ((goodDetailType != null &&!goodDetailType.equals(""))  || (textureType != null &&!textureType.equals("")))){
            Page<Supplier> supplierPage = null;
            Map<String,String> paramMap = new HashMap<>();
            paramMap.put("goodDetailType",goodDetailType);
            paramMap.put("textureType",textureType);
            try {
                supplierPage = supplyService.getSupplierByType(goodDetailType, textureType, pageNo, pageSize);
            } catch (NoSuchEnumException e) {
                session.setAttribute("queryByGoodAndTextureTypeError",e.getMessage());
                return "/order/searchSupplier";
            }
            if (supplierPage.getTotalCount() == 1){
                Supplier supplier = supplierPage.getAllData().get(0);
                return "redirect:/supply/supplierDetail/" +supplier.getSupplierId() + ".html";
            }
            session.setAttribute("supplierPage",supplierPage);
            session.setAttribute("thisView","queryByGoodAndTextureType");
            session.setAttribute("paramMap",paramMap);
            session.setAttribute("supplierListSize",supplierPage.getTotalCount());
            session.setAttribute("pageNo",supplierPage.getPageNo());
            session.setAttribute("pageSize",supplierPage.getPageSize());
            session.setAttribute("controller","supply");
            return "/order/supplierList";
        }
        //两个参数都为空的话，那就重新把保存的参数取出来
        if (pageNo > 1){
            if (session.getAttribute("thisView").equals("queryByGoodAndTextureType")){
                Map<String,String> paramMap = (Map<String, String>) session.getAttribute("paramMap");
                return queryByGoodAndTextureType(paramMap.get("goodDetailType"),paramMap.get("textureType"),pageNo,pageSize,session);
            }
        }
        return "/order/searchSupplier";

    }

    @RequestMapping(value = "supplierGoods/{supplierId}",method = RequestMethod.GET)
    public String supplierGoods(@PathVariable int supplierId,@RequestParam(value = "thisMonth",required = false) boolean thisMonth,
                                @RequestParam(value = "onlyMe",required = false) boolean onlyMe,
                                @RequestParam(value = "fromDate",required = false) Date fromDate,
                                @RequestParam(value = "toDate",required = false) Date toDate,
                                ModelMap modelMap,HttpSession session){

        Supplier supplier = supplyService.getSupplierById(supplierId);
        //假设得到的是按orderId排序好的good；
        List<Good> goods = supplyService.getGoodsBySupplier(supplier);
        List<SupplierOrderBean> supplierOrderBeans = getSupplierOrderBeans(goods);
        flitSupplierOrderBeans(supplierOrderBeans,thisMonth,fromDate,toDate,onlyMe,session);
        modelMap.put("orderBeans",supplierOrderBeans);
        return "/order/goodList";
    }
    private List<SupplierOrderBean> flitSupplierOrderBeans(List<SupplierOrderBean> beans, boolean thisMonth,@Nullable Date fromDate,
                                                           @Nullable Date toDate, boolean onlyMe, HttpSession session){

        Date date = new Date(System.currentTimeMillis());
        User user = (User) session.getAttribute("user");
        if (thisMonth){
            beans.removeIf(bean -> bean.getCreateTime().getYear() != date.getYear() || bean.getCreateTime().getMonth() != date.getMonth());
        }
        if (fromDate != null){
            beans.removeIf(bean -> fromDate.getTime() > bean.getCreateTime().getTime());
        }
        if (toDate != null){
            beans.removeIf(bean -> toDate.getTime() < bean.getCreateTime().getTime());
        }
        if (onlyMe){
            beans.removeIf(bean -> !bean.getUser().getUsername().equals(user.getUsername()));
        }
        return beans;
    }
    private List<SupplierOrderBean> getSupplierOrderBeans(List<Good> goods){
        List<SupplierOrderBean> supplierOrderBeans = new ArrayList<>();
        Order order;
        //这种处理方式不方便对good进行排序；
        for (Good good : goods){
            order = orderService.getOrderByGood(good.getGoodId());
            InnerGoodBean innerGoodBean = InnerGoodBean.goodBeanFromGood(good);
            int size = supplierOrderBeans.size();
            //处理两种情况
            //1、supplierOrderBean为空；
            //2、supplierOrderBean不为空，需要重新添加元素；
            if (size == 0 || supplierOrderBeans.get(size-1).getOrderId() != order.getOrderId()){
                SupplierOrderBean bean = SupplierOrderBean.getSupplierOrderBean(order);
                //对supplierOrderBean进行处理
                Set<InnerGoodBean> innerGoodBeans = new HashSet<>();
                innerGoodBeans.add(innerGoodBean);
                //设置supplierOrderBean的统计成员
                bean = setSums(bean,innerGoodBean);
                bean.setGoods(innerGoodBeans);
                ////设置supplierOrderBean的第一个元素
                supplierOrderBeans.add(bean);
            }else{
                SupplierOrderBean bean = supplierOrderBeans.get(size -1);
                Set<InnerGoodBean> innerGoodBeans = bean.getGoods();
                innerGoodBeans.add(innerGoodBean);
                bean = countSums(bean,innerGoodBean);
            }
        }
        return supplierOrderBeans;
    }
    private SupplierOrderBean setSums(SupplierOrderBean bean,InnerGoodBean innerGoodBean){
        bean.setProfit(innerGoodBean.getProfit());
        bean.setTotalWeight(innerGoodBean.getWeight());
        bean.setTotalAmount(innerGoodBean.getAmount());
        bean.setTotalCost(innerGoodBean.getSumOfBuyMoney());
        bean.setTotalSums(innerGoodBean.getSumOfSalsMoney());
        return bean;
    }
    private SupplierOrderBean countSums(SupplierOrderBean bean,InnerGoodBean innerGoodBean){
        bean.setProfit(bean.getProfit() + innerGoodBean.getProfit());
        bean.setTotalWeight(bean.getTotalWeight() + innerGoodBean.getWeight());
        bean.setTotalAmount(bean.getTotalAmount()+ innerGoodBean.getAmount());
        bean.setTotalCost(bean.getTotalCost() + innerGoodBean.getSumOfBuyMoney());
        bean.setTotalSums(bean.getTotalSums() + innerGoodBean.getSumOfSalsMoney());
        return bean;
    }
    //用于构造一次性查询到很多页数据的情况
    private static Page<Supplier> getThisViewPage(String thisView,int pageNo,int pageSize,HttpSession session){
        Page<Supplier> supplierPage = null;
        try{
            if (session.getAttribute("thisView").equals(thisView)){
                supplierPage = (Page<Supplier>) session.getAttribute("supplierPage");
                supplierPage = new Page<>(supplierPage,pageNo);
                supplierPage.setPageNo(supplierPage.getPageNo());
                supplierPage.setPageSize(supplierPage.getPageSize());
            }
        }catch (Exception e){}
        if (supplierPage != null) {
            if (pageSize != 0) {
                supplierPage = new Page(supplierPage, pageNo, pageSize);
            }else {
                supplierPage = new Page(supplierPage,pageNo);
            }
        }
        return supplierPage;
    }
    private static void noResult(HttpSession session,String thisView,String controller,String msg){
        session.setAttribute("supplierPage",null);
        session.setAttribute("pageNo",1);
        session.setAttribute("pageSize",PageUtil.PAGE_SIZE);
        session.setAttribute("thisView",thisView);
        session.setAttribute("supplierListSize",0);
        session.setAttribute("controller",controller);
        session.setAttribute("msg",msg);
    }
}
