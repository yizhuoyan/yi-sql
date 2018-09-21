package com.yizhuoyan.common.sql.select.sqlpart;


import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.SelectSql;

import java.util.LinkedList;
import java.util.List;

public class SelectPartSql implements PartSql<SelectSql>{
	private List<Object> columnList=new LinkedList<>();
	private boolean distinct=false;
	private final SelectSql buildSql;

	public SelectPartSql(SelectSql buildSql) {
		buildSql.setSelectPartSql(this);
		this.buildSql = buildSql;
	}

	public SelectPartSql selectDistinct(String... columns) {
		this.distinct=true;
		return select(columns);
	}

	public SelectPartSql select(String... columns) {
		for(String column:columns){
			column= Sqls.trim2null(column);
			if(column!=null) {
				columnList.add(column);
			}
		}
		return this;
	}

	public SelectPartSql select(SelectSql subSelect, String alias) {
		if(subSelect==null){
			return this;
		}

		String subSelectSql= Sqls.trim2null(subSelect.toString());
		if(subSelectSql==null){
			return this;
		}
		columnList.add(new StringBuilder().append("(").append(subSelectSql).append(") ").append(alias).toString());
		return this;
	}


	public FromPartSql from(String table) {

		return new FromPartSql(buildSql,table);
	}

	public FromPartSql from(String table,String alias) {

		return new FromPartSql(buildSql,table,alias);
	}

	public FromPartSql from(PartSql subSelect,String alias)
	{
		return new FromPartSql(buildSql,subSelect,alias);
	}


	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();
		if(columnList.size()>0){
			sql.append("SELECT ");
			if(distinct){
				sql.append("DISTINCT ");
			}
			boolean notFirst=false;
			for (Object column:columnList) {
				if(notFirst){
					sql.append(",");
				}else{
					notFirst=true;
				}
				sql.append(column);
			}
		}
		return sql.toString();
	}

	@Override
	public SelectSql getBuildSql() {
		return buildSql;
	}

	@Override
	public String toString() {
		return buildSql.toString();
	}
}
