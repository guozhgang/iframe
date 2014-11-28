package ${daoPackage};

import org.springframework.stereotype.Repository;

import ${entityPackage}.${entityName};
import com.skss.iframe.dao.BaseDao;

@Repository
public class ${className} extends ${base}<${entityName}>{
	public void save${entityName}(${entityName} ${modelName}) {
		this.saveOrUpdate(${modelName});
	}
	public void remove(${entityName} ${modelName}) {
		this.delete(${modelName});
	}
}
