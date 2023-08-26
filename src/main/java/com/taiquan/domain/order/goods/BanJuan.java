package com.taiquan.domain.order.goods;



import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.enums.goodDetailType.PlainType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "banJuanGood")
@Data
@EqualsAndHashCode(callSuper = true)
public class BanJuan  extends Good {
    @NumberFormat(pattern = "###.00")
    private float thick;
    private int width;
    private int length;
    @NumberFormat(pattern = "###.00")
    private float realThick;

    @Enumerated(EnumType.STRING)
    private PlainType plainType;
    private String chanDi;
    @NumberFormat(pattern = "###，###.00")
    private float weight;

    public int compareTo(@NotNull BanJuan banJuan) {
        if (plainType == banJuan.getPlainType()){
            if (getTextureType() == banJuan.getTextureType()){
                if (thick == banJuan.getThick()){
                    return width - banJuan.getWidth();
                }else if(thick > banJuan.getThick()){
                    return 1;
                }else{
                    return -1;
                }
            }
            else{
                return getTextureType().compareTo(banJuan.getTextureType());
            }
        }
        return plainType.compareTo(banJuan.getPlainType());
    }

}
