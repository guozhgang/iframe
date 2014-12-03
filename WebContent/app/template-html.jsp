<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../util/head.jsp"/>
 <div style="float:right">
 	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" plain="false" onclick="opendlg()">发布模板</a>
 	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-save" plain="false" onclick="save()">保存模板</a>
 </div>
 <div id="template_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="deployment()" style="width:90px">发布</a>
    </div>
 <div id="template_dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
            closed="true" buttons="#template_dlg_buttons" data-options="onClose: function() {
					$('#template_form').form('clear');
				}">
        <div class="ftitle">文件信息</div>
        <form id="template_form" class="form" method="post" novalidate>
        	<div class="fitem">
            	<input type="hidden" name="content" id="html_content"/>
                <label>文件名称:</label>
                <input name="htmlName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>文件描述:</label>
                <input name="desc" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>数据来源:</label>
                <input name="tableName" id="source" class="easyui-combobox" data-options="url:'template-html!tables.action',valueField:'id',textField:'text'"  required="true">
            </div>
        </form>
    </div>
 <script type="text/javascript">
	function save() {
		$.ajax({
			url:'template-html!saveTemplate.action',
			dataType:'text',
			type:'post',
			data:{
				content:$("#content").val()
			},
			success:function(data) {
				data = eval("("+data+")");
				$.messager.show({
					title:'系统提示',
					msg:data.message
				});
			}
		});
	}
	function opendlg() {
		$("#template_dlg").dialog("open").dialog("setTitle", "模板");
	}
	function deployment() {
		$("#html_content").val($("#content").val());
		$("#template_form").form('submit', {
			url:'template-html!deployment.action',
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(data) {
				data = eval("("+data+")");
				$.messager.show({
					title:'系统提示',
					msg:data.message
				});
				if (data.flag) {
					$("#template_dlg").dialog("close");
				}
			}
		});
	}
</script>
 <textarea rows="48" id="content" name="content" cols="150" style="border:0">
 ${content}
 </textarea>

