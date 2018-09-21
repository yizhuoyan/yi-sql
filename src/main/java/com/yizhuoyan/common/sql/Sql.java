package com.yizhuoyan.common.sql;



import com.yizhuoyan.common.sql.select.SelectSql;
import com.yizhuoyan.common.sql.select.sqlpart.*;
import com.yizhuoyan.common.sql.where.WhereConditionsPartSql;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Sql {
	
	public static SelectPartSql select(String... columns) {
		return new SelectPartSql(new SelectSql()).select(columns);
	}
	public static SelectPartSql selectDistinct(String... columns) {
		return new SelectPartSql(new SelectSql()).selectDistinct(columns);
	}


	public static FromPartSql from(String table) {

		return new FromPartSql(new SelectSql(),table);
	}

	public static FromPartSql from(String table, String alias) {
		return new FromPartSql(new SelectSql(),table,alias);
	}

	public static FromPartSql from(PartSql subSelect, String alias) {
		return new FromPartSql(new SelectSql(),subSelect,alias);
	}


	public static WherePartSql where(String condition) {
		WherePartSql wherePartSql=new  WherePartSql(new SelectSql());
		return wherePartSql.and(condition);
	}

	public static WherePartSql where(WhereConditionsPartSql condition) {
		WherePartSql wherePartSql=new  WherePartSql(new SelectSql());
		return wherePartSql.and(condition);
	}

	public static WhereConditionsPartSql condition(String condition) {
		WhereConditionsPartSql wherePartSql=new WhereConditionsPartSql();
		return wherePartSql.and(condition);
	}

	public static String like(String[] columns,String value){
		return like(columns,value,false);
	}
	public static String notLike(String[] columns,String value){
		return like(columns,value,true);
	}
	public static String like(Collection<String> columns, String value){
		return like(new ArrayList<>(columns).toArray(new String[columns.size()]),value,false);
	}
	public static String notLike(Collection<String> columns,String value){
		return like(new ArrayList<>(columns).toArray(new String[columns.size()]),value,true);
	}
	public static String like(String columns,String value){
		return like(columns.split(","),value,false);
	}
	public static String notLike(String columns,String value){
		return like(columns.split(","),value,true);
	}

	private static String like(String[] columns,String value,boolean not){

		if(columns==null||columns.length==0){
			return null;
		}
		if(!"?".equals(value)){
			value="'"+ Sqls.escape(value)+"'";
		}
		StringBuilder sb = new StringBuilder();
		if(columns.length>1) {
			sb.append("(");
		}
		sb.append(columns[0]);
		if(not){
			sb.append(" NOT");
		}
		sb.append(" LIKE ").append(value);
		for (int i = 1; i < columns.length; i++) {
			sb.append(" OR ").append(columns[i]);
			if(not){
				sb.append(" NOT");
			}
			sb.append(" LIKE ").append(value);
		}
		if(columns.length>1) {
			sb.append(")");
		}


		return sb.toString();
	}





	
	



}
