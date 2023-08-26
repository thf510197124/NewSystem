package com.taiquan.domain.customer;

import com.taiquan.convertion.PhoneNumbersFormat;
import com.taiquan.domain.BaseDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.util.Locale;

//仅仅作为一个嵌入属性使用
@Embeddable
public class PhoneNumber extends BaseDomain implements Comparable<PhoneNumber>{
    private String numbers;

    public PhoneNumber(){}
    public PhoneNumber(String numbers){
        this.numbers = PhoneNumbersFormat.numberOnly(numbers);
    }
    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;

        PhoneNumber that = (PhoneNumber) o;

        return PhoneNumbersFormat.numberOnly(getNumbers()).equals(PhoneNumbersFormat.numberOnly(that.getNumbers()));
    }

    @Override
    public int hashCode() {
        return PhoneNumbersFormat.numberOnly(getNumbers()).hashCode();
    }

    @Override
    public String toString() {
        return new PhoneNumbersFormat().print(this, Locale.CHINA);
    }

    @Override
    public int compareTo(@NotNull PhoneNumber o) {
        return o.getNumbers().compareTo(getNumbers());
    }
}
