<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="${path }/jquery/easyui/css/style.css">
<script src="${path }/jquery/jquery.min.js"></script>
<script src="${path }/jquery/easyui/jquery.easyui.min.js"></script>
<script src="${path }/jquery/easyui/easyui-lang-zh_CN.js"></script>
<body>
    <div id="login" class="easyui-panel" style="width:400px;height:200px;padding:10px 20px"
            closed="false" >
        <div class="ftitle">用户登陆</div>
        <form id="login_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>用户名:</label>
                <input name="loginName" class="easyui-textbox"  required="true">
            </div>
            <div class="fitem">
            	<label>密&nbsp;&nbsp;码:</label>
                <input name="password" class="easyui-textbox"  required="true">
            </div>
            <div class="fitem" style="text-align:center;">
                <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="login()" style="width:90px">登陆</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()" style="width:90px">取消</a>
            </div>
        </form>
        <script type="text/javascript">
	        $(document).keydown(function(event) {
	        	if (event.keyCode == 13 || event.keyCode == 15) {
	        		login();
	        	}
	        });
        	function login() {
        		$("#login_form").form('submit',{
        			url:'user!login.action',
        			onSubmit:function() {
        				
        			},
        			success:function(data) {
        				data = eval("("+data+")");
        				
        				$.messager.show({
        					title:'系统提示',
        					msg:data.message
        				});
        				if (data.flag) {
        					window.location.href = "user-login.jsp";
        				} 
        			}
        		});
        	}
        </script>
    </div>
</body>