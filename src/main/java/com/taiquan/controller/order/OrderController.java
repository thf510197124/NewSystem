package com.taiquan.controller.order;

import com.taiquan.bean.*;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.order.enums.GoodType;
import com.taiquan.domain.order.enums.orderStatus.OrderStatus;
import com.taiquan.domain.order.enums.OrderType;
import com.taiquan.domain.order.goods.*;
import com.taiquan.service.*;
import com.taiquan.utils.OrderUtils;
import com.taiquan.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.taiquan.utils.OrderUtils.*;
import static com.taiquan.utils.PrintUtil.println;

@Controller
@RequestMapping("/order")
public class OrderController {
    private AUDCustomerService audCustomerService;
    private QueryCustomerService queryCustomerService;
    private UserService userService;
    private OrderService orderService;

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

    @RequestMapping(value = "/updateOrder",method = RequestMethod.POST)
    public String updateOrder(OrderBean orderBean, HttpSession session,ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        order.setOrderType(OrderType.valueOf(orderBean.getOrderType()));
        order.setOrderStatus(OrderStatus.valueOf(orderBean.getOrderStatus()));
        order.setOthers(orderBean.getOthers());
        orderService.saveOrUpdate(order);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }

    @RequestMapping(value = "/addOrder/{customerId}",method = RequestMethod.GET)
    public String addOrder(@PathVariable int customerId, HttpSession session,ModelMap mm){
        Customer customer = queryCustomerService.getCustomerById(customerId);
        Order order = new Order();
        order.setUser(customer.getUser());
        order.setCustomer(customer);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setOrderType(OrderType.问价单);
        order.setOrderStatus(OrderStatus.询价中);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        order.setOrderName(customer.getCompany().getCompanyName() + sdf.format(new Date()));
        orderService.addOrder(order);
        session.setAttribute("order",order);
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customerId);
        session.setAttribute("updateStatus",0);
        createAddGoodModelMap(mm,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "addOrder/addBan",method = RequestMethod.POST)
    public String addBan(BanBean banBean,
                         HttpSession session, ModelMap modelMap){
        //必须在前段保证，添加某一项是，数量必须不为零
        Order order = (Order) session.getAttribute("order");
        orderService.saveOrUpdate(order);
        BanJuan banJuan = banBean.banBeanToBan(banBean);
        banJuan.setOrder(order);
        orderService.addBanJuan(banJuan);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        createAddGoodModelMap(modelMap,order.getOrderId());
        session.setAttribute("updateStatus",0);
        return "/order/addOrder";
    }
    @RequestMapping(value = "addOrder/addGuan",method = RequestMethod.POST)
    public String addGuan(GuanBean guanBean, HttpSession session, ModelMap modelMap){
        //必须在前段保证，添加某一项是，数量必须不为零
        Order order = (Order) session.getAttribute("order");
        //因为这时候不知道order是否已经持久化
        orderService.saveOrUpdate(order);

        Guan guan = guanBean.guanBeanToGuan(guanBean);
        guan.setOrder(order);
        orderService.addGuan(guan);

        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        createAddGoodModelMap(modelMap,order.getOrderId());
        session.setAttribute("updateStatus",0);
        return "/order/addOrder";
    }
    @RequestMapping(value = "addOrder/addXing",method = RequestMethod.POST)
    public String addXing(XingBean xingBean, HttpSession session, ModelMap modelMap){
        //必须在前段保证，添加某一项是，数量必须不为零
        Order order = (Order) session.getAttribute("order");
        orderService.saveOrUpdate(order);
        Xing xing = xingBean.getXing(xingBean);
        xing.setOrder(order);
        orderService.addXing(xing);

        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        createAddGoodModelMap(modelMap,order.getOrderId());
        session.setAttribute("updateStatus",0);
        return "/order/addOrder";
    }
    @RequestMapping(value = "addOrder/addLing",method = RequestMethod.POST)
    public String addLing(LingBean lingBean, HttpSession session, ModelMap modelMap){
        //必须在前段保证，添加某一项是，数量必须不为零
        Order order = (Order) session.getAttribute("order");
        orderService.saveOrUpdate(order);

        Ling ling = lingBean.getLing(lingBean);
        ling.setOrder(order);
        orderService.addLing(ling);

        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);

        createAddGoodModelMap(modelMap,order.getOrderId());
        session.setAttribute("updateStatus",0);
        return "/order/addOrder";
    }
    @RequestMapping(value = "addOrder/addJiaGong",method = RequestMethod.POST)
    public String addJiaGong(JiaGongBean jiaGongBean, HttpSession session, ModelMap modelMap){
        //必须在前段保证，添加某一项是，数量必须不为零
        Order order = (Order) session.getAttribute("order");
        orderService.saveOrUpdate(order);

        JiaGong jiaGong = jiaGongBean.getJiaGong(jiaGongBean);
        jiaGong.setOrder(order);
        orderService.addJiaGong(jiaGong);


        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",0);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "addOrder/addOtherServic",method = RequestMethod.POST)
    public String addOtherServic(OtherServicBean otherServicBean, HttpSession session, ModelMap modelMap){
        //必须在前段保证，添加某一项是，数量必须不为零
        Order order = (Order) session.getAttribute("order");
        orderService.saveOrUpdate(order);

        OtherServic otherServic = otherServicBean.getOtherServic(otherServicBean);
        otherServic.setOrder(order);
        orderService.addOtherServic(otherServic);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",0);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "deleteGood/{goodId}",method = RequestMethod.GET)
    public String deleteGood(@PathVariable int goodId, HttpSession session, ModelMap modelMap){
        Order order1 = (Order) session.getAttribute("order");
        Good good = orderService.getGoodById(goodId);
        orderService.deleteGood(good);
        //持久化状态
        Order order =orderService.getOrderById(order1.getOrderId());
        order.setTotalWeight(OrderUtils.getTotalWeight(order));
        order.setTotalAmount(OrderUtils.getTotalAmount(order));
        order.setTotalCost(getTotalCost(order));
        order.setTotalSums(getTotalSums(order));
        order.setProfit(getTotalSums(order) - getTotalCost(order));
        orderService.updateOrder(order);
        //如果商品不存在，则删除该order；
        if (order.getGoods() == null){
            orderService.deleteOrder(order);
        }
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",0);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    //修改产品程序，实际上是一个删除、添加程序
    @RequestMapping(value = "updateGood/{goodId}",method = RequestMethod.GET)
    public String updateGood(@PathVariable int goodId,HttpSession session,ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        Good good = orderService.getGoodById(goodId);
        if (good.getGoodType().equals(GoodType.BANJUAN)){
            modelMap.put("ban",BanBean.banJuanToBanBean((BanJuan) good));
        }else if (good.getGoodType().equals(GoodType.GUAN)){
            modelMap.put("guan",GuanBean.guanToGuanBean((Guan)good));
        }else if (good.getGoodType().equals(GoodType.XING)){
            modelMap.put("xing",XingBean.xingToXingBean((Xing) good));
        }else if (good.getGoodType().equals(GoodType.LING)){
            modelMap.put("ling",LingBean.lingToLingBean((Ling)good));
        }else if (good.getGoodType().equals(GoodType.JIAGONG)){
            modelMap.put("jia",JiaGongBean.jiaGongToBean((JiaGong) good));
        }else if (good.getGoodType().equals(GoodType.OTHER)){
            modelMap.put("other",OtherServicBean.otherServToOtherBean((OtherServic) good));
        }
        /*orderService.deleteGood(good);*/
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",1);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }

    @RequestMapping(value = "updateGood/updateBan",method = RequestMethod.POST)
    public String updateBan(BanBean ban, HttpSession session, ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        orderService.updateOrder(order);
        BanJuan banJuan = ban.banBeanToBan(ban);
        banJuan.setOrder(order);
        /*orderService.addBanJuan(banJuan);*/
        orderService.updateBanJuan(banJuan);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",2);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "updateGood/updateGuan",method = RequestMethod.POST)
    public String updateGuan(GuanBean guan, HttpSession session, ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        orderService.updateOrder(order);
        Guan guanCai = guan.guanBeanToGuan(guan);
        guanCai.setOrder(order);
        /*orderService.addGuan(guanCai);*/
        orderService.updateGuan(guanCai);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",2);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "updateGood/updateXing",method = RequestMethod.POST)
    public String updateXing(XingBean xing, HttpSession session, ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        orderService.updateOrder(order);
        Xing xingCai = xing.getXing(xing);
        xingCai.setOrder(order);
        /*orderService.addXing(xingCai);*/
        orderService.updateXing(xingCai);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",2);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "updateGood/updateLing",method = RequestMethod.POST)
    public String updateLing(LingBean ling, HttpSession session, ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        orderService.updateOrder(order);
        Ling lingjian = ling.getLing(ling);
        lingjian.setOrder(order);
        /*orderService.addLing(lingjian);*/
        orderService.updateLing(lingjian);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",2);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "updateGood/updateJia",method = RequestMethod.POST)
    public String updateJia(JiaGongBean jia, HttpSession session, ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        orderService.updateOrder(order);
        JiaGong jiaGong = jia.getJiaGong(jia);
        jiaGong.setOrder(order);
        /*orderService.addJiaGong(jiaGong);*/
        orderService.updateJia(jiaGong);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",2);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "updateGood/updateOther",method = RequestMethod.POST)
    public String updateOther(OtherServicBean other, HttpSession session, ModelMap modelMap){
        Order order = (Order) session.getAttribute("order");
        orderService.updateOrder(order);
        OtherServic otherServic = other.getOtherServic(other);
        otherServic.setOrder(order);
        /*orderService.addOtherServic(otherServic);*/
        orderService.updateOtherServic(otherServic);
        Customer customer = queryCustomerService.getCustomerById((Integer) session.getAttribute("customerId"));
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",2);
        createAddGoodModelMap(modelMap,order.getOrderId());
        return "/order/addOrder";
    }
    @RequestMapping(value = "customerOrders/{customerId}",method = RequestMethod.GET)
    public String customerOrders(@PathVariable int customerId,HttpSession session){
        Customer customer = queryCustomerService.getCustomerById(customerId);
        List<Order> orders = orderService.getOrderByCustomer(customer);
        Collections.sort(orders);
        Page<Order> orderPage = new Page<>(1,10,orders);
        session.setAttribute("orders",orders);
        session.setAttribute("customer",customer);
        session.setAttribute("orderPage",orderPage);
        session.setAttribute("pageNo",1);
        session.setAttribute("pageSize",10);
        session.setAttribute("orderListSize",orders.size());
        session.setAttribute("controller","order");
        session.setAttribute("thisView","customerOrders");
        return "/order/orderList";
    }
    @RequestMapping(value = "customerOrders/{customerId}/{pageNo}",method = RequestMethod.GET)
    public String customerOrders(@PathVariable int customerId,@PathVariable int pageNo,HttpSession session){
        Customer customer = queryCustomerService.getCustomerById(customerId);
        List<Order> orders = orderService.getOrderByCustomer(customer);
        for (Order order :orders){
            if (order.getGoods().size() < 1){
                orderService.deleteOrder(order);
            }
        }
        orders = orderService.getOrderByCustomer(customer);
        Collections.sort(orders);
        Page<Order> orderPage = new Page<>(pageNo,10,orders);
        session.setAttribute("orders",orders);
        session.setAttribute("customer",customer);
        session.setAttribute("orderPage",orderPage);
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageSize",10);
        session.setAttribute("orderListSize",orders.size());
        session.setAttribute("controller","order");
        session.setAttribute("thisView","customerOrders");
        return "/order/orderList";
    }
    @RequestMapping(value = "orderDetails/{orderId}",method = RequestMethod.GET)
    public ModelAndView orderDetails(@PathVariable int orderId,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Order order = orderService.getOrderById(orderId);
        Customer customer = (Customer) session.getAttribute("customer");
        session.setAttribute("customer",customer);
        session.setAttribute("customerId",customer.getCustomerId());
        session.setAttribute("order",order);
        session.setAttribute("updateStatus",2);
        modelAndView.addObject("customer",customer);
        createAddGoodModelMap(modelAndView.getModelMap(),orderId);
        modelAndView.setViewName("/order/addOrder");
        return modelAndView;
    }

    //该方法的作用
    //1、把众多的参数放进mov中
    //2、对order的参数进行更新；
    private void createAddGoodModelMap(ModelMap modelMap,int orderId){
        BanBean banBean1 = new BanBean();
        GuanBean guanBean1 = new GuanBean();
        JiaGongBean jiaGongBean1 = new JiaGongBean();
        LingBean lingBean1 = new LingBean();
        OtherServicBean otherServicBean1 = new OtherServicBean();
        XingBean xingBean1 = new XingBean();
        modelMap.put("banBean",banBean1);
        modelMap.put("guanBean",guanBean1);
        modelMap.put("lingBean",lingBean1);
        modelMap.put("otherServicBean",otherServicBean1);
        modelMap.put("xingBean",xingBean1);
        modelMap.put("jiaGongBean",jiaGongBean1);
        /*
        * 这一段代码是更新order的汇总属性
        * */
        Order order = orderService.getOrderById(orderId);
        order.setTotalWeight(getTotalWeight(order));
        order.setTotalAmount(getTotalAmount(order));
        order.setTotalCost(getTotalCost(order));
        order.setTotalSums(getTotalSums(order));
        order.setProfit(getPriciseFloat(getTotalSums(order) - getTotalCost(order),2));
        orderService.updateOrder(order);

        List<CustomerGoodBean> customerGoodBeans = getOrderGoods(order);
        List<InnerGoodBean> innerGoodBeans = getInnerGoodsBean(order);
        OrderBean orderBean = new OrderBean();
        if (order.getOrderStatus() !=null) {
            orderBean.setOrderStatus(order.getOrderStatus().toString());
        }
        if (order.getOrderType()!=null) {
            orderBean.setOrderType(order.getOrderType().toString());
        }
        if (order.getOthers()!=null) {
            orderBean.setOthers(order.getOthers());
        }
        modelMap.put("orderBean",orderBean);
        modelMap.put("order",order);
        modelMap.put("customerGoodBeans", customerGoodBeans);
        modelMap.put("innerGoodBeans",innerGoodBeans);
        /*modelMap.put("orderAmounts", order.getTotalAmount());
        modelMap.put("orderWeights",order.getTotalWeight());
        modelMap.put("orderSums",order.getTotalSums());*/
    }
    private static List<CustomerGoodBean> getOrderGoods(Order order){
        Set<Good> goodSet = order.getGoods();
        List<Good> goods= new ArrayList<Good>(goodSet);
        Collections.sort(goods);
        List<CustomerGoodBean> customerGoodBeans = new ArrayList<>();
        //按照顺序依次添加
        if (goods!=null) {
            for (Good good : goods) {
                if (good.getClass().getSimpleName().equals("BanJuan")) {
                    customerGoodBeans.add(CustomerGoodBean.getGoodBeanFromGood(good));
                }
            }
            for (Good good : goods) {
                if (good.getClass().getSimpleName().equals("Guan")) {
                    customerGoodBeans.add(CustomerGoodBean.getGoodBeanFromGood(good));
                }
            }
            for (Good good : goods) {
                if (good.getClass().getSimpleName().equals("Xing")) {
                    customerGoodBeans.add(CustomerGoodBean.getGoodBeanFromGood(good));
                }
            }
            for (Good good : goods) {
                if (good.getClass().getSimpleName().equals("Ling")) {
                    customerGoodBeans.add(CustomerGoodBean.getGoodBeanFromGood(good));
                }
            }
            for (Good good : goods) {
                if (good.getClass().getSimpleName().equals("JiaGong")) {
                    customerGoodBeans.add(CustomerGoodBean.getGoodBeanFromGood(good));
                }
            }
            for (Good good : goods) {
                if (good.getClass().getSimpleName().equals("OtherServic")) {
                    customerGoodBeans.add(CustomerGoodBean.getGoodBeanFromGood(good));
                }
            }
        }
        //把售价为零的部分，展示时取出
        for (CustomerGoodBean innerGoodBean : new HashSet<>(customerGoodBeans)){
            if (innerGoodBean.getSums() == 0f){
                customerGoodBeans.remove(innerGoodBean);
            }
        }
        return customerGoodBeans;
    }
    private static List<InnerGoodBean> getInnerGoodsBean(Order order){
        Set<Good> goodSet = order.getGoods();
        List<Good> goods= new ArrayList<Good>(goodSet);
        Collections.sort(goods);
        List<InnerGoodBean> customerGoodBeans = new ArrayList<>();
        //按照顺序依次添加
        if (goods!=null) {
            for (Good good : goods) {
                if (good.getGoodType().equals(GoodType.BANJUAN)) {
                    customerGoodBeans.add(InnerGoodBean.goodBeanFromGood(good));
                }
            }
            for (Good good : goods) {
                if (good.getGoodType().equals(GoodType.GUAN)) {
                    customerGoodBeans.add(InnerGoodBean.goodBeanFromGood(good));
                }
            }
            for (Good good : goods) {
                if (good.getGoodType().equals(GoodType.XING)) {
                    customerGoodBeans.add(InnerGoodBean.goodBeanFromGood(good));
                    ;
                }
            }
            for (Good good : goods) {
                if (good.getGoodType().equals(GoodType.LING)) {
                    customerGoodBeans.add(InnerGoodBean.goodBeanFromGood(good));
                    ;
                }
            }
            for (Good good : goods) {
                if (good.getGoodType().equals(GoodType.JIAGONG)) {
                    customerGoodBeans.add(InnerGoodBean.goodBeanFromGood(good));
                    ;
                }
            }
            for (Good good : goods) {
                if (good.getGoodType().equals(GoodType.OTHER)) {
                    customerGoodBeans.add(InnerGoodBean.goodBeanFromGood(good));
                    ;
                }
            }
        }

        return customerGoodBeans;
    }
    private static void main(String[] args){

    }
}
