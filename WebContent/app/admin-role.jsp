<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_role"></div>
    <div id="role_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRole()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRole()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="role_search" data-options="prompt:'请输入您要查询的关键字'" name="role_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="role_reset">重置</a></div>
    </div>
    
    <div id="role_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#role_dlg_buttons" data-options="onClose: function() {
					$('#role_form').form('clear');
				}">
        <div class="ftitle">角色信息</div>
        <form id="role_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>角色名称:</label>
                <input name="roleName" class="easyui-textbox" required="true">
                <input name="menuIds" type="hidden"/>
            </div>
            <div class="fitem">
                <label>菜&nbsp;&nbsp;&nbsp;&nbsp;单:</label>
                <input id="role_menu" required="true">                
            </div>
        </form>
    </div>
    <div id="role_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRole()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#role_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_role').datagrid({
				url: 'role!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#role_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '角色名称',
						field : 'roleName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#role_search").searchbox({
				searcher: function(value, name) {
					$("#build_role").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#role_reset").click(function(){
				$("#role_search").searchbox("clear");
				$("#build_role").datagrid('load', {
					key:''
				});
			});
			setPagination("build_role");
			//创建菜单下拉列表
			$("#role_menu").combotree({
				valueField: 'id',
		        textField: 'text',
		        cascadeCheck:false,
		        multiple: true,
		        url: '${path}/menu!findAllMenu.action',
		        onCheck: function(node, checked) {
		        	var tree = $("#role_menu").combotree("tree");
		        	var nodes = $("#role_menu").combotree("getValues");
		        	
		        	
		        	
		        	$("input[name=menuIds]").val(nodes);
		        }
			});
		});
        var url;
        function newRole(){
            $('#role_dlg').dialog('open').dialog('setTitle','添加角色');
            url = "${path}/role!save.action";
        }
        function editRole() {
        	var data = $("#build_role").datagrid("getSelected");
        	if (data) {
        		$('#role_dlg').dialog('open').dialog('setTitle','编辑角色');
        		var val = "";
        		for (var i = 0; i < data.menuList.length; i++) {
        			val += data.menuList[i].id + ",";
        		}
        		val = val.substr(0, val.length - 1);
        		$("#role_menu").combotree("setValues", val);
        		$("input[name=menuIds]").val(val);
            	$("#role_form").form('load', data);
                url = "${path}/role!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeRole() {
        	var record = $("#build_role").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '${path}/role!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_role").datagrid("reload");
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
       	function saveRole() {
       		$("#role_form").form('submit', {
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
       					$('#role_dlg').dialog('close');
           				$("#build_role").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>