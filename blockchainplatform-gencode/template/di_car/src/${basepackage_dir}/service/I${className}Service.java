

<#assign myParentDir="service">
package ${basepackage}.${myParentDir};
  
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
import ${basepackage}.entity.${className};
import ${basepackage}.service.IBaseSpringrainService;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
<#include "/copyright_class.include" >
@RpcServiceAnnotation
public interface I${className}Service extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	${className} find${className}ById(String id) throws Exception;
	
	
	
}
