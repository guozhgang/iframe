<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_org"></div>
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
			$('#build_org').treegrid({
				url: 'org!list.action',
				method: 'post',
                idField: 'id',
                treeField: 'text',
                fit: true,
                fitColumns: true,
				toolbar:'#org_toolbar' ,
				columns : [[
					{
						field:'id',
						hidden:true
					},{
						title : '组织机构名称',
						field : 'text',
						width:200
					},{
						title:'组织机构代码',
						field:'code',
						hidden:false,
						width:200
					}
				]] 
			});
			$("#parent").combotree({
		        url: 'org!list.action',
		        onClick: function(node) {
		        	$("input[name=parent]").val(node.id);
		        }
			});
			//查询
			$("#org_search").searchbox({
				searcher: function(value, name) {
					$("#build_org").treegrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#org_reset").click(function(){
				$("#org_search").searchbox("clear");
				$("#build_org").treegrid('load', {
					key:''
				});
			});
			setPagination("build_org");
			
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
        		flag = "edit";
        		$('#org_dlg').dialog('open').dialog('setTitle','编辑机构');
            	$("#org_form").form('load', data);
            	if (!parent) {
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
       					$('#org_dlg').dialog('close');
       					var checkedOrg = $("#build_org").treegrid("getSelected");
       					if (id) { 
       						var toParent = $("#build_org").treegrid("getParent", id);
       						if (flag == "edit") {  //修改刷新
       							var checkedParent = $("#build_org").treegrid("getParent", checkedOrg.id);
       							if (checkedParent == toParent) {  //判断要修改的目标节点是否一致
       								if ($("#build_org").treegrid("isLeaf", id)) {   //刷新目标节点
               							$("#build_org").treegrid("reload", toParent.id);
               						} else {
               							$("#build_org").treegrid("reload", checkedParent.id);
               						}
       							} else { 
       								alert(1);
       								 if ($("#build_org").treegrid("isLeaf", checkedOrg.id)) {  //选中节点为叶子节点，刷新父节点
               							$("#build_org").treegrid("reload", checkedParent.id);
               						} else {
               							$("#build_org").treegrid("reload", checkedOrg.id);
               						} 
               						if ($("#build_org").treegrid("isLeaf", id)) {   //刷新目标节点
               							$("#build_org").treegrid("reload", toParent.id);
               						} else {
               							$("#build_org").treegrid("reload", id);
               						}
       							}
       						} else if (flag == "add") {  //添加刷新
       							if ($("#build_org").treegrid("isLeaf", id)) {   //刷新目标节点
           							if (!toParent) {
           								$("#build_org").treegrid("reload");
           							} else {
           								$("#build_org").treegrid("reload", toParent.id);
           							}
           						} else {
           							$("#build_org").treegrid("reload", id);
           						}
       						}
       					} else { 
       						$("#build_org").treegrid("reload");
       					}
       				}
       			}
       		});
       	}
    </script>

</body>
