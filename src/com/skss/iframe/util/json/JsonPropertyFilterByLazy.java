package com.skss.iframe.util.json;

import net.sf.json.util.PropertyFilter;

/**
 * JSON序列化时过滤hibernate延迟加载属性
 * @author： guozhigang
 * @time：2014年11月28日,上午10:20:35
 */
public class JsonPropertyFilterByLazy implements PropertyFilter{

	@Override
	public boolean apply(Object arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
