<%@ page language="java"  pageEncoding="utf-8"%>
<jsp:include page="../util/head.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
        	<ul id="menu_tree"></ul>
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