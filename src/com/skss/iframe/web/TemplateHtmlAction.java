package com.skss.iframe.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ResultPath;

import com.skss.app.entity.Menu;
import com.skss.app.entity.Role;
import com.skss.app.entity.User;
import com.skss.app.service.MenuService;
import com.skss.app.service.RoleService;
import com.skss.iframe.dao.TemplateData;
import com.skss.iframe.entity.Template;
import com.skss.iframe.entity.TreeNode;
import com.skss.iframe.template.util.FreemarkerUtil;
import com.skss.iframe.util.string.StringUtil;

@ResultPath("/app")
public class TemplateHtmlAction extends
		ActionUtil<Template, TemplateHtmlAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private TemplateData templateData;
	@Resource
	private MenuService menuService;
	@Resource
	private RoleService roleService;

	public void deployment() {
		// saveTemplate();
		String className = request.getParameter("tableName");
		String htmlName = request.getParameter("htmlName");
		String desc = request.getParameter("desc");
		htmlName = StringUtil.format(htmlName);
		try {
			String filePath = ServletActionContext.getServletContext()
					.getRealPath("app") + "\\" + htmlName + ".jsp";
			if (new File(filePath).exists()) {
				this.sendMessage(false, "文件名已存在");
			} else {
				Map<String, Object> map = TemplateData.columns(className);
				className = StringUtil.format(className);
				map.put("entityModelName", className.replaceFirst(className
						.substring(0, 1), className.substring(0, 1)
						.toLowerCase()));
				map.put("entityName", className.replaceFirst(className
						.substring(0, 1), className.substring(0, 1)
						.toUpperCase()));
				String path = ServletActionContext.getServletContext()
						.getContextPath();
				map.put("path", path);
				String templatePath = ServletActionContext.getServletContext()
						.getRealPath("/template");
				String templateName = "/html.ftl";
				FreemarkerUtil.analysisTemplate(templatePath, templateName,
						filePath, map);
				Menu menu = new Menu();
				menu.setText(desc);
				menu.setParent("1");
				menu.setNodeId(htmlName + ".jsp");
				menuService.saveMenu(menu); // 保存菜单
				User user = (User) session.get("user");
				Role role = user.getRoles().get(0);
				List<Menu> menus = role.getMenuList();
				menus.add(menu);
				roleService.save(role, "");
				this.sendMessage(true, "文件发布成功");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.sendMessage(false, "文件发布失败");
		}
	}

	public String readTemplate() {
		try {
			String templatePath = ServletActionContext.getServletContext()
					.getRealPath("template") + "\\html.ftl";
			FileInputStream fis = new FileInputStream(new File(templatePath));
			char[] data = new char[fis.available()];
			Reader reader = new InputStreamReader(fis, "utf-8");
			int n = reader.read(data);
			reader.close();
			request.setAttribute("content", new String(data, 0, n));
			return "";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void saveTemplate() {
		String content = request.getParameter("content");
		String templatePath = ServletActionContext.getServletContext()
				.getRealPath("template") + "\\html.ftl";
		try {
			if (content == "" || content == null) {
				this.sendMessage(false, "发布失败,模板内容不能为空");
			} else {
				FileOutputStream fos = new FileOutputStream(new File(
						templatePath));
				OutputStreamWriter write = new OutputStreamWriter(fos, "utf-8");
				write.write(content);
				write.close();
				this.sendMessage(true, "模板保存成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("模板保存失败：" + e.getMessage());
			this.sendMessage(false, "模板保存失败");
		}
	}

	@SuppressWarnings("unchecked")
	public void tables() {
		List<TreeNode> listnode = null;
		try {
			List<Object> list = templateData.tables();
			listnode = new ArrayList<TreeNode>();
			if (list.size() != 0) {
				TreeNode node = null;
				Map<String, String> map = new HashMap<String, String>();

				for (int i = 0; i < list.size(); i++) {
					map = (Map<String, String>) list.get(i);
					node = new TreeNode();
					node.setId(map.get("name"));
					node.setText(map.get("name"));
					node.setAttributes(attributes);
					node.setState("open");
					listnode.add(node);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.sendTree(listnode);
	}
}
