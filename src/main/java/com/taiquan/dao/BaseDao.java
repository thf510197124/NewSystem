package com.taiquan.dao;

import com.taiquan.exception.NoSuchObjectException;
import com.taiquan.utils.Page;
import org.hibernate.Query;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    public T load(Serializable id);
    public T get(Serializable id);
    public List<T> loadAll();
    public T save(T entity);
    public void remove(T entity);
    public void removeAll(String tableName);
    public void removeSome(List<T> lists);
    public void update(T entity);
    public void saveOrUpdate(T entity);
    public List<T> find(String sql);
    public Object sqlQuery(String sql);
    public List<T> sqlQueryList(String sql);
    public List sqlQueryForField(String sql);
    public List<T> find(String sql,Object...params);
    public List<T> namedParamFind(String hql,Map<String,Object> params);
    public T findOne(String sql,Object...params) throws NoSuchObjectException;
    public T namedParamFindOne(String sql,Map<String,Object> param) throws NoSuchObjectException;
    public List<T> namedParamFindOne(String sql,String string1,Object obj);
    public void initlize(Object entity);
    public Page<T> pagedQuery(String hql, int pageNO, int pageSize, Object...params);
    public Page<T> pagedQuery(String hql, int pageNo, int pageSize,@Nullable Map<String,Object> params);
    public Query createQuery(String hql, Object...params);
    public Query createQueryMap(String hql, Map<String,Object> params);

}
