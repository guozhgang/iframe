package com.skss.iframe.util;

import java.util.HashMap;
import java.util.Map;

import com.skss.iframe.constant.cache.DBConstant;

public class Conver {
	
	public static  Map<String, Object> dbTypeConverJava() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(DBConstant.VARCHAR, "String");
		return map;
	}
}
