package ${daoPackage};

import org.springframework.stereotype.Repository;

import ${entityPackage}.${entityName};
import com.skss.iframe.dao.BaseDao;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ${className} extends ${base}<${entityName}>{
	public void save${entityName}(${entityName} ${modelName}) {
		this.saveOrUpdate(${modelName});
	}
	public void remove(${entityName} ${modelName}) {
		this.delete(${modelName});
	}
	public List<${entityName}> list(String hql, int start, int rows) {
		return this.findPaginationListByHQL(hql, start, rows);
	} 
	public int count() {
		return this.getCountByHql("select count(i) from ${entityName} i");
	}
}
