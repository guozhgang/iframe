<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
 <div id="build_table"></div>
    <div id="table_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="build_template()">构建JAVA</a>
    </div>
    
    <div id="table_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#table_dlg_buttons" data-options="onClose: function() {
					$('#table_form').form('clear');
				}">
        <div class="ftitle">JAVA模板</div>
        <form id="org_form" class="form" method="post" novalidate>
        	<input type="hidden" name="name" id="table_name"/>
        	<div class="fitem">
                <label>构建POJO:</label>
                <input name="path" class="easyui-textbox" required="true" value="F:\\workspace\\iframe\\test\\com\\skss\\test\\entity\\">
            </div>
            <div class="fitem">
                <label>构建Dao:</label>
                <input name="path" class="easyui-textbox" required="true" value="F:\\workspace\\iframe\\test\\com\\skss\\test\\dao\\">
            </div>
            <div class="fitem">
                <label>构建Service:</label>
                <input name="path" class="easyui-textbox" required="true" value="F:\\workspace\\iframe\\test\\com\\skss\\test\\service\\">           
            </div>
            <div class="fitem">
                <label>构建Action:</label>
                <input name="path" class="easyui-textbox" required="true" value="F:\\workspace\\iframe\\test\\com\\skss\\test\\action\\">           
            </div>
        </form>
    </div>
    <div id="table_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="submitJAVA()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#table_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
    	$(function() {
    		$("#build_table").datagrid({
    			url: 'template!tables.action',
    			method: 'post',
				fitColumns: true,
				singleSelect: false,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#table_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						title : '表名',
						field : 'name',
						align : 'center',
						width : 120
					}
				]]
    			
    		});
    	});
    	function build_template() {
    		var row = $("#build_table").datagrid("getSelections");
    		if (row) {
    			var tableName = "";
    			for (var i = 0; i < row.length; i++) {
    				tableName += row[i].name + ",";
    			}
    			$("#table_dlg").dialog("open").dialog("setTitle", "JAVA");
    			$("#table_name").val(tableName.substring(0, tableName.length - 1));
    		} else {
    			$.messager.show({
    				title: '系统提示',
    				msg : '请至少选择一张表'
    			});
    		}
    	}
    	function submitJAVA() {
    		$("#org_form").form('submit',{
    			url:'template!deployment.action',
    			onSumit:function() {
    				return true;
    			},
    			success:function(r) {
    				r = eval("("+r+")");
    				$.messager.show({
    					title: '系统提示',
    					msg: r.message
    				});
    			}
    		});
    	}
    </script>
    
    

</body>
