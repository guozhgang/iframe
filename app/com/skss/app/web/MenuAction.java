package com.skss.app.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.skss.app.entity.Menu;
import com.skss.app.service.MenuService;
import com.skss.iframe.entity.TreeNode;
import com.skss.iframe.web.ActionUtil;

/*@InterceptorRefs({@InterceptorRef("defaultStack"),@InterceptorRef("MyIntercept")})
@ParentPackage(value="struts-interceptor")*/
public class MenuAction extends ActionUtil<Menu>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private MenuService menuService;
	/**
	 * 异步获取菜单
	 */
	public void findMenuByAsync() {
		String id = request.getParameter("id");
		List<Menu> list = menuService.findMenuByAsync(id);
		List<TreeNode> treelist = new ArrayList<TreeNode>();
		if (list.size() != 0) {
			TreeNode node = null;
			Menu menu = null;
			for (int i = 0; i < list.size(); i++) {
				attributes = new HashMap<String, Object>();
				menu = list.get(i);
				node = new TreeNode();
				node.setId(menu.getId());
				node.setText(menu.getText());
				attributes.put("nodeId", menu.getNodeId());
				node.setAttributes(attributes);
				node.setState(menuService.findCount(menu.getId()) == 0 ? "open" : "closed");
				treelist.add(node);
			}
		}
		sendTree(treelist);
	}
	/**
	 * 获取所有菜单
	 */
	public void findAllMenu() {
		String id = request.getParameter("id");
		sendTree(appendChildren(id));
	}
	/**
	 * 递归追加子节点
	 * @param id
	 * @return
	 */
	public List<TreeNode> appendChildren(String id) {
		List<Menu> list = menuService.findMenuByAsync(id);
		List<TreeNode> treelist = new ArrayList<TreeNode>();
		if (list.size() != 0) {
			TreeNode node = null;
			Menu menu = null;
			for (int i = 0; i < list.size(); i++) {
				attributes = new HashMap<String, Object>();
				menu = list.get(i);
				node = new TreeNode();
				node.setId(menu.getId());
				node.setText(menu.getText());
				attributes.put("nodeId", menu.getNodeId());
				node.setAttributes(attributes);
				if (menuService.findCount(menu.getId()) == 0) {
					node.setState("open");
				} else {
					node.setState("closed");
					node.setChildren(appendChildren(menu.getId()));
				}
				treelist.add(node);
			}
		}
		return treelist;
	}
	
	
}
