package com.yizhuoyan.common.sql.where;

import com.yizhuoyan.common.sql.Sqls;

/**
 * Created by Administrator on 2018/9/19 0019.
 */
public class WhereCondition {
    //是否包含
    public final boolean include;
    //and or
    public String andOr;

    public Object condition;

    public WhereCondition(boolean include, String andOr, Object condition) {
        this.include = include;
        this.andOr = andOr;
        this.condition = condition;
    }

    public static WhereCondition of(boolean include, String andOr, Object condition){
        return new WhereCondition(include,andOr,condition);
    }

    @Override
    public String toString() {
        StringBuilder sql=new StringBuilder();
        if(include&&condition!=null){
            String conditionStr= Sqls.trim2null(this.condition.toString());
            if(conditionStr!=null){
                if(!(this.condition instanceof  CharSequence)){
                    conditionStr="("+conditionStr+")";
                }
                String andOrStr=Sqls.trim2null(this.andOr);
                if(andOrStr!=null) {
                    sql.append(andOr).append(' ');
                }
                sql.append(conditionStr);
            }
        }
        return sql.toString();
    }
}
