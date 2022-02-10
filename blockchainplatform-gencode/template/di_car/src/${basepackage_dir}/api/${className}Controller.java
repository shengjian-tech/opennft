
<#assign myParentDir="api">
package ${basepackage}.${myParentDir};
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
<#assign classNameLowerCase=className?lower_case>
<#assign from=basepackage?last_index_of(".")>
<#assign rootPagefloder=basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign pkJavaType=table.idColumn.javaType>
import ${basepackage}.entity.${className};
import ${basepackage}.service.I${className}Service;
import ${basepackage}.base.BaseController;

import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Arrays;

<#include "/copyright_class.include" >
@RestController
@RequestMapping(value="/api/${targetpackage}/${classNameLowerCase}", method = RequestMethod.POST)
public class ${className}Controller  extends BaseController {
	@Resource
	private I${className}Service ${classNameLower}Service;

	/**
	 * 列表数据
	 *
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ReturnDatas<${className}> list(@RequestBody Page<${className}> page)
			throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		// Page page = newPage(request);
		// ==执行分页查询
		List<${className}> datas=${classNameLower}Service.queryForListByEntity(page.getData(),page);
			//returnObject.setQueryBean(page.getData());
		returnObject.setPage(page);
		returnObject.setResult(datas);
		return returnObject;
	}

	/**
	 * 查看的Json格式数据
	 */
	@RequestMapping(value = "/look", method = RequestMethod.POST)   
	public ReturnDatas<${className}> look(${pkJavaType} id) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		
		if(StringUtils.isNotBlank(id)){
		  ${className} ${classNameLower} = ${classNameLower}Service.find${className}ById(id);
		   returnObject.setResult(${classNameLower});
		}else{
		   returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	/**
	 * 保存 操作,返回json格式数据
	 * 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)    
	public ReturnDatas<${className}> save(@RequestBody ${className} ${classNameLower}) {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
		try {
		
			${pkJavaType} ${table.pkColumn.columnNameFirstLower} =${classNameLower}.get${table.pkColumn.columnName}();
			if(StringUtils.isBlank(${table.pkColumn.columnNameFirstLower})){
			  ${classNameLower}.set${table.pkColumn.columnName}(null);
			}
			${classNameLower}Service.save(${classNameLower});

			returnObject.setResult(${classNameLower});
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.SAVE_ERROR);
		}
		return returnObject;
	
	}
		
	
	/**
	 * 修改 操作,返回json格式数据
	 * 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)    
	public ReturnDatas<${className}> update(@RequestBody ${className} ${classNameLower}) {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			${pkJavaType} ${table.pkColumn.columnNameFirstLower} =${classNameLower}.get${table.pkColumn.columnName}();
			if(StringUtils.isBlank(${table.pkColumn.columnNameFirstLower})){
			   return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
			}
			${classNameLower}Service.update(${classNameLower});

			returnObject.setResult(${classNameLower});
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)   
	public  ReturnDatas<${className}> delete(String id ) throws Exception {
			// 执行删除
		try {
		    if(StringUtils.isNotBlank(id)){
			    ${classNameLower}Service.deleteById(id,${className}.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_ERROR);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping(value = "/delete/more", method = RequestMethod.POST)
	public ReturnDatas deleteMore(@RequestBody ${pkJavaType}[] ids) {

		if (ids == null || ids.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_ERROR);
		}
		try {
			List<String> listIds = Arrays.asList(ids);
			${classNameLower}Service.deleteByIds(listIds,${className}.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_ERROR);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
			
	}

}
