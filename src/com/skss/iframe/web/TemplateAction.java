package com.skss.iframe.web;

import java.net.URL;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.skss.iframe.template.util.FreemarkerUtil;

public class TemplateAction extends ActionSupport{
	private String templateName = "dao";
	public void deployment() {
		
		String templatePath = ServletActionContext.getServletContext().getRealPath("/template");
		System.out.println(System.getProperty("user.dir"));
		String temp = templateName;
		String filePath = templatePath + "/" + templateName + ".html";
		templateName = "/" + templateName + ".ftl";
		//生成HTML文件
		try {
			FreemarkerUtil.analysisTemplate(templatePath, templateName, filePath, new HashMap<String, String>());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
