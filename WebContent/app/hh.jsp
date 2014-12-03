       <%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_ssMenu"></div>
    <div id="ssMenu_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSsMenu()">SsMenu</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSsMenu()">编辑ddddddd</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeSsMenu()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="ssMenu_search" data-options="prompt:'请输入您要查询的关键字'" name="ssMenu_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="ssMenu_reset">重置</a></div>
    </div>
    
    <div id="ssMenu_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#ssMenu_dlg_buttons" data-options="onClose: function() {
					$('#ssMenu_form').form('clear');
				}">
        <div class="ftitle">测试信息</div>
        <form id="ssMenu_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>测试名称:</label>
                <input name="ssMenuName" class="easyui-textbox" required="true">
            </div>
           
        </form>
    </div>
    <div id="ssMenu_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSsMenu()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#ssMenu_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_ssMenu').datagrid({
				url: 'ssMenu!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#ssMenu_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '测试名ddd称',
						field : 'ssMenuName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#ssMenu_search").searchbox({
				searcher: function(value, name) {
					$("#build_ssMenu").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#ssMenu_reset").click(function(){
				$("#ssMenu_search").searchbox("clear");
				$("#build_ssMenu").datagrid('load', {
					key:''
				});
			});
			setPagination("build_ssMenu");
		});
        var url;
        function newSsMenu(){
            $('#ssMenu_dlg').dialog('open').dialog('setTitle','添加测试');
            url = "/iframe/ssMenu!save.action";
        }
        function editSsMenu() {
        	var data = $("#build_ssMenu").datagrid("getSelected");
        	if (data) {
        		$('#ssMenu_dlg').dialog('open').dialog('setTitle','编辑测试');
            	$("#ssMenu_form").form('load', data);
                url = "/iframe/ssMenu!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeSsMenu() {
        	var record = $("#build_ssMenu").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '/iframe/ssMenu!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_ssMenu").datagrid("reload");
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
       	function saveSsMenu() {
       		$("#ssMenu_form").form('submit', {
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
       					$('#ssMenu_dlg').dialog('close');
           				$("#build_ssMenu").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>
 
 
 
 
 
 
