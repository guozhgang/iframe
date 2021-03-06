<%@page import="com.skss.app.entity.Menu"%>
<%@page import="com.skss.app.entity.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.skss.app.entity.User"%>

<%@ page language="java" pageEncoding="utf-8"%>
<%
	 User user = (User)session.getAttribute("user");
	 List<Menu> list = user.getRoles().get(0).getMenuList();
	 String menuIds = "";
	 for (int i = 0; i < list.size(); i++) {
		menuIds += list.get(i).getId() + ",";
	 }
	 menuIds = menuIds.substring(0, menuIds.length() - 1);
%>
<%@page import="com.skss.app.entity.Menu"%>
<%@page import="com.skss.app.entity.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.skss.app.entity.User"%>
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/css/style.css">
<script src="${path }/jquery/jquery.min.js"></script>
<script src="${path }/jquery/easyui/jquery.easyui.min.js"></script>
<script src="${path }/jquery/easyui/easyui-lang-zh_CN.js"></script>
<script src="${path }/util/main.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统管理平台</title>
<style type="text/css">
    #fm{
        margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:80px;
    }
    .fitem input{
        width:160px;
    }
</style>
</head>
<body style="margin:0; padding:0;">
	 <div class="easyui-layout" style="width:100%;height:100%;">
        <!-- <div data-options="region:'north'" style="height:50px"></div>
        <div data-options="region:'south',split:true" style="height:50px;"></div>
        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div> -->
        <div data-options="region:'west',split:true" title="系统管理平台" style="width:200px;">
        	<ul id="menu_tree" class="easyui-tree" data-options="url:'${path}/menu!findAllMenu.action',
				onlyLeafCheck: true,
				cascadeCheck:false,
				animate:true,
				onClick: function(node) {
						var isLeaf = $('#menu_tree').tree('isLeaf', node.target);
						if (isLeaf) buildTab(node); 
					}
		"></ul>
        </div>
        <div data-options="region:'center'">
        	 <div id="menuTab" class="easyui-tabs" data-options="fit:true">
				<div title="首页" style="padding: 0px; overflow: hidden;" align="center">
					
				</div>
			</div> 
			
        </div>
    </div>
    
</body>
</html>