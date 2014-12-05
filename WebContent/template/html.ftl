                 <%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_${entityModelName}"></div>
    <div id="${entityModelName}_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="new${entityName}()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit${entityName}()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="remove${entityName}()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="${entityModelName}_search" data-options="prompt:'请输入您要查询的关键字'" name="${entityModelName}_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="${entityModelName}_reset">重置</a></div>
    </div>
    
    <div id="${entityModelName}_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#${entityModelName}_dlg_buttons" data-options="onClose: function() {
					$('#${entityModelName}_form').form('clear');
				}">
        <div class="ftitle">用户信息</div>
        <form id="${entityModelName}_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>城市名称:</label>
                <input name="cityName" class="easyui-textbox" required="true">
            </div>
                       
        </form>
    </div>
    <div id="${entityModelName}_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save${entityName}()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#${entityModelName}_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_${entityModelName}').datagrid({
				url: '${entityModelName}!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#${entityModelName}_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '城市名称',
						field : 'cityName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#${entityModelName}_search").searchbox({
				searcher: function(value, name) {
					$("#build_${entityModelName}").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#${entityModelName}_reset").click(function(){
				$("#${entityModelName}_search").searchbox("clear");
				$("#build_${entityModelName}").datagrid('load', {
					key:''
				});
			});
			setPagination("build_${entityModelName}");
		});
        var url;
        function new${entityName}(){
            $('#${entityModelName}_dlg').dialog('open').dialog('setTitle','添加测试');
            url = "${path}/${entityModelName}!save.action";
        }
        function edit${entityName}() {
        	var data = $("#build_${entityModelName}").datagrid("getSelected");
        	if (data) {
        		$('#${entityModelName}_dlg').dialog('open').dialog('setTitle','编辑测试');
            	$("#${entityModelName}_form").form('load', data);
                url = "${path}/${entityModelName}!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function remove${entityName}() {
        	var record = $("#build_${entityModelName}").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '${path}/${entityModelName}!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_${entityModelName}").datagrid("reload");
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
       	function save${entityName}() {
       		$("#${entityModelName}_form").form('submit', {
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
       					$('#${entityModelName}_dlg').dialog('close');
           				$("#build_${entityModelName}").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 