package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.SelectSql;

import java.util.LinkedList;
import java.util.List;

public class GroupByPartSql extends  OrderByAndLimit  {

	private List<String> columns=new LinkedList<>();

	public GroupByPartSql(SelectSql buildSql,String column, String... others)
	{
		super(buildSql);
		if(column!=null){
			columns.add(column);
		}
		for (String col:others ) {
				columns.add(col);
		}
	}

	public HavingPartSql having(String condition){
		HavingPartSql havingPartSql=new HavingPartSql(buildSql,condition);
		return havingPartSql;
	}

	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();
		if(columns.size()>0){
			sql.append("GROUP BY");
			boolean first=true;
			for(String col:columns){
				if(first){
					sql.append(' ');
					first=false;
				}else{
					sql.append(',');
				}
				sql.append(col);
			}
		}
		return sql.toString();
	}

	@Override
	public String toString() {
		return buildSql.toString();
	}
}
