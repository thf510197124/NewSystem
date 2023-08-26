package com.taiquan.domain.order.goods;



import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.enums.goodDetailType.XingCaiType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "xingGood")
@Data
@EqualsAndHashCode(callSuper = true)
public class Xing extends Good {
    @Enumerated(EnumType.STRING)
    private XingCaiType xingCaiType;

    @NumberFormat(pattern = "###，###.00")
    private float weight;

    public int compareTo(@NotNull Xing xing){
        if (xingCaiType == xing.getXingCaiType()){
            if (getTextureType()==xing.getTextureType()){
                return this.getSpec().compareTo(xing.getSpec());
            }else{
                return getTextureType().compareTo(xing.getTextureType());
            }
        }else{
            return xingCaiType.compareTo(xing.getXingCaiType());
        }
    }
}
