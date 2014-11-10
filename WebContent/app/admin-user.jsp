<%@ page language="java" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$('#user').datagrid({
			url: '${path}/user!findAllUser.action',
			method: 'post',
			fitColumns: true,
			singleSelect: true,
			rownumbers: true,
			pagination :true,
			pageSize:20,
			selectOnCheck : false,
			checkOnSelect : true,
			fit:true,
			columns : [[
				{
					title : '用户名',
					field : 'userName',
					width : 120
				}
			]]
		});	
	});
</script>
<div id="user"></div>
