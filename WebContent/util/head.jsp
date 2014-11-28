<%@page import="com.skss.app.entity.Role"%>
<%@page import="com.skss.app.entity.Menu"%>
<%@page import="java.util.List"%>
<%@page import="com.skss.app.entity.User"%>
<%@ page language="java" pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/css/style.css">
<script src="${path }/jquery/jquery.min.js"></script>
<script src="${path }/jquery/easyui/jquery.easyui.min.js"></script>
<script src="${path }/jquery/easyui/easyui-lang-zh_CN.js"></script>
<script src="${path }/util/main.js"></script>
<%
	/* User user = (User)session.getAttribute("user");
	System.out.print("user:"+user);
	List<Menu> list = user.getRoles().get(0).getMenuList();
	String menuIds = "";
	for (int i = 0; i < list.size(); i++) {
		menuIds += list.get(i).getId() + ",";
	}
	menuIds = menuIds.substring(0, menuIds.length() - 1);
	System.out.println("menuIds:"+ menuIds); */
/* 	List<Role> roles = (List<Role>)session.getAttribute("role");
	System.out.println(roles.get(0).getMenuList()); */
	
	
%>
