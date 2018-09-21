package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.SelectSql;

import java.util.LinkedList;
import java.util.List;

public class OrderByPartSql extends  AbstractEndPartSql {
	List<String> orderBys=new LinkedList<>();

	public OrderByPartSql(SelectSql buildSql,String orderByField, String... others) {
		super(buildSql);
		buildSql.setOrderByPartSql(this);
		orderBys.add(orderByField);
		for(String field:others){
			orderBys.add(field);
		}
	}
	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();
		if(orderBys.size()>0){
			sql.append(" ORDER BY");
			boolean first=true;
			for(String column:orderBys){
				if(first){
					first=false;
					sql.append(' ');
				}else{
					sql.append(',');
				}
				sql.append(column);
			}
		}
		return sql.toString();
	}

	@Override
	public String toString() {
		return buildSql.toString();
	}



}
