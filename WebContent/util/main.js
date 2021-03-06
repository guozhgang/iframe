
/**
 * 创建tab页
 * @param node
 */
function buildTab(node) {
	var iframe = "<iframe src='"+node.attributes.nodeId+"' frameborder='0' style='width:100%;height:100%'></iframe>";
	if ($("#menuTab").tabs("exists", node.text)) {
		$("#menuTab").tabs("select",node.text);
	} else {
		$("#menuTab").tabs('add', {
			title:node.text,
			content:iframe,
			closable:true
		});
	}
}
/**
 * 设置分页风格
 * @param id datagrid id
 */
function setPagination(id) {
	$($("#" + id).datagrid("getPager")).pagination({
		pageList: [5,10,15,20],
		pageSize:15,
		layout:['list','sep','first','prev','links','next','last','sep','refresh'],
		displayMsg:'当前显示 {from} / {to} 条记录   共 {total} 条记录'
	});
}