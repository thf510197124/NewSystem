package com.taiquan.dao;

import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.utils.Page;
import com.taiquan.utils.PageUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.taiquan.utils.PrintUtil.println;

public class BaseDaoHibernate5<T> implements BaseDao<T>{
    private Class<T>         entityClass;

    private HibernateTemplate hibernateTemplate;

    private HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public BaseDaoHibernate5(){
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Type type =  parameterizedType.getActualTypeArguments()[0];
        if (type instanceof Class) {
            this.entityClass = (Class<T>) type;
        } else if (type instanceof ParameterizedType) {
            this.entityClass = (Class<T>) ((ParameterizedType)type).getRawType();
        }
    }
    public Session getSession(){
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }
    @Override
    public T load(Serializable id) {
        return getHibernateTemplate().load(entityClass,id);
    }

    @Override
    public T get(Serializable id) {
        return getHibernateTemplate().get(entityClass,id);
    }

    @Override
    public List<T> loadAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    @CachePut(value="fixedRegion",key = "#entity.id")
    public T save(T entity) {
        Serializable id = getHibernateTemplate().save(entity);
        return getHibernateTemplate().get(entityClass,id);
    }

    @Override
    @CacheEvict(value={"freqChangeRegion","fixedRegion"},key = "#entity.id")
    public void remove(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    @CacheEvict(value={"freqChangeRegion","fixedRegion"})
    public void removeAll(String tableName) {
        getSession().createQuery("truncate table " + tableName + "")
                .executeUpdate();
    }

    @Override
    public void removeSome(List<T> lists) {
        for (T t : lists){
            getHibernateTemplate().delete(t);
        }
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public List<T> find(final String sql) {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(sql);
                return query.list();
            }
        });
    }

    @Override
    public Object sqlQuery(String sql) {
        return getSession().createSQLQuery(sql).list().get(0);
    }

    @Override
    public List<T> sqlQueryList(String sql) {
        List list =  getSession().createSQLQuery(sql).addEntity(entityClass).list();
        List<T> lists = new ArrayList<>();
        for (Object enti :list){
            lists.add((T)enti);
        }
        return lists;
    }

    @Override
    public List sqlQueryForField(String sql) {
        return getSession().createSQLQuery(sql).list();
    }

    @Override
    public List<T> find(final String sql,final Object... params) {
        return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(sql);
                for (int i=0;i < params.length;i++){
                    println("param[i] = " + params[i]);
                    query.setParameter(i,params[i]);
                }
                return (List<T>)query.list();
            }
        });
    }

    @Override
    public List<T> namedParamFind(String hql, @Nullable Map<String, Object> params) {
        return getHibernateTemplate().execute(session -> {
            Query query = session.createQuery(hql);
            if (params != null) {
                for (String key : params.keySet()) {
                    query.setParameter(key, params.get(key));
                }
            }
            return (List<T>)query.list();
        });
    }

    @Override
    @Cacheable(value="fixedRegion")
    public T findOne(String sql, Object... params) throws NoSuchObjectException {
        try{
            return find(sql,params).get(0);
        }
        catch (Exception e){
            throw new NoSuchObjectException("参数太多？换个参数试试");
        }
    }

    @Override
    public T namedParamFindOne(String sql, Map<String, Object> param) throws NoSuchObjectException {
        try {
            return namedParamFind(sql,param).get(0);
        }catch (IndexOutOfBoundsException e){
            throw new NoSuchObjectException("");
        }

    }

    @Override
    public List<T> namedParamFindOne(String sql, String string1, Object obj) {
        Map<String,Object> map = new HashMap<>();
        map.put(string1,obj);
        return (List<T>) namedParamFind(sql,map);
    }

    @Override
    public void initlize(Object entity) {
        getHibernateTemplate().initialize(entity);
    }

    @Override
    public Page<T> pagedQuery(String hql, int pageNO, int pageSize, Object... params) {
        Assert.hasText(hql,"查询语句不能为空");
        Assert.isTrue(pageNO>=1,"pageNO should start from 1");
        String countQueryString = "select count (*) " + choseSelect(removeOrders(hql));
        List countList = find(countQueryString,params);
        int totalCount =new Long((Long)countList.get(0)).intValue();
        if (totalCount<1){
            return new Page(new ArrayList());
        }
        int startIndex = PageUtil.getStartOfPage(pageNO,pageSize);
        Query query = createQuery(hql,params);
        List<T> list = (List<T>)query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        Page<T> page = new Page<T>();
        page.setPageNo(pageNO);
        page.setPageSize(pageSize);
        page.setThisPageList(list);
        page.setTotalCount(totalCount);
        page.setAllPageNo(totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
        return page;
    }
    @Override
    public Page<T> pagedQuery(String hql,int pageNo, int pageSize,Map<String, Object> params) {
        Assert.hasText(hql,"查询语句不能为空");
        Assert.isTrue(pageNo>=1,"pageNO should start from 1");
        String countQueryString = "select count (*) " + choseSelect(removeOrders(hql));
        List countList = namedParamFind(countQueryString,params);
        int totalCount =new Long((Long)countList.get(0)).intValue();
        if (totalCount<1){
            return new Page(new ArrayList());
        }
        int startIndex = PageUtil.getStartOfPage(pageNo,pageSize);
        Query query = createQueryMap(hql,params);
        List<T> list = (List<T>)query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        Page<T> page = new Page<T>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setThisPageList(list);
        page.setTotalCount(totalCount);
        page.setAllPageNo(totalCount%pageSize == 0?totalCount/pageSize : totalCount/pageSize + 1);
        return page;
    }
    @Override
    public Query createQuery(String hql, Object... params) {
        Assert.hasText(hql,"查询语句不能为空");
        Query query = getSession().createQuery(hql);
        for (int i=0;i<params.length;i++){
            query.setParameter(i,params[i]);
        }
        return query;
    }
    @Override
    public Query createQueryMap(String hql, @Nullable Map<String,Object> params) {
        Assert.hasText(hql,"查询语句不能为空");
        Query query = getSession().createQuery(hql);
        if (params !=null) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query;
    }
    private static String choseSelect(String hql){
        Assert.hasText(hql,"hql查询语句不能为空！");
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1,"hql:" + hql + "must has a keyword 'from'");
        return hql.substring(beginPos);
    }
    private static String removeOrders(String hql){
        Assert.hasText(hql,"hql查询语句不能为空");
        Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",Pattern.CASE_INSENSITIVE);
        Matcher m       = pattern.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            m.appendReplacement(sb,"");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
