package com.yizhuoyan.common.sql.select.sqlpart;

import com.yizhuoyan.common.sql.PartSql;
import com.yizhuoyan.common.sql.Sqls;
import com.yizhuoyan.common.sql.select.SelectSql;

import java.util.LinkedList;


public  class UnionPartSql implements PartSql<SelectSql> {
	protected  final SelectSql buildSql;

	private static class UnionSql{
		String unionType;
		 SelectSql unionSelectSql;
	}

	private LinkedList<UnionSql> unionSqls=new LinkedList<>();

	public UnionPartSql(SelectSql buildSql) {
		this.buildSql=buildSql;
		buildSql.setUnionPartSql(this);
	}


	public UnionPartSql union(PartSql<SelectSql> unionSelectSql){
		UnionSql unionSql=new UnionSql();
		unionSql.unionType="UNION";
		unionSql.unionSelectSql=unionSelectSql.getBuildSql();
		unionSqls.add(unionSql);
		return this;
	}


	public UnionPartSql unionAll(PartSql<SelectSql> unionSelectSql){
		UnionSql unionSql=new UnionSql();
		unionSql.unionSelectSql=unionSelectSql.getBuildSql();
		unionSql.unionType="UNION ALL";
		unionSqls.add(unionSql);
		return this;
	}

	@Override
	public String getPartSql() {
		StringBuilder sql=new StringBuilder();
		if(!unionSqls.isEmpty()){
			for(UnionSql unionSql:unionSqls) {
				if(unionSql.unionSelectSql!=null){
					String unionSqlString = Sqls.trim2null(unionSql.unionSelectSql.getPartSql());
					if (unionSqlString != null) {
						if(sql.length()>0){
							sql.append('\n');
						}
						sql.append(unionSql.unionType);
						sql.append('\n').append(unionSqlString);
					}
				}

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
