package com.taiquan.dao.customer.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.customer.CustomerDao;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.customerEnums.CapitalType;
import com.taiquan.domain.customerEnums.CustomerType;
import com.taiquan.domain.order.Order;
import com.taiquan.domain.users.User;
import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.utils.Page;
import net.sf.ehcache.statistics.CacheUsageListener;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.taiquan.utils.PrintUtil.println;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoHibernate5<Customer> implements CustomerDao {
    @Override
    public Page<Customer> getCustomerByUser(User user, int pageNo, int pageSize) {
        String hql = "from Customer cus where cus.user = ? order by cus.customerId desc";
        return pagedQuery(hql,pageNo,pageSize,user);
    }

    @Override
    public Customer getCustomerByCompany(Company company) throws NoSuchObjectException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("company",company);
        return namedParamFindOne("from Customer com where com.company = :company",map);
    }
    @Override
    public Page<Customer> getCustomerByContactPlanPage(@Nullable CustomerType customerType,
                                                       @Nullable Date nextDate, boolean isOnlyDate,
                                                       @Nullable Date updateTime,boolean onlyUpdate,
                                                       User user,boolean onlyMe,int pageNo,int pageSize) {
        Map<String,Object> map = new HashMap<>();
        StringBuilder hql = new StringBuilder();
        if (onlyMe){
            hql.append("select cus from Customer cus left join cus.contactPlan as con where cus.user = :user");
        }else{
            if (user.isMgr()){
                //处理管理员的情况
                hql.append("select cus from Customer cus left join cus.user user left join cus.contactPlan as con where user.manager = :user or user = :user");
            }else {
                hql.append("select cus from Customer cus left join cus.contactPlan as con where cus.user = :user");
            }
        }
        map.put("user",user);
        if (customerType != null && customerType != CustomerType.请选择){
            hql.append(" and con.customerType = :customerType ");
            map.put("customerType",customerType);
        }
        if(nextDate!=null){
            if (isOnlyDate){
                hql.append(" and con.nextDate = :nextDate ");
            }else {
                hql.append(" and con.nextDate <= :nextDate ");
            }
            map.put("nextDate",nextDate);
        }
        if (updateTime != null){
            if (onlyUpdate){
                hql.append(" and con.updateTime = :updateTime and con.customerType <> :customerType");
                map.put("customerType", CustomerType.尚未联系);
            }else{
                hql.append(" and con.updateTime = :updateTime ");
            }
            map.put("updateTime",updateTime);
        }
        return  pagedQuery(hql.toString(),pageNo,pageSize,map);
    }

    @Override
    public List<Customer> getCustomerByCompanyName(String companyName, User user) throws NoSuchObjectException {
        String hql = "select com from Customer com left join com.company comp where comp.companyName = :companyName and com.user =:user";
        Map<String,Object> map= new HashMap<>();
        map.put("companyName",companyName);
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomerByCompanyOwner(String owner, User user) throws NoSuchObjectException {
        String hql = "select com from Customer com left join com.company comp where comp.owner = :owner and com.user = :user";
        Map<String,Object> map= new HashMap<>();
        map.put("owner",owner);
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomerByCompanyBusiness(String business, User user) throws NoSuchObjectException {
        String hql = "select com from Customer com left join com.company comp where comp.businesses like '%" +  business  + "%' and com.user = :user";
        Map<String,Object> map= new HashMap<>();
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomerByPhoneNumber(String phoneNumber, User user) throws NoSuchObjectException {
        String hql = "select com from Customer com left join com.company comp where comp.phoneNumber = :phoneNumber and com.user = :user";
        Map<String,Object> map= new HashMap<>();
        map.put("phoneNumber",new PhoneNumber(phoneNumber));
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomerByCapital(float low, float heigh, User user) throws NoSuchObjectException {
        String hql = "from Customer com left join com.company comp where comp.capitalType = :capitalType1 and comp.capital between :low1 and :high1 and com.user = :user" +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType2 and comp.capital between :low2 and :high2 and com.user = :user " +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType3 and comp.capital between :low3 and :high3 and com.user = :user " +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType4 and comp.capital between :low4 and :high4 and com.user = :user " +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType5 and comp.capital between :low5 and :high5 and com.user = :user " +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType6 and comp.capital between :low6 and :high6 and com.user = :user " +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType7 and comp.capital between :low7 and :high7 and com.user = :user " +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType8 and comp.capital between :low8 and :high8 and com.user = :user " +
                "union from Customer com left join com.company comp where comp.capitalType = :capitalType9 and comp.capital between :low9 and :high9 and com.user = :user ";
        Map<String,Object> map= new HashMap<>();
        map.put("capitalType1", CapitalType.万元);map.put("low1",low);map.put("high1",heigh);
        map.put("capitalType2", CapitalType.万加元);map.put("low2",low/CapitalType.万加元.getCurrentcy());map.put("high2",heigh/CapitalType.万加元.getCurrentcy());
        map.put("capitalType3", CapitalType.万日元);map.put("low3",low/CapitalType.万日元.getCurrentcy());map.put("high3",heigh/CapitalType.万日元.getCurrentcy());
        map.put("capitalType4", CapitalType.万欧元);map.put("low4",low/CapitalType.万欧元.getCurrentcy());map.put("high4",heigh/CapitalType.万欧元.getCurrentcy());
        map.put("capitalType5", CapitalType.万澳元);map.put("low5",low/CapitalType.万澳元.getCurrentcy());map.put("high5",heigh/CapitalType.万澳元.getCurrentcy());
        map.put("capitalType6", CapitalType.万美元);map.put("low6",low/CapitalType.万英镑.getCurrentcy());map.put("high6",heigh/CapitalType.万英镑.getCurrentcy());
        map.put("capitalType7", CapitalType.万英镑);map.put("low7",low/CapitalType.万英镑.getCurrentcy());map.put("high7",heigh/CapitalType.万英镑.getCurrentcy());
        map.put("capitalType8", CapitalType.万港币);map.put("low8",low/CapitalType.万港币.getCurrentcy());map.put("high8",heigh/CapitalType.万港币.getCurrentcy());
        map.put("capitalType9", CapitalType.万韩元);map.put("low9",low/CapitalType.万韩元.getCurrentcy());map.put("high9",heigh/CapitalType.万韩元.getCurrentcy());
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomerByOthers(String other, User user) throws NoSuchObjectException {
        String hql = "select com from Customer com left join com.company comp where comp.others like '%" + other +"%' and com.user = :user";
        Map<String,Object> map= new HashMap<>();
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomerByCompanyAddTime(Date addDate, boolean onlyDate, User user) throws NoSuchObjectException {
        String hql = "";
        if (onlyDate){
            hql = "from Customer com left join com.company.companyLog log where log.addDate = :addDate and com.user = :user";
        }else{
            hql = "from Customer com left join com.company.companyLog log where log.addDate < :addDate and com.user = :user";
        }
        Map<String,Object> map= new HashMap<>();
        map.put("addTime",addDate);
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomersByEmployeeName(String name, User user) throws NoSuchObjectException {
        String hql = "select com from Customer com left join com.employees as employee left join com.company company " +
                "where (employee.name = :name or company.owner = :name) and com.user = :user";
        Map<String,Object> map= new HashMap<>();
        map.put("name",name);
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public List<Customer> getCustomersByNameKeyWord(String nameKeyWord, User user) throws NoSuchObjectException {
        String hql = "select com from Customer com left join com.company comp where comp.companyName like '%" + nameKeyWord + "%' and com.user = :user";
        Map<String,Object> map= new HashMap<>();
        map.put("user",user);
        return namedParamFind(hql,map);
    }

    @Override
    public Page<Customer> getCustomersByKeyWord(String keyWord, User user) {
        String hql = "select cus from Customer cus left join cus.company com where com.companyName like '%"
                + keyWord + "%' or com.businesses like '%" + keyWord + "%' or com.others like '%" + keyWord + "%'";
        List<Customer> customers = find(hql);
        return new Page<>(10,customers);
    }

    @Override
    public List<Customer> getCustomerByEmployee(Employee employee) {
        String hql = "select emp.customer from Employee emp where emp = :employee ";
        //String hql1 ="select cus from customer where (select emp from Employee emp where emp = :employee) in cus.employees";
        /*String hql = "from Customer cus inner join com.employees emp "*/
        Map<String,Object> map= new HashMap<>();
        map.put("employee",employee);
        return namedParamFind(hql,map);
    }
}
