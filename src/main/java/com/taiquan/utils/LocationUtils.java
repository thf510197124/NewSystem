package com.taiquan.utils;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.taiquan.utils.PrintUtil.println;

public class LocationUtils {
    private static double EARTH_RADIUS = 6378.137;
    private static Double rad(Double lat){
        return lat * Math.PI /180.0;
    }
    //得到的单位是米
    public static double getDistance(double lng1,double lat1,double lng2,double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return s;
    }

    /**
     * @param lng current position's longitude;
     * @param lat current position's latitude.
     * @param distance expected around distance with kilometers;
     * @return List<double> returns two double numbers,one is longtidude's round,when use is best way is +-longtide,and +-latitude
     * */
    public static List<Double> getRound(double lng,double lat,double distance){
        List<Double> round = new ArrayList<>();
        round.add(distance/111319*Math.cos(rad(lat)));
        //同一经度，每一个纬度相差111.3公里
        round.add(distance/111319);
        return round;

    }
}