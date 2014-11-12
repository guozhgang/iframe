<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_org"></div>
    <div id="org_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newOrg()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrg()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeOrg()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="org_search" data-options="prompt:'请输入您要查询的关键字'" name="org_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="org_reset">重置</a></div>
    </div>
    
    <div id="org_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#org_dlg_buttons" data-options="onClose: function() {
					$('#org_form').form('clear');
				}">
        <div class="ftitle">机构信息</div>
        <form id="org_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>机构名称:</label>
                <input name="orgName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>机构代码:</label>
                <input name="orgCode" class="easyui-textbox" required="true">           
            </div>
        </form>
    </div>
    <div id="org_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveOrg()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#org_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_org').datagrid({
				url: 'org!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#org_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '机构名称',
						field : 'orgName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#org_search").searchbox({
				searcher: function(value, name) {
					$("#build_org").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#org_reset").click(function(){
				$("#org_search").searchbox("clear");
				$("#build_org").datagrid('load', {
					key:''
				});
			});
			setPagination("build_org");
			//创建菜单下拉列表
			$("#org_menu").combotree({
				valueField: 'id',
		        textField: 'text',
		        multiple: true,
		        url: '${path}/menu!findAllMenu.action',
		        onCheck: function(node, checked) {
		        	$("input[name=menuIds]").val($("#org_menu").combotree("getValues"));
		        }
			});
		});
        var url;
        function newOrg(){
            $('#org_dlg').dialog('open').dialog('setTitle','添加机构');
            url = "${path}/org!save.action";
        }
        function editOrg() {
        	var data = $("#build_org").datagrid("getSelected");
        	if (data) {
        		$('#org_dlg').dialog('open').dialog('setTitle','编辑机构');
        		var val = "";
        		for (var i = 0; i < data.menuList.length; i++) {
        			val += data.menuList[i].id + ",";
        		}
        		val = val.substr(0, val.length - 1);
        		$("#org_menu").combotree("setValues", val);
        		$("input[name=menuIds]").val(val);
            	$("#org_form").form('load', data);
                url = "${path}/org!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeOrg() {
        	var record = $("#build_org").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '${path}/org!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_org").datagrid("reload");
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
       	function saveOrg() {
       		$("#org_form").form('submit', {
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
       					$('#org_dlg').dialog('close');
           				$("#build_org").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>