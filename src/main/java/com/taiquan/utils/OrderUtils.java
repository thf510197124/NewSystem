package com.taiquan.utils;

import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.order.goods.BanJuan;
import com.taiquan.domain.order.goods.Guan;
import com.taiquan.domain.order.goods.Xing;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderUtils {
    public static int getTotalAmount(Order order){
        Set<Good> goodList = order.getGoods();
        int totalAmount=0;
        for (Good good : goodList){
            totalAmount = totalAmount + good.getAmount();
        }
        return totalAmount;
    }
    public static float getTotalWeight(Order order){
        Set<Good> goods = order.getGoods();
        float totalWeight = 0f;
        for (Good good : goods){
            if (good.getGoodType().getName().equals("不锈钢板")){
               totalWeight = totalWeight +((BanJuan)good).getWeight();
            }else if (good.getGoodType().getName().equals("不锈钢管")){
                totalWeight = totalWeight + ((Guan)good).getWeight();
            }else if (good.getGoodType().getName().equals("不锈钢型材")){
                totalWeight = totalWeight + ((Xing)good).getWeight();
            }
        }
        return getPriciseFloat(totalWeight,2);
    }
    public static float getTotalSums(Order order){
        Set<Good> goods = order.getGoods();
        float totalSums = 0f;
        for (Good good : goods){
            totalSums = totalSums + good.getSumOfSalsMoney();
        }
        return getPriciseFloat(totalSums,2);
    }
    public static float getTotalCost(Order order){
        Set<Good> goods = order.getGoods();
        float totalCost = 0f;
        for (Good good:goods){
            totalCost = totalCost + good.getSumOfBuyMoney();
        }
        return getPriciseFloat(totalCost,2);
    }
    public static float getPriciseFloat(float a,int pricise){
        BigDecimal bigDecimal = new BigDecimal(Float.toString(a));
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
