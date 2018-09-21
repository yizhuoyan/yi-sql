package com.yizhuoyan.common.sql.delete;

import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.where.WhereConditionsPartSql;

/**
 * Created by Administrator on 2018/9/7 0007.
 */
public class DeleteSQL extends WhereConditionsPartSql {
    private final String table;

    public DeleteSQL(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        StringBuilder sql=new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(table);
        String where= Sqls.trim2null(super.toString());
        if(where!=null) {
            sql.append(" WHERE ").append(where);
        }
        return sql.toString();
    }
}
