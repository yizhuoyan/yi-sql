package com.yizhuoyan.common.sql;

/**
 * Sql片段
 * Created by Administrator on 2018/9/7 0007.
 */
public interface PartSql<T> {
    /**
     * 获取sql片段
     * @return
     */
    String getPartSql();

    /**
     * 获取sql片段所属Sql
     * @return
     */
    T getBuildSql();
}
