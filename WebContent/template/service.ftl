package ${servicePackage};


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${daoPackage}.${daoName};
import ${entityPackage}.${entityName};

@Service
public class ${className} {
	@Resource
	private ${daoName} ${daoModelName};

	public void save${entityName}(${entityName} ${modelName}) {
		if (null == ${modelName}.getId() || "".equals(${modelName}.getId())) {
			${modelName}.setId(null);
		}
		${daoModelName}.save${entityName}(${modelName});
	}
	public void remove(${entityName} ${modelName}) {
		${daoModelName}.remove(${modelName});
	}
}
