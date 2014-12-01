package com.skss.iframe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skss.iframe.entity.Template;
import com.skss.iframe.util.DBHelper;
import com.skss.iframe.util.string.StringUtil;

public class TemplateData {
	/**
	 * 获取列名
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public static Map<String, Object> columns(String name) throws Exception,
			SQLException {
		Connection conn = DBHelper.getConnection();
		PreparedStatement pre = conn.prepareStatement("show FIELDS from "
				+ name + "");
		ResultSet rs = pre.executeQuery();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Template> columnlist = new ArrayList<Template>();
		String type = "";
		String methodName = "";
		while (rs.next()) {
			if (rs.getString(2).contains("(")) {
				type = rs.getString(2).substring(0,
						rs.getString(2).indexOf("("));
			} else {
				type = rs.getString(2);
			}
			if ("varchar".equals(type)) {
				type = "String";
			} else if ("int".equals(type)) {
				type = "int";
			}
			methodName = rs.getString(1).replaceFirst(
					rs.getString(1).substring(0, 1),
					rs.getString(1).substring(0, 1).toUpperCase());
			Template template = new Template(
					StringUtil.format(rs.getString(1)), rs.getString(1),
					StringUtil.format(methodName), type);
			columnlist.add(template);
		}
		map.put("columns", columnlist);
		pre.close();
		conn.close();
		return map;
	}
}
