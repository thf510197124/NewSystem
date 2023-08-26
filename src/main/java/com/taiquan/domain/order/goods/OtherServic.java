package com.taiquan.domain.order.goods;


import com.taiquan.domain.order.Good;
import com.taiquan.domain.order.enums.goodDetailType.OtherServiceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "otherServicGood")
@Data
@EqualsAndHashCode(callSuper = true)
public class OtherServic extends Good {
    @Enumerated(EnumType.STRING)
    private OtherServiceType otherServiceType;

    public int compareTo(OtherServic otherServic){
        return otherServiceType.compareTo(otherServic.getOtherServiceType());
    }
}
