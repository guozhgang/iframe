package com.skss.iframe.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection(
						"jdbc:mysql://127.0.0.1:3306/app?useUnicode=true&characterEncoding=utf-8",
						"root", "root");
		return conn;
	}
}
