package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.select.SelectSql;


public final class ForUpdatePartSql extends AbstractEndPartSql{

	public ForUpdatePartSql(SelectSql buildSql) {
		super(buildSql);
		buildSql.setForUpdatePartSql(this);
	}

	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();
		sql.append("FOR UPDATE");
		return sql.toString();
	}

	@Override
	public String toString() {
		return buildSql.toString();
	}

}
