<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_role"></div>
    <div id="role_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRole()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeRole()">删除</a>
    </div>
    <div id="role_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#role_dlg_buttons">
        <div class="ftitle">角色信息</div>
        <form id="role_form" class="form" method="post" novalidate>
            <div class="fitem">
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
				pageSize:20,
				selectOnCheck : false,
				checkOnSelect : true,
				fit:true,
				toolbar:'#role_toolbar',
				columns : [[
					{
						checkbox:true,
						width : 120
					},{
						title : '角色名称',
						field : 'roleName',
						align : 'center',
						width : 120
					}
				]]
			});	
			//创建菜单下拉列表
			$("#role_menu").combotree({
				valueField: 'id',
		        textField: 'text',
		        multiple: true,
		        url: '${path}/menu!findAllMenu.action',
		        onCheck: function(node, checked) {
		        	$("input[name=menuIds]").val($("#role_menu").combotree("getValues"));
		        }
			});
		});
        var url;
        function newRole(){
            $('#role_dlg').dialog('open').dialog('setTitle','添加角色');
            url = "${path}/role!save.action";
        }
        
       	function saveRole() {
       		$("#role_form").form('submit', {
       			url: url,
       			onSubmit: function() {
       				return true;
       			},
       			success: function(data) {
       				data = eval("(" + data + ")");
       				$.messager.show( {
						title : '系统提示',
						msg : data.message
					});
       			}
       		});
       	}
    </script>

</body>