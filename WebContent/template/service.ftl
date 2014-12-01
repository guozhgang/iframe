package ${servicePackage};


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${daoPackage}.${daoName};
import ${entityPackage}.${entityName};
import java.util.ArrayList;
import java.util.List;

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
	public List<${entityName}> list(${entityName} ${modelName}, int start, int limit) {
		StringBuilder hql = new StringBuilder("from ${entityName} i where 1 = 1 ");
		return ${daoModelName}.list(hql.toString(), start, limit);
	}
	public int count() {
		return ${daoModelName}.count();
	}
}
