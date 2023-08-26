package com.taiquan.dao.customer;

import com.taiquan.dao.BaseDao;
import com.taiquan.domain.customer.Address;
import com.taiquan.exception.NoSuchPositionException;


import java.util.List;

public interface AddressDao extends BaseDao<Address> {
    public List<Address> getAddressByKeyWord(String addressKeyWord);
    public List<Address> getAddressByAround(String addressKeyWord,int miles) throws NoSuchPositionException;
}
