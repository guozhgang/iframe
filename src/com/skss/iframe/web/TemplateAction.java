package com.skss.iframe.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.struts2.ServletActionContext;

import com.skss.iframe.entity.Template;
import com.skss.iframe.template.util.FreemarkerUtil;


public class TemplateAction extends ActionUtil<Template, TemplateAction>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void deployment() throws Exception{
		String names[] = request.getParameter("name").split(",");
		for (int k = 0; k < names.length; k++) {
			String name = names[k];
			String paths[] = request.getParameterValues("path");
			Map<String, Object> map = columns(name);
			map.put("table", name);
			name = TemplateAction.format(name);
			name =  name.replaceFirst(name.substring(0, 1),name.substring(0, 1).toUpperCase());
			map.put("base", "BaseDao");
			String templatePath = ServletActionContext.getServletContext().getRealPath("/template");
			try {
				for (int i = 0; i < paths.length; i++) {
					String className = "";
					String templateName = "";
					String packageName = paths[i].substring(paths[i].indexOf("com"), paths[i].lastIndexOf("\\") - 1).replaceAll("\\\\\\\\", ".");
					if (paths[i].contains("entity")) {
						className = name;
						templateName = "entity";
						map.put("entityPackage", packageName);
						map.put("entityName", className);
						map.put("modelName", className.replaceFirst(className.substring(0, 1),className.substring(0, 1).toLowerCase()));
					} else if (paths[i].contains("dao")) {
						className = name + "Dao";
						templateName = "dao";
						map.put("daoPackage", packageName);
						map.put("daoName", className);
						map.put("daoModelName", className.replaceFirst(className.substring(0, 1),className.substring(0, 1).toLowerCase()));
					} else if (paths[i].contains("service")) {
						className = name + "Service";
						templateName = "service";
						map.put("servicePackage", packageName);
						map.put("serviceName", className);
						map.put("serviceModelName", className.replaceFirst(className.substring(0, 1),className.substring(0, 1).toLowerCase()));
					} else if (paths[i].contains("action")) {
						className = name + "Action";
						templateName = "action";
						map.put("actionPackage", packageName);
						map.put("actionName", className);
						map.put("actionModelName", className.replaceFirst(className.substring(0, 1),className.substring(0, 1).toLowerCase()));
					}
					map.put("className",className);
					String filePath = paths[i] + className + ".java";
					templateName = "/" + templateName + ".ftl";
					FreemarkerUtil.analysisTemplate(templatePath, templateName, filePath, map);
				}
				this.sendMessage(true, "文件发布成功");
				logger.info("文件发布成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("文件发布失败:" + e.getMessage());
			}
		
		}
	}
	/**
	 * 获取列名
	 * @param name
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	private Map<String, Object> columns(String name) throws Exception,
			SQLException {
		Connection conn = TemplateAction.getConnection();
		PreparedStatement pre = conn.prepareStatement("show FIELDS from "+name+"");
		ResultSet rs = pre.executeQuery();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Template> columnlist = new ArrayList<Template>();
		String type = "";
		String methodName = "";
		while(rs.next()) {
			if (rs.getString(2).contains("(")) {
				type = rs.getString(2).substring(0, rs.getString(2).indexOf("("));
			} else {
				type = rs.getString(2);
			}
			if ("varchar".equals(type)) {  
				type = "String";
			} else if ("int".equals(type)) {
				type = "int";
			}
			methodName =rs.getString(1).replaceFirst(rs.getString(1).substring(0, 1),rs.getString(1).substring(0, 1).toUpperCase())  ; 
			Template template = new Template(TemplateAction.format(rs.getString(1)), TemplateAction.format(methodName), type);
			columnlist.add(template);
		}
		map.put("columns", columnlist);
		pre.close();
		conn.close();
		return map;
	}
	/**
	 * 获取所有数据库表
	 * @throws Exception
	 */
	public void tables() throws Exception{
		Connection conn = TemplateAction.getConnection();
		PreparedStatement pre = conn.prepareStatement("show TABLES");
		ResultSet rs = pre.executeQuery();
		List<Object> tables = new ArrayList<Object>();
		String name = "";
		while (rs.next()) {
			Map<String, String> map = new HashMap<String, String>();
			name = rs.getString(1);
			map.put("name", name);
			tables.add(map);
		}
		pre.close();
		conn.close();
		this.sendJSON(tables, 20);
	}
	/**
	 * 连接数据库
	 * @return
	 * @throws Exception
	 */
	private static Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver") ;
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/app?useUnicode=true&characterEncoding=utf-8" , "root" , "root") ;
		return conn;
	}
	/**
	 * 格式化命名
	 * @param str
	 * @return
	 */
	private static String format(String str) {
		int fromIndex = str.indexOf("_");
		if (fromIndex != -1) {
			String firstStr = str.substring(0, fromIndex);
			str = str.substring(fromIndex + 1);
			return firstStr + format(str.replaceFirst(str.substring(0,1), str.substring(0,1).toUpperCase()));
		}
		return str;
	}
	public static void main(String[] args) {
		String str = "F:\\workspace\\iframe\\test\\com\\skss\\test\\entity\\";
		System.out.println(str.substring(str.indexOf("com"), str.lastIndexOf("\\")).replaceAll("\\\\", "."));
	}
}
