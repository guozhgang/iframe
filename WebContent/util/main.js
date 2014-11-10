$(function() {
	$("#menu_tree").tree({
		url:'${path}/menu!findAllMenu.action',
		onlyLeafCheck: true,
		onClick: function(node) {
			var isLeaf = $("#menu_tree").tree("isLeaf", node.target);
			if (isLeaf) buildTab(node); 
		}
	});
});
/**
 * 创建tab页
 * @param node
 */
function buildTab(node) {
	var iframe = "<iframe src='"+node.attributes.nodeId+".jsp' frameborder='0' style='width:100%;height:100%'></iframe>";
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