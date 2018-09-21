package com.yizhuoyan.common.sql;

/**
 * Created by Administrator on 2018/9/6 0006.
 */
public class SqlRuntimeException extends  RuntimeException {
    public SqlRuntimeException(String message) {
        super(message);
    }

    public SqlRuntimeException(String message,Throwable cause) {
        super(message,cause);
    }
}
