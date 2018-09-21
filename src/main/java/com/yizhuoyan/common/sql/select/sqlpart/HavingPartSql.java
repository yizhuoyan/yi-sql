package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.SelectSql;

import java.util.LinkedList;

public class HavingPartSql extends OrderByAndLimit {
	final String condition;
	static class GroupCondition {
	}

	public HavingPartSql(SelectSql buildSql,String condition) {
		super(buildSql);
		buildSql.setHavingPartSql(this);
		this.condition= Sqls.trim2null(condition);
	}


	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();
		if(condition!=null){
			sql.append("HAVING ");
			sql.append(condition);
		}
		return sql.toString();
	}

	@Override
	public String toString() {
		return buildSql.toString();
	}


}
