     <%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
<body>
    <div id="build_test"></div>
    <div id="test_toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTest()">Test</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTest()">编辑ddddddd</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeTest()">删除</a>
        <div style="float:right;"><input class="easyui-searchbox" id="test_search" data-options="prompt:'请输入您要查询的关键字'" name="test_search" style="width:200px;"/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" id="test_reset">重置</a></div>
    </div>
    
    <div id="test_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#test_dlg_buttons" data-options="onClose: function() {
					$('#test_form').form('clear');
				}">
        <div class="ftitle">测试信息</div>
        <form id="test_form" class="form" method="post" novalidate>
            <div class="fitem">
            	<input type="hidden" name="id"/>
                <label>测试名称:</label>
                <input name="testName" class="easyui-textbox" required="true">
            </div>
           
        </form>
    </div>
    <div id="test_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveTest()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#test_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
	    $(function() {
			$('#build_test').datagrid({
				url: 'test!list.action',
				method: 'post',
				fitColumns: true,
				singleSelect: true,
				rownumbers: true,
				pagination :true,
				striped:true,
				fit:true,
				toolbar:'#test_toolbar',
				columns : [[
					{
						checkbox:true,
						field:'checkbox'
					},{
						field:'id',
						hidden:true
					},{
						title : '测试名称',
						field : 'testName',
						align : 'center',
						width : 120
					}
				]]
			});
			//查询
			$("#test_search").searchbox({
				searcher: function(value, name) {
					$("#build_test").datagrid('load',{
						key: value
					});
				}
			});
			//重置
			$("#test_reset").click(function(){
				$("#test_search").searchbox("clear");
				$("#build_test").datagrid('load', {
					key:''
				});
			});
			setPagination("build_test");
		});
        var url;
        function newTest(){
            $('#test_dlg').dialog('open').dialog('setTitle','添加测试');
            url = "/iframe/test!save.action";
        }
        function editTest() {
        	var data = $("#build_test").datagrid("getSelected");
        	if (data) {
        		$('#test_dlg').dialog('open').dialog('setTitle','编辑测试');
            	$("#test_form").form('load', data);
                url = "/iframe/test!save.action";
        	} else {
        		$.messager.show({
        			title:'系统提示',
        			msg:'请先选择一条信息'
        		});
        	}
        	
        }
        function removeTest() {
        	var record = $("#build_test").datagrid("getSelected");
        	if (record) {
        		$.messager.confirm("系统提示", "您确认要删除该条信息吗?", function(ok) {
        			if (ok) {
        				$.ajax({
                			url: '/iframe/test!remove.action',
                			data: {id: record.id},
                			dataType: 'text',
                			type: 'post',
                			success: function(r) {
                				r = eval("("+r+")");
                				$.messager.show({
                					title: '系统提示',
                					msg: r.message
                				});
                				$("#build_test").datagrid("reload");
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
       	function saveTest() {
       		$("#test_form").form('submit', {
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
       					$('#test_dlg').dialog('close');
           				$("#build_test").datagrid("reload");
       				}
       			}
       		});
       	}
    </script>

</body>
 
 
 
 
