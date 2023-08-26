package com.taiquan.domain.order.goods;


import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.enums.goodDetailType.JiaGongType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "jiaGongGood")
@Data
@EqualsAndHashCode(callSuper = true)
public class JiaGong extends Good {
    @Enumerated(EnumType.STRING)
    private JiaGongType jiaGongType;

    public int compareTo(@NotNull JiaGong jiaGong){
        return jiaGongType.compareTo(jiaGong.getJiaGongType());
    }
}
