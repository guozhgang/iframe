<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_user"></div>
    <div id="user_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="user_search" data-options="prompt:'请输入您要查询的关键字'" name="user_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="user_reset">重置</a></div>
    </div>
    
    <div id="user_dlg" class="easyui-dialog" style="width:400px;height:380px;padding:10px 20px"
            closed="true" buttons="#user_dlg_buttons" data-options="onClose: function() {
					$('#user_form').form('clear');
				}">
        <div class="ftitle">用户信息</div>
        <form id="user_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>用户名称:</label>
                <input name="userName" class="easyui-textbox"  required="true">
            </div>
            <div class="fitem">
            	<label>登陆名称:</label>
                <input name="loginName" class="easyui-textbox"  required="true">
            </div>
            <div class="fitem">
            	<label>登录密码:</label>
                <input name="password" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
            	<label>确认密码:</label>
                <input name="loginName" class="easyui-textbox"  required="true">
            </div>
            
            
            
        </form>
    </div>
    <div id="user_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#user_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_user').datagrid({
				url: 'user!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#user_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '用户名',
						field : 'userName',
						align : 'center',
						width : 120
					},{
						title: '登陆名',
						field:'loginName',
						width: 120
					}
				]]
			});
			//查询
			$("#user_search").searchbox({
				searcher: function(value, name) {
					$("#build_user").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#user_reset").click(function(){
				$("#user_search").searchbox("clear");
				$("#build_user").datagrid('load', {
					key:''
				});
			});
			setPagination("build_user");
			//创建菜单下拉列表
			$("#user_menu").combotree({
				valueField: 'id',
		        textField: 'text',
		        multiple: true,
		        url: '${path}/menu!findAllMenu.action',
		        onCheck: function(node, checked) {
		        	$("input[name=menuIds]").val($("#user_menu").combotree("getValues"));
		        }
			});
		});
        var url;
        function newUser(){
            $('#user_dlg').dialog('open').dialog('setTitle','添加用户');
            url = "${path}/user!save.action";
        }
        function editUser() {
        	var data = $("#build_user").datagrid("getSelected");
        	if (data) {
        		$('#user_dlg').dialog('open').dialog('setTitle','编辑用户');
        		var val = "";
        		for (var i = 0; i < data.menuList.length; i++) {
        			val += data.menuList[i].id + ",";
        		}
        		val = val.substr(0, val.length - 1);
        		$("#user_menu").combotree("setValues", val);
        		$("input[name=menuIds]").val(val);
            	$("#user_form").form('load', data);
                url = "${path}/user!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeUser() {
        	var record = $("#build_user").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '${path}/user!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_user").datagrid("reload");
                			}
                		});
        			}
        		});
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg: '请先选择一条信息'
        		});
        	}
        }
       	function saveUser() {
       		$("#user_form").form('submit', {
       			url: url,
       			onSubmit: function() {
       				return $(this).form('validate');
       			},
       			success: function(data) {
       				data = eval("(" + data + ")");
       				$.messager.show( {
						title : '系统提示',
						msg : data.message
					});
       				if (data.flag) {
       					$('#user_dlg').dialog('close');
           				$("#build_user").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>