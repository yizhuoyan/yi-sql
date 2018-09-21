package com.yizhuoyan.common.sql.update;

import com.yizhuoyan.common.sql.SqlRuntimeException;
import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.where.WhereConditionsPartSql;

import java.util.LinkedList;

/**
 * Created by Administrator on 2018/9/7 0007.
 */
public class UpdateSql extends WhereConditionsPartSql {
    final String table;
    final LinkedList<String> sets=new LinkedList<>();

    public UpdateSql(String table) {
        this.table = table;
    }

    public UpdateSql set(String... parts){
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sql=new StringBuilder();
        sql.append("UPDATE ");
        sql.append(table);
        if(sets.isEmpty()){
            throw  new SqlRuntimeException("must at least have one set ");
        }
        sql.append(" SET ").append(sets.pop());
        for (String setPart:sets) {
            sql.append(",").append(setPart);
        }

        String where= Sqls.trim2null(super.toString());
        if(where!=null) {
            sql.append(" WHERE ").append(where);
        }
        return sql.toString();
    }
}
