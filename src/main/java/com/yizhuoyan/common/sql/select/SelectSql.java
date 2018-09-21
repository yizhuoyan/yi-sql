package com.yizhuoyan.common.sql.select;

import com.yizhuoyan.common.sql.Sql;
import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.sqlpart.*;

import lombok.Data;

import java.util.LinkedList;


/**
 * Created by Administrator on 2018/9/6 0006.
 */
@Data
public class SelectSql implements  PartSql<SelectSql> {

    private SelectPartSql selectPartSql;
    private FromPartSql fromPartSql;
    private WherePartSql wherePartSql;
    private GroupByPartSql groupByPartSql;
    private HavingPartSql havingPartSql;
    private OrderByPartSql orderByPartSql;
    private LimitPartSql limitPartSql;
    private ForUpdatePartSql forUpdatePartSql;
    private UnionPartSql unionPartSql;



    @Override
    public String getPartSql() {
        StringBuilder sql =new StringBuilder();
        if(selectPartSql!=null){
            sql.append(selectPartSql.getPartSql());
        }
        if(fromPartSql!=null){
            if(sql.length()>0){
                sql.append(' ');
            }
            sql.append(fromPartSql.getPartSql());
        }
        if(wherePartSql!=null){
            if(sql.length()>0){
                sql.append(' ');
            }
            sql.append(wherePartSql.getPartSql());
        }
        if(groupByPartSql!=null){
            if(sql.length()>0){
                sql.append(' ');
            }
            sql.append(groupByPartSql.getPartSql());
        }
        if(havingPartSql!=null){
            if(sql.length()>0){
                sql.append(' ');
            }
            sql.append(havingPartSql.getPartSql());
        }
        if(orderByPartSql!=null){
            if(sql.length()>0){
                sql.append(' ');
            }
            sql.append(orderByPartSql.getPartSql());
        }
        if(limitPartSql!=null){
            if(sql.length()>0){
                sql.append(' ');
            }
            sql.append(limitPartSql.getPartSql());
        }
        if(forUpdatePartSql!=null){
            if(sql.length()>0){
                sql.append(' ');
            }
            sql.append(forUpdatePartSql.getPartSql());
        }
        if(unionPartSql!=null){
            if(sql.length()>0){
                sql.append('\n');
            }
            String unionSql= Sqls.trim2null(unionPartSql.getPartSql());
            if(unionSql!=null){
                sql.append(unionSql);
            }
        }

        return sql.toString();
    }

    @Override
    public SelectSql getBuildSql() {
        return this;
    }

    @Override
    public String toString() {

        return getPartSql();
    }

}
