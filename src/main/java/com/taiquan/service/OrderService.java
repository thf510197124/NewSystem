package com.taiquan.service;

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

import java.util.Date;
import java.util.List;

public interface OrderService {
    public Good addGood(Good good);
    /*public List<Good> addGoodList(List<Good> goods);*/
    public void deleteGood(Good good);
    /*public void deleteGoods(List<Good> goods);*/
    public void updateGood(Good good);
    public Good getGoodById(int goodId);
    /*public List<Good> getGoodByGoodType(GoodType goodType);*/
    public List<Good> getGoodByTextureType(TextureType textureType);

    public Order addOrder(Order order);
    public void deleteOrder(Order order);
    public Order getOrderById(int orderId);
    public Order updateOrder(Order order);
    public List<Order> getOrderByAddTime(User user,Date from, Date to);
    public List<Order> getOrderByOrderTypeAndTime(User user,OrderType orderType, Date from, Date to);
    public int getOrderNumbersByCustomer(Customer customer);
    public List<Order> getOrderByCustomer(Customer customer);
    public List<Order> getOrderByUser(User user);


    void addBanJuan(BanJuan banJuan);
    void addGuan(Guan guan);
    void addOtherServic(OtherServic otherServic);
    void addJiaGong(JiaGong jiaGong);
    void addLing(Ling ling);
    void addXing(Xing xing);

    void saveOrUpdate(Order order);

    int getGoodSizeBySupplier(Supplier supplier);
    List<Good> getGoodsBySupplier(Supplier supplier);

    List<Texture> loadAllTexture();
    void addTexture(Texture texture);

    void updateBanJuan(BanJuan banJuan);
    void updateGuan(Guan guan);
    void updateXing(Xing xing);
    void updateLing(Ling ling);
    void updateJia(JiaGong jiaGong);
    void updateOtherServic(OtherServic otherServic);

    Order getOrderByGood(int goodId);
}
