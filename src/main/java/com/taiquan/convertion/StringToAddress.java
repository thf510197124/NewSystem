package com.taiquan.convertion;

import com.taiquan.domain.customer.Address;
import com.taiquan.utils.AddressUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToAddress implements Converter<String,Address> {
    @Override
    public Address convert(String source) {
        return AddressUtils.createAddress(source);
    }
}
