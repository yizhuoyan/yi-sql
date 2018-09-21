package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.PartSql;
import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.where.WhereCondition;
import com.yizhuoyan.common.sql.where.WhereConditionsPartSql;
import com.yizhuoyan.common.sql.select.SelectSql;

import java.util.LinkedList;

/**
 * Created by Administrator on 2018/9/7 0007.
 */
public class WherePartSql extends OrderByAndLimit {
    private final WhereConditionsPartSql conditionPartSql;

    public WherePartSql(SelectSql buildSql) {
        super(buildSql);
        buildSql.setWherePartSql(this);
        conditionPartSql = new WhereConditionsPartSql();
    }

    public LinkedList<WhereCondition> getConditions() {
        return conditionPartSql.getConditions();
    }


    public WherePartSql and(String conditionSql) {
        conditionPartSql.and(conditionSql);
        return this;
    }
    public WherePartSql andIf(boolean b,String conditionSql) {
        conditionPartSql.andIf(b,conditionSql);
        return this;
    }

    public WherePartSql or(String conditionSql) {
        conditionPartSql.or(conditionSql);
        return this;
    }
    public WherePartSql orIf(boolean b,String conditionSql) {
        conditionPartSql.orIf(b,conditionSql);
        return this;
    }
    public WherePartSql and(WhereConditionsPartSql conditionSql) {
        conditionPartSql.and(conditionSql);
        return this;
    }
    public WherePartSql andIf(boolean b,WhereConditionsPartSql conditionSql) {
        conditionPartSql.andIf(b,conditionSql);
        return this;
    }


    public WherePartSql or(WhereConditionsPartSql conditionSql) {
        conditionPartSql.or(conditionSql);
        return this;
    }
    public WherePartSql orIf(boolean b,WhereConditionsPartSql conditionSql) {
        conditionPartSql.orIf(b,conditionSql);
        return this;
    }

    public WherePartSql andExists(PartSql conditionSql) {
        conditionPartSql.andExists(conditionSql);
        return this;
    }
    public WherePartSql andExistsIf(boolean b,PartSql conditionSql) {
        conditionPartSql.andExistsIf(b,conditionSql);
        return this;
    }

    public WherePartSql andNotExists(PartSql conditionSql) {
        conditionPartSql.andNotExists(conditionSql);
        return this;
    }
    public WherePartSql andNotExistsIf(boolean b,PartSql conditionSql) {
        conditionPartSql.andNotExistsIf(b,conditionSql);
        return this;
    }

    public WherePartSql orExists(PartSql conditionSql) {
        conditionPartSql.orExists(conditionSql);
        return this;
    }
    public WherePartSql orExistsIf(boolean b,PartSql conditionSql) {
        conditionPartSql.orExistsIf(b,conditionSql);
        return this;
    }
    public WherePartSql orNotExists(PartSql conditionSql) {
        conditionPartSql.orNotExists(conditionSql);
        return this;
    }
    public WherePartSql orNotExistsIf(boolean b,PartSql conditionSql) {
        conditionPartSql.orNotExistsIf(b,conditionSql);
        return this;
    }

    public WherePartSql betweenAnd(Object min, Object max) {
        conditionPartSql.betweenAnd(min,max);
        return this;
    }

    public WherePartSql notBetweenAnd(Object min, Object max) {
        conditionPartSql.notBetweenAnd(min,max);
        return this;
    }

    public WherePartSql in(Object... values) {
        conditionPartSql.in(values);
        return this;
    }

    public WherePartSql in(String values) {
        conditionPartSql.in(values);
        return this;
    }

    public WherePartSql in(PartSql<SelectSql> subQuerySql) {
        conditionPartSql.in(subQuerySql);
        return this;
    }

    public WherePartSql notIn(Object... values) {
        conditionPartSql.notIn(values);
        return this;
    }

    public WherePartSql notIn(String values) {
        conditionPartSql.notIn(values);
        return this;
    }

    public WherePartSql notIn(PartSql<SelectSql> subQuerySql) {
        conditionPartSql.notIn(subQuerySql);
        return this;
    }

    public WherePartSql like(String value) {
        conditionPartSql.like(value);
        return this;
    }

    public WherePartSql like(String value, String escape) {
        conditionPartSql.like(value,escape);
        return this;
    }

    public WherePartSql notLike(String value) {
        conditionPartSql.notLike(value);
        return this;
    }

    public WherePartSql notLike(String value, String escape) {
        conditionPartSql.notLike(value,escape);
        return this;
    }


    public WherePartSql regexp(String value) {
        conditionPartSql.regexp(value);
        return this;
    }

    public WherePartSql notRegexp(String value) {
        conditionPartSql.notRegexp(value);
        return this;
    }

    public GroupByPartSql groupBy(String column, String... othersColumns) {
        return new GroupByPartSql(buildSql, column, othersColumns);
    }


    @Override
    public String getPartSql() {
        StringBuilder sql = new StringBuilder();
        String conditionSql= Sqls.trim2null(conditionPartSql.toString());
        if (conditionSql!=null) {
            sql.append("WHERE ");
            sql.append(conditionSql);
        }
        return sql.toString();
    }

    @Override
    public String toString() {
        return buildSql.toString();
    }
}
