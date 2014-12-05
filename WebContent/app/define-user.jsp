           <%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_ssUser"></div>
    <div id="ssUser_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSsUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSsUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSsUser()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="ssUser_search" data-options="prompt:'请输入您要查询的关键字'" name="ssUser_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="ssUser_reset">重置</a></div>
    </div>
    
    <div id="ssUser_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#ssUser_dlg_buttons" data-options="onClose: function() {
					$('#ssUser_form').form('clear');
				}">
        <div class="ftitle">用户信息</div>
        <form id="ssUser_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>用户名称:</label>
                <input name="ssUserName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>性别:</label>
                <input name="ssUserName" class="easyui-textbox" required="true">
            </div>
           
        </form>
    </div>
    <div id="ssUser_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSsUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ssUser_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_ssUser').datagrid({
				url: 'ss-user!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#ssUser_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '测试名ddd称',
						field : 'ssUserName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#ssUser_search").searchbox({
				searcher: function(value, name) {
					$("#build_ssUser").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#ssUser_reset").click(function(){
				$("#ssUser_search").searchbox("clear");
				$("#build_ssUser").datagrid('load', {
					key:''
				});
			});
			setPagination("build_ssUser");
		});
        var url;
        function newSsUser(){
            $('#ssUser_dlg').dialog('open').dialog('setTitle','添加测试');
            url = "/iframe/ss-user!save.action";
        }
        function editSsUser() {
        	var data = $("#build_ssUser").datagrid("getSelected");
        	if (data) {
        		$('#ssUser_dlg').dialog('open').dialog('setTitle','编辑测试');
            	$("#ssUser_form").form('load', data);
                url = "/iframe/ss-user!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeSsUser() {
        	var record = $("#build_ssUser").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '/iframe/ssUser!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_ssUser").datagrid("reload");
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
       	function saveSsUser() {
       		$("#ssUser_form").form('submit', {
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
       					$('#ssUser_dlg').dialog('close');
           				$("#build_ssUser").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>
 
 
 
 
 
 
 
 
 
 
