package com.skss.iframe.util;


import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class Log4jUtil<T> extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected  Logger logger = Logger.getLogger(getEntity());
	
	@SuppressWarnings("unchecked")
	private Class<?> getEntity(){
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
