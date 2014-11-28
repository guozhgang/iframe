package com.skss.iframe.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.skss.iframe.entity.Template;
import com.skss.iframe.template.util.FreemarkerUtil;


public class TemplateAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String templateName = "dao";
	public void deployment() throws Exception{
		Class.forName("com.mysql.jdbc.Driver") ;
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/app?useUnicode=true&characterEncoding=utf-8" , "root" , "root") ;
		PreparedStatement pre = conn.prepareStatement("show FIELDS from ss_menu");
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
			Template template = new Template(rs.getString(1), methodName, type);
			columnlist.add(template);
		}
		map.put("columns", columnlist);
		pre.close();
		conn.close();
		
		map.put("table", "test");
		String cl = "test";
		cl =  cl.replaceFirst(cl.substring(0, 1),cl.substring(0, 1).toUpperCase());
		map.put("className",cl);
		map.put("base", "BaseDao");
		String templatePath = ServletActionContext.getServletContext().getRealPath("/template");
		String filePath = "F:\\workspace\\iframe\\app\\com\\skss\\app\\test\\"+cl+".java";
		templateName = "/" + templateName + ".ftl";
		try {
			FreemarkerUtil.analysisTemplate(templatePath, templateName, filePath, map);
			System.out.println("文件生成成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
