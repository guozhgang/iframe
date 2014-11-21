<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
   	<div class="easyui-layout" data-options="fit:true">
   		<div data-options="region:'west',title:'组织机构',split:false " style="width:200px;">
   			<div id="build_org_tree"></div>
   		</div>
   		<div data-options="region:'center'">
   			 <div id="build_org"></div>
   		</div>
   	</div>
    <div id="org_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newOrg()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrg()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeOrg()">删除</a>
    </div>
    
    <div id="org_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#org_dlg_buttons" data-options="onClose: function() {
					$('#org_form').form('clear');
				}">
        <div class="ftitle">机构信息</div>
        <form id="org_form" class="form" method="post" novalidate>
        	<div class="fitem">
            	<input type="hidden" name="id"/>
                <label>上级机构:</label>
                <input id="parent"  class="easyui-combotree">
                <input type="hidden" name="parent"/>
            </div>
            <div class="fitem">
                <label>机构名称:</label>
                <input name="text" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>机构代码:</label>
                <input name="code" class="easyui-textbox" required="true">           
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
				method: 'post',
		        fit: true,
		        animate: true,
		        fitColumns: true,
		        singleSelect:true,
		        pagination: true,
				toolbar:'#org_toolbar' ,
				columns : [[
					{
						field:'checkbox',
						checkbox:true

					},{
						field:'id',
						hidden:true
					},{
						title : '名称',
						field : 'text',
						width:200
					},{
						title:'代码',

						field:'code',
						hidden:false,
						width:200
					},{
						title:'级别',
						field:'level',
						width:200

					}
				]] 
			});
			setPagination("build_org");
			$("#build_org_tree").tree({
				animate: true,
	    		url:'${path}/org!list.action',
	    		onClick:function(node) {
	    			$("#build_org").datagrid("options").url = '${path}/org!findAllOrg.action';
	    			$("#build_org").datagrid("load", {
	    				id: node.id
	    			});
	   			}
	    	});
			$("#parent").combotree({
		        url: 'org!list.action',
		        onClick: function(node) {
		        	$("input[name=parent]").val(node.id);
		        }
			});
			
			
		});
        var url,flag;
        function newOrg(){
        	flag = "add";
        	var row = $("#build_org").treegrid("getSelected");
        	if (row) {
        		$("#parent").combotree("setText", row.text);
        		$("input[name=parent]").val(row.id);
        	}
            $('#org_dlg').dialog('open').dialog('setTitle','添加机构');
            url = "${path}/org!save.action";
        }
        function editOrg() {
        	var data = $("#build_org").treegrid("getSelected");
        	var parent = $("#build_org").treegrid("getParent", data.id);
        	if (data) {
        		var parent = $("#build_org").treegrid("getParent", data.id);

        		flag = "edit";
        		$('#org_dlg').dialog('open').dialog('setTitle','编辑机构');
            	$("#org_form").form('load', data);
            	if (!parent) {
            		var orgtree = $("#build_org_tree").tree("getSelected");
            		if(orgtree) {
            			$("#parent").combotree("setText", orgtree.text);
                		$("input[name=parent]").val(orgtree.id);
            		};
            	} else {
            		$("#parent").combotree("setText", parent.text);
            		$("input[name=parent]").val(parent.id);
            	}
                url = "${path}/org!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeOrg() {
        	var record = $("#build_org").treegrid("getSelected");
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
                				$("#build_org").treegrid("reload");
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
       		var id = $("input[name=parent]").val();
       		 
       		
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
       					
       						$("#build_org_tree").tree("reload");	
       					

       				}
       				$('#org_dlg').dialog('close');
       			}
       		}); 
       	}
    </script>

</body>
