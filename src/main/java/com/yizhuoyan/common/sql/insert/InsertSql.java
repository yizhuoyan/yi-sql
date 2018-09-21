package com.yizhuoyan.common.sql.insert;

import com.yizhuoyan.common.sql.Sqls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InsertSql {
	private final String tableName;
	private final List<String> columns;
	private final List<Object> values;

	public InsertSql(String tableName, List<String> columns) {
		this.tableName = tableName;
		this.columns = columns;
		this.values=new ArrayList(columns.size());
		Collections.fill(this.values,"?");
	}

	public InsertSql values(Object... valueArray){
		for (int i = 0; i <valueArray.length ; i++) {
			this.values.add(i,Sqls.object2sqlValue(valueArray[i]));
		}
		return this;
	}



	public static InsertSql of(String table, String... columns) {
		return of(table,Arrays.asList(columns));
	}

	public static InsertSql of(String table, List<String> columns) {
		return new InsertSql(table,columns);
	}
	
	public static InsertSql of(String table, String columns) {
		return of(table, Arrays.asList(columns.split(",")));
	}

	@Override
	public String toString() {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ").append(tableName).append('(');

		for (String column:columns) {
			sql.append(column).append(',');
		}
		sql.setCharAt(sql.length() - 1, ')');
		sql.append("values(");
		for (Object value:values) {
			sql.append(value).append(',');
		}
		sql.setCharAt(sql.length() - 1, ')');
		return sql.toString();
	}
}
