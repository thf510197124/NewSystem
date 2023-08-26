package com.taiquan.utils;
import java.util.List;

public class ListUtil {
    public static <T>int getIndex(List<T> customers,T customer){
        if(customers.contains(customer)){
            return customers.indexOf(customer);
        }else {
            return -1;
        }
    }

    public static <T> T nextCustomer(List<T> tList,T customer){
        int index = getIndex(tList,customer);
        if(index==tList.size()){
            return tList.get(0);
        }else  if (index != -1){
            return tList.get(index+1);
        }else{
            return null;
        }
    }
    public static <T>T prevCustomer(List<T> tList,T customer){
        int index = getIndex(tList,customer);
        if(index>1){
            return tList.get(index-1);
        }else if (index==0){
            return tList.get(tList.size()-1);
        } else{
            return null;
        }
    }
}
