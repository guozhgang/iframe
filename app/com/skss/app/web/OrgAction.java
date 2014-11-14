package com.skss.app.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.skss.app.dao.OrgTree;
import com.skss.app.entity.Organization;
import com.skss.app.service.OrgService;
import com.skss.iframe.entity.TreeNode;
import com.skss.iframe.web.ActionUtil;


public class OrgAction extends ActionUtil<Organization>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private OrgService orgService;
	public void save() {
		try {
			this.orgService.save(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
			e.printStackTrace();
		}
	}
	public void list() {
		//sendJSON(this.orgService.list(model, start, rows), this.orgService.count());
		String id = request.getParameter("id");
		/*List<Organization> list = this.orgService.list(id);
		List<TreeNode> treelist = new ArrayList<TreeNode>();
		if (list.size() != 0) {
			OrgTree node = null;
			Organization org = null;
			for (int i = 0; i < list.size(); i++) {
				org = list.get(i);
				node = new OrgTree();
				node.setId(org.getId());
				node.setText(org.getText());
				node.setCode(org.getCode());
				node.setAttributes(attributes);
				node.setState(orgService.count(org.getId()) == 0 ? "open" : "closed");
				treelist.add(node);
			}
		}
		sendTree(treelist);*/
		sendTree(this.orgService.appendChildren(id));
	}
	
	public void findAllOrg() {
		sendJSON(this.orgService.list(start, rows, model.getId()), 50);
	}
	
}
