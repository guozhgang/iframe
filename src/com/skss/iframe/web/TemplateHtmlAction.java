package com.skss.iframe.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ResultPath;

import com.skss.iframe.dao.TemplateData;
import com.skss.iframe.entity.Template;
import com.skss.iframe.template.util.FreemarkerUtil;
import com.skss.iframe.util.string.StringUtil;

@ResultPath("/app")
public class TemplateHtmlAction extends
		ActionUtil<Template, TemplateHtmlAction> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void deployment() throws Exception {
		String className = "test";
		String htmlName = "test";
		htmlName = StringUtil.format(htmlName);
		Map<String, Object> map = TemplateData.columns(className);
		className = StringUtil.format(className);
		map.put("entityModelName", className.replaceFirst(className.substring(
				0, 1), className.substring(0, 1).toLowerCase()));
		map.put("entityName", className.replaceFirst(className.substring(0, 1),
				className.substring(0, 1).toUpperCase()));
		String path = ServletActionContext.getServletContext().getContextPath();
		map.put("path", path);
		String templatePath = ServletActionContext.getServletContext()
				.getRealPath("/template");
		String templateName = "/html.ftl";
		String filePath = "F:\\workspace\\iframe\\WebContent\\app\\" + htmlName
				+ ".jsp";
		FreemarkerUtil.analysisTemplate(templatePath, templateName, filePath,
				map);
	}

	public String readTemplate() {
		try {
			String templatePath = "F:\\workspace\\iframe\\WebContent\\template\\html.ftl";
			FileInputStream fis = new FileInputStream(new File(templatePath));
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			byte[] data = new byte[fis.available()];
			int n = isr.read();
			fis.close();
			request.setAttribute("content", new String(data, 0, n));
			return "";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
