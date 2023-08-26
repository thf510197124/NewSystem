package com.taiquan.domain.order.goods;



import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.enums.goodDetailType.PupeType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "guanGood")
@Data
@EqualsAndHashCode(callSuper = true)
public class Guan extends Good {

    @Enumerated(EnumType.STRING)
    private PupeType pupeType;

    @NumberFormat(pattern = "###.00")
    private float thick;

    @NumberFormat(pattern = "###.00")
    private float realThick;

    @NumberFormat(pattern = "#，###.0")
    private float diameter;
    @NumberFormat(pattern = "#，###.0")
    private float diameter2;
    private int length;

    @NumberFormat(pattern = "###，###.00")
    private float weight;

    public int compareTo(@NotNull Guan guan) {
        if (pupeType == guan.getPupeType()){
            if (getTextureType() == guan.getTextureType()) {
                if (diameter2 == guan.getDiameter2()) {
                    if (diameter == guan.getDiameter()) {
                        return ((Float) (thick - guan.getThick())).intValue();
                    } else {
                        return ((Float) (diameter - guan.getDiameter())).intValue();
                    }
                } else {
                    return ((Float) (diameter2 - guan.getDiameter2())).intValue();
                }
            }else{
                return getTextureType().compareTo(guan.getTextureType());
            }
        }
        return pupeType.compareTo(guan.getPupeType());
    }
}
