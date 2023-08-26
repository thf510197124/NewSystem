package com.taiquan.utils;

import org.hibernate.dialect.MySQL5Dialect;

public class Mysql5InnoDBDialect extends MySQL5Dialect {
    @Override
    public String getTableTypeString(){
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
