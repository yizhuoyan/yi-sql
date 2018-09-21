package com.yizhuoyan.common.sql.where;

import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.SelectSql;
import com.yizhuoyan.common.sql.PartSql;

import java.util.LinkedList;

/**
 * Created by Administrator on 2018/9/7 0007.
 */
public  class WhereConditionsPartSql {

    protected LinkedList<WhereCondition> conditions = new LinkedList<>();


    public LinkedList<WhereCondition> getConditions(){
        return conditions;
    }
    private WhereConditionsPartSql addAndCondition(Object condition){
        conditions.add(WhereCondition.of(true,"AND",condition));
        return this;
    }
    private WhereConditionsPartSql addAndCondition(boolean include,Object condition){
        conditions.add(WhereCondition.of(include,"AND",condition));
        return this;
    }
    private WhereConditionsPartSql addOrCondition(Object condition){
        conditions.add(WhereCondition.of(true,"OR",condition));
        return this;
    }
    private WhereConditionsPartSql addOrCondition(boolean include,Object condition){
        conditions.add(WhereCondition.of(include,"OR",condition));
        return this;
    }

    public WhereConditionsPartSql and(String conditionSql) {
        return addAndCondition(conditionSql);
    }
    public WhereConditionsPartSql andIf(boolean b, String conditionSql) {
        return addAndCondition(b,conditionSql);
    }

    public WhereConditionsPartSql or(String conditionSql) {
        return addOrCondition(conditionSql);
    }
    public WhereConditionsPartSql orIf(boolean b, String conditionSql) {
        return addOrCondition(b,conditionSql);
    }

    public WhereConditionsPartSql andIf(boolean b, WhereConditionsPartSql conditionSql) {
        return addAndCondition(b,conditionSql);
    }
    public WhereConditionsPartSql and(WhereConditionsPartSql conditionSql) {

        return addAndCondition(conditionSql);
    }

   
    public WhereConditionsPartSql or(WhereConditionsPartSql conditionSql) {

        return addOrCondition(conditionSql);
    }
    public WhereConditionsPartSql orIf(boolean b, WhereConditionsPartSql conditionSql) {
        return addOrCondition(b,conditionSql);
    }

   
    public WhereConditionsPartSql andExists(PartSql conditionSql) {
        return andExistsIf(true,conditionSql);
    }
    public WhereConditionsPartSql andExistsIf(boolean b, PartSql conditionSql) {
        if(conditionSql==null)return this;
        String sql=Sqls.trim2null(conditionSql.toString());
        if(sql!=null) {
            addAndCondition(b,"EXISTS (" + sql + ")");
        }
        return this;
    }
   
    public WhereConditionsPartSql andNotExists(PartSql conditionSql) {
        return andNotExistsIf(true,conditionSql);
    }
    public WhereConditionsPartSql andNotExistsIf(boolean b, PartSql conditionSql) {
        if(conditionSql==null)return this;
        String sql=Sqls.trim2null(conditionSql.toString());
        if(sql!=null) {
            addAndCondition(b,"NOT EXISTS (" + sql + ")");
        }
        return this;
    }
   
    public WhereConditionsPartSql orExists(PartSql conditionSql) {

        return orExistsIf(true,conditionSql);
    }
    public WhereConditionsPartSql orExistsIf(boolean b,PartSql conditionSql) {
        if(conditionSql==null)return this;
        String sql=Sqls.trim2null(conditionSql.toString());
        if(sql!=null) {
            addOrCondition(b,"EXISTS (" + sql + ")");
        }
        return this;
    }

    public WhereConditionsPartSql orNotExists(PartSql conditionSql) {
        return orNotExistsIf(true,conditionSql);
    }
    public WhereConditionsPartSql orNotExistsIf(boolean b,PartSql conditionSql) {
        if(conditionSql==null)return this;
        String sql=Sqls.trim2null(conditionSql.toString());
        if(sql!=null) {
            addOrCondition(b,"NOT EXISTS (" + sql + ")");
        }
        return this;
    }

   
    public WhereConditionsPartSql betweenAnd(Object min, Object max) {
        WhereCondition last=conditions.pollLast();
        if(min==null||max==null){
            return this;
        }
        String minUse=Sqls.trim2null(min.toString());
        String maxUse=Sqls.trim2null(max.toString());
        if(minUse==null||maxUse==null){
            return this;
        }
        last.condition=new StringBuilder().append(last.condition).append(" BETWEEN ").append(min).append(" AND ").append(max).toString();
        conditions.add(last);
        return this;
    }
   
    public WhereConditionsPartSql notBetweenAnd(Object min, Object max) {
        WhereCondition last=conditions.pollLast();
        if(min==null||max==null){
            return this;
        }
        String minUse=Sqls.trim2null(min.toString());
        String maxUse=Sqls.trim2null(max.toString());
        if(minUse==null||maxUse==null){
            return this;
        }
        last.condition=new StringBuilder().append(last.condition).append(" NOT BETWEEN ").append(min).append(" AND ").append(max).toString();
        conditions.add(last);
        return this;
    }

   
    public WhereConditionsPartSql in(Object... values) {
        return in(Sqls.join(values,","));
    }


   
    public WhereConditionsPartSql in(String values) {
        WhereCondition last=conditions.pollLast();
        values=Sqls.trim2null(values);
        if(values==null){
            return this;
        }
        last.condition=new StringBuilder().append(last.condition)
                .append(" IN(").append(values).append(")").toString();
        conditions.add(last);
        return this;
    }

   
    public WhereConditionsPartSql in(PartSql<SelectSql> subQuerySql) {
        return in(subQuerySql.toString());
    }
   
    public WhereConditionsPartSql notIn(Object... values) {
        return notIn(Sqls.join(values,","));
    }
   
    public WhereConditionsPartSql notIn(String values) {
        WhereCondition last=conditions.pollLast();
        values=Sqls.trim2null(values);
        if(values==null){
            return this;
        }
        last.condition=new StringBuilder().append(last.condition)
                .append(" NOT IN(").append(values).append(")").toString();
        conditions.add(last);
        return this;
    }
   
    public WhereConditionsPartSql notIn(PartSql<SelectSql> subQuerySql) {
        return notIn(subQuerySql.toString());
    }



   
    public WhereConditionsPartSql like(String value) {
        return like(value,null);
    }

   
    public WhereConditionsPartSql like(String value, String escape) {
        WhereCondition last=conditions.pollLast();
        value= Sqls.trim2null(value);
        if(value==null){
            return this;
        }
        if(!"?".equals(value)){
           value=Sqls.quoteAndEscape(value);
        }


        StringBuilder newLast=new StringBuilder().append(last.condition)
                .append(" LIKE ").append(value);
        escape=Sqls.trim2null(escape);
        if(escape!=null){
            escape=Sqls.quoteAndEscape(escape);
            newLast.append(" ESCAPE ").append(escape);
        }
        last.condition=newLast;
        conditions.add(last);
        return this;
    }
   
    public WhereConditionsPartSql notLike(String value) {
        return notLike(value,null);
    }
   
    public WhereConditionsPartSql notLike(String value, String escape) {
        WhereCondition last=conditions.pollLast();
        value= Sqls.trim2null(value);
        if(value==null){
            return this;
        }
        if(!"?".equals(value)){
            value=Sqls.quoteAndEscape(value);
        }
        StringBuilder newLast=new StringBuilder().append(last.condition)
                .append(" NOT LIKE ").append(value);
        escape=Sqls.trim2null(escape);
        if(escape!=null){
            escape=Sqls.quoteAndEscape(escape);
            newLast.append(" ESCAPE ").append(escape);
        }
        last.condition=newLast;
        conditions.add(last);
        return this;
    }

   
    public WhereConditionsPartSql regexp(String value) {
        WhereCondition last=conditions.pollLast();
        value= Sqls.trim2null(value);
        if(value==null)return this;

        if(!"?".equals(value)){
            value=Sqls.quoteAndEscape(value);
        }
        last.condition=new StringBuilder().append(last.condition)
                .append(" REGEXP ").append(value);
        conditions.add(last);
        return this;
    }
   
    public WhereConditionsPartSql notRegexp(String value) {
        WhereCondition last=conditions.pollLast();
        value= Sqls.trim2null(value);
        if(value==null){
            return this;
        }
        if(!"?".equals(value)){
            value=Sqls.quoteAndEscape(value);
        }
        StringBuilder newLast=new StringBuilder().append(last.condition)
                .append(" NOT REGEXP ").append(value);
        last.condition=newLast;
        conditions.add(last);
        return this;
    }


    public String toString() {
        StringBuilder sql = new StringBuilder();
        if (conditions!=null) {
            boolean first=true;
            for (WhereCondition condition:conditions) {
                String conditionStr= Sqls.trim2null(condition.toString());
                if(conditionStr!=null){
                    if(first){
                        condition.andOr=null;
                        conditionStr= Sqls.trim2null(condition.toString());
                        if(conditionStr==null){
                            continue;
                        }
                        first=false;
                    }else {
                        sql.append(' ');
                    }
                    sql.append(conditionStr);
                }
            }
        }
        return sql.toString();
    }




}
