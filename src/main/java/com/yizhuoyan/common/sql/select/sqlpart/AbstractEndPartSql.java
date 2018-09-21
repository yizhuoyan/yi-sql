package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.PartSql;
import com.yizhuoyan.common.sql.select.SelectSql;

/**
 * Created by Administrator on 2018/9/7 0007.
 */
public abstract class AbstractEndPartSql implements PartSql<SelectSql> {
    protected  final SelectSql buildSql;

    public AbstractEndPartSql(SelectSql buildSql) {
        this.buildSql=buildSql;
    }

    public SelectSql limit(int begin, int offset) {
        LimitPartSql limitPartSql = new LimitPartSql(buildSql, begin, offset);
        return buildSql;
    }

    public SelectSql limitByPage(int pageNo, int pageSize){
        return limit((pageNo - 1) * pageSize, pageSize);
    }


    public SelectSql forUpdate(){
        ForUpdatePartSql forUpdatePartSql=new ForUpdatePartSql(buildSql);
        return buildSql;
    }

    public UnionPartSql union(PartSql<SelectSql> unionSql){
        return new UnionPartSql(buildSql).union(unionSql);
    }
    public UnionPartSql unionAll(PartSql<SelectSql> unionSql){
        return new UnionPartSql(buildSql).unionAll(unionSql);
    }

    @Override
    public SelectSql getBuildSql() {
        return buildSql;
    }
}
