package com.taiquan.service.impl;

import com.taiquan.dao.order.GoodDao;
import com.taiquan.dao.order.OrderDao;
import com.taiquan.dao.order.SupplierDao;
import com.taiquan.dao.order.TextureDao;
import com.taiquan.dao.order.goods.*;
import com.taiquan.domain.customer.Customer;
import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.order.Supplier;
import com.taiquan.domain.order.Texture;
import com.taiquan.domain.order.enums.GoodType;
import com.taiquan.domain.order.enums.OrderType;
import com.taiquan.domain.order.enums.TextureType;
import com.taiquan.domain.order.goods.*;
import com.taiquan.domain.users.User;
import com.taiquan.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private GoodDao goodDao;
    private OrderDao orderDao;
    private SupplierDao supplierDao;
    private BanJuanDao banJuanDao;
    private GuanDao guanDao;
    private JiaGongDao jiaGongDao;
    private LingDao lingDao;
    private OtherServicDao otherServicDao;
    private XingDao xingDao;
    private TextureDao textureDao;

    public GoodDao getGoodDao() {
        return goodDao;
    }
    @Autowired
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    @Autowired
    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public BanJuanDao getBanJuanDao() {
        return banJuanDao;
    }

    @Autowired
    public void setBanJuanDao(BanJuanDao banJuanDao) {
        this.banJuanDao = banJuanDao;
    }

    public GuanDao getGuanDao() {
        return guanDao;
    }

    @Autowired
    public void setGuanDao(GuanDao guanDao) {
        this.guanDao = guanDao;
    }

    public JiaGongDao getJiaGongDao() {
        return jiaGongDao;
    }

    @Autowired
    public void setJiaGongDao(JiaGongDao jiaGongDao) {
        this.jiaGongDao = jiaGongDao;
    }

    public LingDao getLingDao() {
        return lingDao;
    }

    @Autowired
    public void setLingDao(LingDao lingDao) {
        this.lingDao = lingDao;
    }

    public OtherServicDao getOtherServicDao() {
        return otherServicDao;
    }

    @Autowired
    public void setOtherServicDao(OtherServicDao otherServicDao) {
        this.otherServicDao = otherServicDao;
    }

    public XingDao getXingDao() {
        return xingDao;
    }

    @Autowired
    public void setXingDao(XingDao xingDao) {
        this.xingDao = xingDao;
    }

    public TextureDao getTextureDao() {
        return textureDao;
    }

    @Autowired
    public void setTextureDao(TextureDao textureDao) {
        this.textureDao = textureDao;
    }
    @Override
    public Good addGood(Good good) {
        return goodDao.save(good);
    }



    /*@Override
    public List<Good> addGoodList(List<Good> goods) {
        return null;
    }*/

    @Override
    public void deleteGood(Good good) {
        goodDao.remove(good);
    }

    /*@Override
    public void deleteGoods(List<Good> goods) {

    }*/

    @Override
    public void updateGood(Good good) {
        goodDao.update(good);
    }

    @Override
    public Good getGoodById(int goodId) {
        return goodDao.get(goodId);
    }

//    @Override
//    public List<Good> getGoodByGoodType(GoodType goodType) {
//        return goodDao.getGoodByGoodType(goodType);
//    }

    @Override
    public List<Good> getGoodByTextureType(TextureType textureType) {
        return goodDao.getGoodByTextureType(textureType);
    }

    @Override
    public Order addOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderDao.remove(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDao.get(orderId);
    }

    @Override
    public Order updateOrder(Order order) {
        orderDao.update(order);
        return order;
    }

    @Override
    public List<Order> getOrderByAddTime(User user,Date from, Date to) {
        return orderDao.getOrderByAddTime(user,from,to);
    }

    @Override
    public List<Order> getOrderByOrderTypeAndTime(User user,OrderType orderType, Date from, Date to) {
        return orderDao.getOrderByOrderTypeAndTime(user,orderType,from,to);
    }

    @Override
    public int getOrderNumbersByCustomer(Customer customer) {
        return getOrderByCustomer(customer).size();
    }

    @Override
    public List<Order> getOrderByCustomer(Customer customer) {
        List<Order> orders = orderDao.getOrderByCustomer(customer);
        for (Order order :new ArrayList<>(orders)){
            if (order.getGoods().size() < 1){
                orderDao.remove(order);
            }
        }
        return orderDao.getOrderByCustomer(customer);
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        return orderDao.getOrderByUser(user);
    }

    @Override
    public void addBanJuan(BanJuan banJuan) {
        banJuanDao.save(banJuan);
    }

    @Override
    public void addGuan(Guan guan) {
        guanDao.save(guan);
    }

    @Override
    public void addOtherServic(OtherServic otherServic) {
        otherServicDao.save(otherServic);
    }

    @Override
    public void addJiaGong(JiaGong jiaGong) {
        jiaGongDao.save(jiaGong);
    }

    @Override
    public void addLing(Ling ling) {
        lingDao.save(ling);
    }

    @Override
    public void addXing(Xing xing) {
        xingDao.save(xing);
    }

    @Override
    public void saveOrUpdate(Order order) {
        /*if (order.getOrderId()==0){
            return orderDao.save(order);
        }else{
           orderDao.update(order);
           return orderDao.get(order.getOrderId());
        }*/
        orderDao.saveOrUpdate(order);
    }

    @Override
    public int getGoodSizeBySupplier(Supplier supplier) {
        return goodDao.getGoodSizeBySupplier(supplier);
    }

    @Override
    public List<Good> getGoodsBySupplier(Supplier supplier) {
        return goodDao.getGoodsBySupplier(supplier);
    }

    @Override
    public List<Texture> loadAllTexture() {
        return textureDao.loadAll();
    }

    @Override
    public void addTexture(Texture texture) {
        textureDao.save(texture);
    }

    @Override
    public void updateBanJuan(BanJuan banJuan) {
        banJuanDao.update(banJuan);
    }

    @Override
    public void updateGuan(Guan guan) {
        guanDao.update(guan);
    }

    @Override
    public void updateXing(Xing xing) {
        xingDao.update(xing);
    }

    @Override
    public void updateLing(Ling ling) {
        lingDao.update(ling);
    }

    @Override
    public void updateJia(JiaGong jiaGong) {
        jiaGongDao.update(jiaGong);
    }

    @Override
    public void updateOtherServic(OtherServic otherServic) {
        otherServicDao.update(otherServic);
    }

    @Override
    public Order getOrderByGood(int goodId) {
        return orderDao.getOrderByGoodId(goodId);
    }


}
