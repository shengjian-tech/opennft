package net.shengjian.system.api;

import net.shengjian.frame.entity.AuditLog;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.service.IAuditlogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * TODO 日志管理
 *
 * @author jiagou.com<Auto generate>
 * @version 2013-08-02 12:14:50
 */
@Controller
@RequestMapping(value = "/system/auditlog")
public class AuditlogController extends BaseController {
    @Resource
    private IAuditlogService auditlogService;

    private String listurl = "/system/auditlog/auditlogList";

    /**
     * 列表数据,调用listjson方法,保证和app端数据统一
     *
     * @param request
     * @param model
     * @param auditlog
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model, AuditLog auditlog) throws Exception {
        ReturnDatas<List<AuditLog>> returnObject = listjson(request, model, auditlog);
        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        return listurl;
    }

    /**
     * json数据,为APP提供数据
     *
     * @param request
     * @param model
     * @param auditlog
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/json")
    @ResponseBody
    public ReturnDatas<List<AuditLog>> listjson(HttpServletRequest request, Model model, AuditLog auditlog) throws Exception {
        ReturnDatas<List<AuditLog>> returnObject = ReturnDatas.getSuccessReturnDatas();

        Page<?> page = newPage(request);
        page.setOrder("operationTime");
        page.setSort("desc");

        List<AuditLog> datas = auditlogService.queryForListByEntity(auditlog, page);
        //returnObject.setQueryBean(auditlog);
        returnObject.setPage(page);
        returnObject.setResult(datas);
        return returnObject;
    }

    /**
     * 导出Excle格式的数据
     *
     * @param request
     * @param response
     * @param model
     * @param auditlog
     * @throws Exception
     */
    @RequestMapping("/list/export")
    public void listexport(HttpServletRequest request, HttpServletResponse response, Model model, AuditLog auditlog)
            throws Exception {
        File file = null; //(null, listurl, page, AuditLog.class, auditlog, GlobalStatic.excelext,auditlogService);

        String fileName = "auditlog" + GlobalStatic.excelext;
        downFile(response, file, fileName, true);
        return;
    }

    /**
     * 查看操作,调用APP端lookjson方法
     */
    @RequestMapping(value = "/look")
    public String look(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReturnDatas<AuditLog> returnObject = lookjson(model, request, response);
        model.addAttribute(GlobalStatic.returnDatas, returnObject);
        return "/system/auditlog/auditlogLook";
    }

    /**
     * 查看的Json格式数据,为APP端提供数据
     */
    @RequestMapping(value = "/look/json")
    @ResponseBody
    public ReturnDatas<AuditLog> lookjson(Model model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ReturnDatas<AuditLog> returnObject = ReturnDatas.getSuccessReturnDatas();
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            AuditLog auditlog = auditlogService.findAuditlogById(id);
            returnObject.setResult(auditlog);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }

        return returnObject;

    }

}
