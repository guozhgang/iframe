package com.skss.iframe.template.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * freemarker生成文件工具类
 * @author Administrator
 *
 */
public class FreemarkerUtil {
	/**
	 * 解析模板类
	 * @param templatePath  模板文件的路径
	 * @param templateName  模板的名称
	 * @param fileName   要生成的HTML文件
	 * @param root  生成HTML所需数据
	 */
	public static void analysisTemplate(String templatePath, String templateName, String filePath, Map<?, ?> root) throws Exception{
		Configuration config = new Configuration();
		config.setDirectoryForTemplateLoading(new File(templatePath));
		config.setObjectWrapper(new DefaultObjectWrapper());
		Template template = config.getTemplate(templateName, "utf-8");
		FileOutputStream fos = new FileOutputStream(filePath);
		Writer out = new OutputStreamWriter(fos, "utf-8");
		template.process(root, out);
		out.flush();
		out.close();
	}
}
