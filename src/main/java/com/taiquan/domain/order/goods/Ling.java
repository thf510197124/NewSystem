package com.taiquan.domain.order.goods;


import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.enums.goodDetailType.LingBuJianType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "lingGood")
@Data
@EqualsAndHashCode(callSuper = true)
public class Ling extends Good {

    @Enumerated(EnumType.STRING)
    private LingBuJianType lingBuJianType;

    public int compareTo(@NotNull Ling ling){
        if (lingBuJianType == ling.getLingBuJianType()){
            return getTextureType().compareTo(ling.getTextureType());
        }
        return lingBuJianType.compareTo(ling.getLingBuJianType());
    }

}
