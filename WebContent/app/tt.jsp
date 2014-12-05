        <%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_ssRoleMenu"></div>
    <div id="ssRoleMenu_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSsRoleMenu()">SsRoleMenu</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSsRoleMenu()">编辑ddddddd</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSsRoleMenu()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="ssRoleMenu_search" data-options="prompt:'请输入您要查询的关键字'" name="ssRoleMenu_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="ssRoleMenu_reset">重置</a></div>
    </div>
    
    <div id="ssRoleMenu_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#ssRoleMenu_dlg_buttons" data-options="onClose: function() {
					$('#ssRoleMenu_form').form('clear');
				}">
        <div class="ftitle">测试信息</div>
        <form id="ssRoleMenu_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>测试名称:</label>
                <input name="ssRoleMenuName" class="easyui-textbox" required="true">
            </div>
           
        </form>
    </div>
    <div id="ssRoleMenu_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSsRoleMenu()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ssRoleMenu_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_ssRoleMenu').datagrid({
				url: 'ssRoleMenu!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#ssRoleMenu_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '测试名ddd称',
						field : 'ssRoleMenuName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#ssRoleMenu_search").searchbox({
				searcher: function(value, name) {
					$("#build_ssRoleMenu").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#ssRoleMenu_reset").click(function(){
				$("#ssRoleMenu_search").searchbox("clear");
				$("#build_ssRoleMenu").datagrid('load', {
					key:''
				});
			});
			setPagination("build_ssRoleMenu");
		});
        var url;
        function newSsRoleMenu(){
            $('#ssRoleMenu_dlg').dialog('open').dialog('setTitle','添加测试');
            url = "/iframe/ssRoleMenu!save.action";
        }
        function editSsRoleMenu() {
        	var data = $("#build_ssRoleMenu").datagrid("getSelected");
        	if (data) {
        		$('#ssRoleMenu_dlg').dialog('open').dialog('setTitle','编辑测试');
            	$("#ssRoleMenu_form").form('load', data);
                url = "/iframe/ssRoleMenu!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeSsRoleMenu() {
        	var record = $("#build_ssRoleMenu").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '/iframe/ssRoleMenu!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_ssRoleMenu").datagrid("reload");
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
       	function saveSsRoleMenu() {
       		$("#ssRoleMenu_form").form('submit', {
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
       					$('#ssRoleMenu_dlg').dialog('close');
           				$("#build_ssRoleMenu").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>
 
 
 
 
 
 
 
