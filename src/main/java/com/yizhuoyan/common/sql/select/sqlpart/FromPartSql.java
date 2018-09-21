package com.yizhuoyan.common.sql.select.sqlpart;



import com.yizhuoyan.common.sql.PartSql;
import com.yizhuoyan.common.sql.SqlRuntimeException;
import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.SelectSql;

import java.util.LinkedList;

public class FromPartSql extends  OrderByAndLimit  {
	LinkedList<JoinTable> tables=new LinkedList<>();
	private static class JoinTable{
		String alias;
		String tableName;
		String joinType;
		String onConditions;
	}
	public FromPartSql(SelectSql buildSql,String tableName,String alias) {
		super(buildSql);
		tableName=assertLegalTableName(tableName);
		buildSql.setFromPartSql(this);
		JoinTable joinTable = new JoinTable();
		joinTable.tableName=tableName;
		joinTable.alias=alias;
		tables.add(joinTable);
	}

	public FromPartSql(SelectSql buildSql,String tableName) {
		this(buildSql,tableName,null);
	}
	public FromPartSql(SelectSql buildSql,PartSql subSelect,String alias) {
		this(buildSql,new StringBuilder().append("(")
				.append(subSelect).append(")").toString(),alias);
	}


	public FromPartSql innerJoin(String table) {
		return innerJoin(table,null);
	}
	public FromPartSql innerJoin(PartSql table,String alias) {

		return innerJoin(new StringBuilder().append("(")
				.append(table).append(")").toString(),alias);
	}

	public FromPartSql innerJoin(String tableName, String alias) {
		tableName=assertLegalTableName(tableName);

		JoinTable joinTable = new JoinTable();
		joinTable.tableName=tableName;
		joinTable.alias=alias;
		joinTable.joinType="INNER JOIN";

		tables.add(joinTable);
		return this;
	}

	public FromPartSql leftJoin(String tableName, String alias) {
		tableName=assertLegalTableName(tableName);
		JoinTable joinTable = new JoinTable();
		joinTable.tableName=tableName;
		joinTable.alias=alias;
		joinTable.joinType="LEFT JOIN";

		tables.add(joinTable);
		return this;
	}
	public FromPartSql leftJoin(String table) {
		return leftJoin(table,null);
	}

	public FromPartSql leftJoin(PartSql table,String alias) {
		return leftJoin(new StringBuilder().append("(")
				.append(table).append(")").toString(),alias);
	}

	public FromPartSql rightJoin(String tableName, String alias) {
		tableName=assertLegalTableName(tableName);
		JoinTable joinTable = new JoinTable();
		joinTable.tableName=tableName;
		joinTable.alias=alias;
		joinTable.joinType="RIGHT JOIN";

		tables.add(joinTable);
		return this;
	}

	public FromPartSql rightJoin(String table) {

		return rightJoin(table,null);
	}

	public FromPartSql rightJoin(PartSql table,String alias) {
		return rightJoin(new StringBuilder().append("(")
				.append(table).append(")").toString(),alias);
	}








	public FromPartSql fullJoin(String tableName, String alias) {
		tableName=assertLegalTableName(tableName);
		JoinTable joinTable = new JoinTable();
		joinTable.tableName=tableName;
		joinTable.alias=alias;
		joinTable.joinType="FULL JOIN";

		tables.add(joinTable);
		return this;
	}
	public FromPartSql fullJoin(String table) {

		return fullJoin(table,null);
	}
	public FromPartSql fullJoin(PartSql table, String alias) {
		return fullJoin(new StringBuilder().append("(")
				.append(table).append(")").toString(),alias);
	}
	public FromPartSql on(String condition) {
		condition=Sqls.trim2null(condition);
		if(condition!=null) {
			tables.getLast().onConditions = condition;
		}
		return this;
	}

	private String assertLegalTableName(String tableName){
		tableName= Sqls.trim2null(tableName);
		if(tableName==null){
			throw  new SqlRuntimeException("legal table name");
		}
		return tableName;
	}




	public WherePartSql where(String condition){
		WherePartSql wherePartSql=new WherePartSql(buildSql);
		wherePartSql.and(condition);
		return  wherePartSql;
	}
	public WherePartSql whereExists(PartSql sql){
		WherePartSql wherePartSql=new WherePartSql(buildSql);
		wherePartSql.andExists(sql);
		return  wherePartSql;
	}
	public WherePartSql whereNotExists(PartSql sql){
		WherePartSql wherePartSql=new WherePartSql(buildSql);
		wherePartSql.andNotExists(sql);
		return  wherePartSql;
	}

	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();

		if(tables.size()>0){
			sql.append("FROM");
			boolean first=true;
			for (JoinTable table:tables ) {
				if(first){
					sql.append(' ').append(table.tableName);
					if(table.alias!=null){
						sql.append(' ').append(table.alias);
					}
					first=false;
				}else{
					if(table.joinType!=null) {
						sql.append(' ').append(table.joinType);
					}

					sql.append(' ').append(table.tableName);
					if(table.alias!=null){
						sql.append(' ').append(table.alias);
					}
					if(table.onConditions!=null){
						sql.append(" ON ").append(table.onConditions);
					}
				}
			}
		}
		return sql.toString();
	}

	@Override
	public String toString() {
		return buildSql.toString();
	}
}
