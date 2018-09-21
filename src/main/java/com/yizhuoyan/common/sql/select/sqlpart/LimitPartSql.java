package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.select.SelectSql;
import lombok.Data;


public final class LimitPartSql extends AbstractEndPartSql{
	private int begin;
	private int offset;

	public LimitPartSql(SelectSql buildSql, int begin, int offset) {
		super(buildSql);
		buildSql.setLimitPartSql(this);
		this.begin = begin;
		this.offset = offset;
	}

	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();
		sql.append("LIMIT ").append(begin).append(',').append(offset);
		return sql.toString();
	}

	@Override
	public String toString() {
		return buildSql.toString();
	}

}
