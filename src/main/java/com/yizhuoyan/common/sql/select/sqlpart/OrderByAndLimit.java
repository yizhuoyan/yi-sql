package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.select.SelectSql;

/**
 * Created by Administrator on 2018/9/7 0007.
 */
public abstract class OrderByAndLimit extends  AbstractEndPartSql {
    public OrderByAndLimit(SelectSql buildSql) {
        super(buildSql);
    }
    public OrderByPartSql orderBy(String orderBy, String... others) {
        OrderByPartSql orderByPartSql = new OrderByPartSql(buildSql,orderBy, others);
        return orderByPartSql;
    }



}
