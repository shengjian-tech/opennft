

<#assign myParentDir="service.impl">
package ${basepackage}.${myParentDir};
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import ${basepackage}.entity.${className};
import ${basepackage}.service.I${className}Service;
import org.springframework.stereotype.Service;


import java.util.List;
<#include "/copyright_class.include" >

@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends BaseSpringrainServiceImpl implements I${className}Service {

   
    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    ${className} ${classNameLower}=(${className}) entity;
	    return super.save(${classNameLower}).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		${className} ${classNameLower}=(${className}) entity;
		return super.update(${classNameLower});
    }
	
    @Override
	public ${className} find${className}ById(String id) throws Exception{
		return super.findById(id,${className}.class);
	}

}
