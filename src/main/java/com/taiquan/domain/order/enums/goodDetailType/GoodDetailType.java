package com.taiquan.domain.order.enums.goodDetailType;


import com.taiquan.exception.NoSuchEnumException;

import javax.persistence.Enumerated;

public enum GoodDetailType {
    冷轧2B,
    冷轧BA,
    热轧NO1,
    冷轧卷,
    热轧卷,
    冷轧钢带,
    热轧卷带,
    黑钛金镜面板,
    黑钛金拉丝板,
    玫瑰金镜面板,
    玫瑰金拉丝板,
    黄钛金镜面板,
    黄钛金拉丝板,
    无缝管,
    工业焊管,
    换热管,
    装饰管,
    毛细管,
    焊接换热管,
    卫生级无缝管,
    卫生级焊管,
    槽钢,
    角钢,
    方钢,
    冷拉扁钢,
    热轧扁钢,
    剪板扁钢,
    毛圆,
    光元,
    圆饼,
    零割,
    六角棒,
    八角棒,
    工字钢,
    封头,
    法兰,
    法兰盲板,
    弯头,
    三通,
    接头,
    大小头,
    阀门,
    人孔,
    焊丝,
    螺丝螺母,
    干磨拉丝,
    油磨长丝,
    油磨短丝,
    镜面8K,
    镜面16K,
    精磨8K,
    热轧油磨,
    热轧镜面,
    热轧抛光,
    剪板,
    折弯,
    整平,
    激光,
    分条,
    分卷,
    钢板镀色,
    蚀刻,
    机械加工,
    钢管抛光,
    开平,
    钢板零割,
    打磨,
    冲花,
    压花,
    卷圆,
    开槽,
    水刀切割,
    冲孔,
    其他,
    物流,
    木架,
    废料;

    @Override
    public String toString() {
        return super.toString();
    }

    public  static GoodDetailType getGoodDetailType(String name) throws NoSuchEnumException {
        if (name.equals("")){
            return null;
        }
        try {
            return GoodDetailType.valueOf(name);
        }catch (IllegalArgumentException e){
            throw new NoSuchEnumException("您输入的产品种类不存在！");
        }
    }
}
