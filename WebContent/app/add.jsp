                <%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_ssOrg"></div>
    <div id="ssOrg_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSsOrg()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSsOrg()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSsOrg()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="ssOrg_search" data-options="prompt:'请输入您要查询的关键字'" name="ssOrg_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="ssOrg_reset">重置</a></div>
    </div>
    
    <div id="ssOrg_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#ssOrg_dlg_buttons" data-options="onClose: function() {
					$('#ssOrg_form').form('clear');
				}">
        <div class="ftitle">用户信息</div>
        <form id="ssOrg_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>用户名称:</label>
                <input name="ssOrgName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>性别:</label>
                <input name="ssOrgName" class="easyui-textbox" required="true">
            </div>
           
        </form>
    </div>
    <div id="ssOrg_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSsOrg()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ssOrg_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_ssOrg').datagrid({
				url: 'ssOrg!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#ssOrg_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '测试名ddd称',
						field : 'ssOrgName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#ssOrg_search").searchbox({
				searcher: function(value, name) {
					$("#build_ssOrg").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#ssOrg_reset").click(function(){
				$("#ssOrg_search").searchbox("clear");
				$("#build_ssOrg").datagrid('load', {
					key:''
				});
			});
			setPagination("build_ssOrg");
		});
        var url;
        function newSsOrg(){
            $('#ssOrg_dlg').dialog('open').dialog('setTitle','添加测试');
            url = "/iframe/ssOrg!save.action";
        }
        function editSsOrg() {
        	var data = $("#build_ssOrg").datagrid("getSelected");
        	if (data) {
        		$('#ssOrg_dlg').dialog('open').dialog('setTitle','编辑测试');
            	$("#ssOrg_form").form('load', data);
                url = "/iframe/ssOrg!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeSsOrg() {
        	var record = $("#build_ssOrg").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '/iframe/ssOrg!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_ssOrg").datagrid("reload");
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
       	function saveSsOrg() {
       		$("#ssOrg_form").form('submit', {
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
       					$('#ssOrg_dlg').dialog('close');
           				$("#build_ssOrg").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
