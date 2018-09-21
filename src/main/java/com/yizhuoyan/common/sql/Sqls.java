package com.yizhuoyan.common.sql;

import java.time.LocalDate;
import java.util.List;

public abstract class Sqls {

	public static String trim2null(String s) {
		if (s == null)
			return null;
		if ((s = s.trim()).length() == 0)
			return null;
		return s;
	}

	public static String escape(String value){
		if(value!=null){
			return value.replace("'","\\'");
		}
		return value;
	}
	public static String quoteAndEscape(String value){
		if(value!=null){
			return "'"+value.replace("'","\\'")+"'";
		}
		return value;
	}


	public static String join(Object[] arr, String join) {
		if (arr == null || arr.length == 0)
			return "";
		StringBuilder sb = new StringBuilder(arr[0].toString());
		for (int i = 1; i < arr.length; i++) {
			sb.append(join).append(arr[i]);
		}
		return sb.toString();
	}

	public static <T> String join(List<T> list, String join) {
		if (list == null || list.size() == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			sb.append(join).append(list.get(i));
		}
		return sb.toString();
	}

	public static String object2sqlValue(Object value){
		if(value==null){
			return null;
		}
		if(value instanceof  String){
			return quoteAndEscape(value.toString());
		}
		if(value instanceof Number){
			return value.toString();
		}
		if(value instanceof Boolean){
			return ((Boolean)value)?"1":"0";
		}

		return value.toString();


	}
}
