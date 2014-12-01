package com.skss.iframe.web;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.skss.iframe.dao.TemplateData;
import com.skss.iframe.entity.Template;
import com.skss.iframe.template.util.FreemarkerUtil;
import com.skss.iframe.util.DBHelper;
import com.skss.iframe.util.string.StringUtil;

public class TemplateAction extends ActionUtil<Template, TemplateAction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void deployment() throws Exception {
		String names[] = request.getParameter("name").split(",");
		for (int k = 0; k < names.length; k++) {
			String name = names[k];
			String paths[] = request.getParameterValues("path");
			Map<String, Object> map = TemplateData.columns(name);
			map.put("table", name);
			name = StringUtil.format(name);
			name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
					.toUpperCase());
			map.put("base", "BaseDao");
			String templatePath = ServletActionContext.getServletContext()
					.getRealPath("/template");
			try {
				for (int i = 0; i < paths.length; i++) {
					String className = "";
					String templateName = "";
					String packageName = paths[i].substring(
							paths[i].indexOf("com"),
							paths[i].lastIndexOf("\\") - 1).replaceAll(
							"\\\\\\\\", ".");
					if (paths[i].contains("entity")) {
						className = name;
						templateName = "entity";
						map.put("entityPackage", packageName);
						map.put("entityName", className);
						map.put("modelName", className.replaceFirst(
								className.substring(0, 1),
								className.substring(0, 1).toLowerCase()));
					} else if (paths[i].contains("dao")) {
						className = name + "Dao";
						templateName = "dao";
						map.put("daoPackage", packageName);
						map.put("daoName", className);
						map.put("daoModelName", className.replaceFirst(
								className.substring(0, 1),
								className.substring(0, 1).toLowerCase()));
					} else if (paths[i].contains("service")) {
						className = name + "Service";
						templateName = "service";
						map.put("servicePackage", packageName);
						map.put("serviceName", className);
						map.put("serviceModelName", className.replaceFirst(
								className.substring(0, 1),
								className.substring(0, 1).toLowerCase()));
					} else if (paths[i].contains("web")) {
						className = name + "Action";
						templateName = "action";
						map.put("actionPackage", packageName);
						map.put("actionName", className);
						map.put("actionModelName", className.replaceFirst(
								className.substring(0, 1),
								className.substring(0, 1).toLowerCase()));
					}
					map.put("className", className);
					String filePath = paths[i] + className + ".java";
					templateName = "/" + templateName + ".ftl";
					File file = new File(paths[i]);
					if (!file.exists()) {
						file.mkdirs();
					}
					File classFile = new File(filePath);
					if (classFile.exists()) { // 备份
						File bakFile = new File("f:\\iframe\\" + className
								+ TemplateAction.getTime() + ".bak");
						if (bakFile.exists()) {

						}
						FileUtils.copyFile(classFile, bakFile);
					}
					FreemarkerUtil.analysisTemplate(templatePath, templateName,
							filePath, map);
				}
				this.sendMessage(true, "文件发布成功");
				logger.info("文件发布成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.sendMessage(false, "文件发布失败");
				logger.info("文件发布失败:" + e.getMessage());
			}
		}
	}

	/**
	 * 获取所有数据库表
	 * 
	 * @throws Exception
	 */
	public void tables() throws Exception {
		Connection conn = DBHelper.getConnection();
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

	private static String getTime() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		return year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分"
				+ second + "秒";

	}

}
