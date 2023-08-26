package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.AddressDao;
import com.taiquan.domain.customer.Address;
import com.taiquan.exception.NoSuchPositionException;
import com.taiquan.utils.AddressUtils;
import com.taiquan.utils.Corderinate;
import com.taiquan.utils.LocationUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

@Repository("addressDao")
public class AddressDaoImpl extends BaseDaoHibernate5<Address> implements AddressDao {

    public List<Address> getAddressByKeyWord(String addressKeyWord){
        String hql = "from Address add where add.simple like '%" + addressKeyWord + "%' or add.formatterAddress like '%" + addressKeyWord + "%'";
        return find(hql);
    }
    public List<Address> getAddressByAround(String addressKeyWord,int miles) throws NoSuchPositionException{
        Corderinate position = AddressUtils.getGeocoderAngel(addressKeyWord);
        if (position == null){
            throw new NoSuchPositionException("没找到这个地名");
        }
        List<Double> round   = LocationUtils.getRound(position.getLng().doubleValue(),position.getLat().doubleValue(),miles);
        BigDecimal lngSmall = position.getLng().subtract(BigDecimal.valueOf(round.get(0)));
        BigDecimal lngBig = position.getLng().add(BigDecimal.valueOf(round.get(0)));
        BigDecimal latSmall = position.getLat().subtract(BigDecimal.valueOf(round.get(1)));
        BigDecimal latBig = position.getLat().add(BigDecimal.valueOf(round.get(1)));
        String hql = "from Address a " +
                "where a.longitude between :lngSmall and :lngBig " +
                "and a.latitude between :latSmall and :latBig";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("lngSmall",lngSmall);
        map.put("lngBig",lngBig);
        map.put("latSmall",latSmall);
        map.put("latBig",latBig);
        return namedParamFind(hql,map);
    }
}
