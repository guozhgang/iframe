package com.skss.iframe.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.skss.app.entity.Menu;
import com.skss.app.entity.Role;
import com.skss.iframe.entity.TreeNode;
import com.skss.iframe.util.Constant;
import com.skss.iframe.util.json.ObjectJsonValueProcessor;
@SuppressWarnings({ "rawtypes","unchecked" })
/**
 * action父类，所有action必须继承此类
 * @author guozhgang
 *
 * @param <M>
 */
public class ActionUtil<M> extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware,ModelDriven<M>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;	
	protected Map session;
	private Map<Object, Object> map = new HashMap<Object, Object>();
	protected int rows;
	protected int page;
	protected int start;  //从第几条开始
	protected M model;
	protected Map<String, Object> attributes = new HashMap<String, Object>();  //保存树节点中除id,text,state之外的所有属性
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	public void setSession(Map session) {
		// TODO Auto-generated method stub
		this.session = session;
		
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	public HttpServletRequest getRequest() {
		return this.request;
	}
	public HttpServletResponse getResponse() {
		return this.response;
	}
	public Map getSession() {
		return this.session;
	}
	
	protected void print(Object obj) {
		response.setContentType("text/html;charset=UTF-8"); //解决中文乱码
		PrintWriter out = null;
		try {
			out =  response.getWriter();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(obj);
		out.flush();
		out.close();
	}
	protected void sendSuccess() {
		map.put("flag", true);
		map.put("message", Constant.SUCCESS_MESSAGE);
		JSONObject json = JSONObject.fromObject(map);
		this.print(json);
	}
	protected void sendFailure() {
		map.put("flag", false);
		map.put("message", Constant.ERROR_MESSAGE);
		JSONObject json = JSONObject.fromObject(map);
		this.print(json);
	}
	protected void sendMessage(boolean flag, String message) {
		map.put("flag", flag);
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map);
		this.print(json);
	}
	public void sendJSON(List list, int total) {
		map.put(Constant.ROWS, list);
		map.put(Constant.TOTAL, total);
		/*JsonConfig jsonConfg = new JsonConfig();
		jsonConfg.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[]{"menuList"}, Role.class));
		JSONArray json = JSONArray.fromObject(map);
		String dataJSON = json.toString();
		dataJSON = dataJSON.substring(1,dataJSON.length()-1);   //符合easyui的json格式
		
		jsonConfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray json = JSONArray.fromObject(list, jsonConfg);*/
		JsonConfig jsonConfg = new JsonConfig();
		jsonConfg.registerJsonValueProcessor(Role.class, new ObjectJsonValueProcessor(new String[]{"menuList"}, Role.class));
		JSONObject json = JSONObject.fromObject(map, jsonConfg);
		this.print(json.toString());
	}
	/**
	 * send tree
	 * @param list
	 */
	public void sendTree(List<TreeNode> list) {
		JSONArray json = JSONArray.fromObject(list);
		this.print(json);
	}

	public void printJSONForTreeOrCombo(List list){
		JSONArray json = JSONArray.fromObject(list);
		this.print(json);
	}
	public Map<Object, Object> getMap() {
		return map;
	}
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		start = (this.page-1)*rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public M getModel() {
		if (model ==null) {
			try {
				model = (M) getEntity().newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return model;
		
	}
	
	private Class<?> getEntity(){
		return (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public void setModel(M model) {
		this.model = model;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	
}
