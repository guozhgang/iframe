package ${actionPackage};

import javax.annotation.Resource;
import com.skss.iframe.web.ActionUtil;
import ${entityPackage}.${entityName};
import ${servicePackage}.${serviceName};

public class ${className} extends ActionUtil<${entityName}, ${className}>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ${serviceName} ${serviceModelName};
	public void save() {
		try {
			${serviceModelName}.save${entityName}(model);
			this.sendSuccess();
		} catch (Exception e) {
			// TODO: handle exception
			this.sendMessage(false, "参数错误");
		}
	}
	public void remove() {
		${serviceModelName}.remove(model);
	}
}
