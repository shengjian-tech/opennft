package net.shengjian.system.api;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.system.api.vo.OrgTreeVO;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.DicData;
import net.shengjian.system.entity.Org;
import net.shengjian.system.service.IDicDataService;
import net.shengjian.system.service.IOrgService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 部门信息模块
 *
 * @author springrain<Auto generate>
 * @version 2019-07-27 16:10:11
 */
@RestController
@RequestMapping(value = "/api/system/org")
public class OrgController extends BaseController {
    @Resource
    private IOrgService orgService;

    @Resource
    private IDicDataService dicDataService;

    /**
     * 部门列表数据，分页查询，page.data中可封装查询条件
     *
     * @param page 对象参数
     * @return 分页数据
     * @throws Exception 异常
     */
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ReturnDatas<List<Org>> lists(@RequestBody Page<Org> page) {
        ReturnDatas<List<Org>> returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询
        List<Org> datas = null;
        try {
            Org org = Optional.ofNullable(page.getData()).orElse(new Org());
            datas = orgService.findOrgList(org, page);
            orgService.orgTypeName(datas);
            //datas = orgService.listOrg2Tree(datas);
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(e.toString());
            return returnObject;
        }
        // returnObject.setQueryBean(page.getData());
        returnObject.setPage(page);
        returnObject.setResult(datas);
        return returnObject;
    }

    /**
     * 所有部门列表数据，返回树结构(不分页),(是否有效：是)
     *
     * @return 所有数据，不分页
     * @throws Exception 异常
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnDatas<List<Org>> list() throws Exception {
        ReturnDatas<List<Org>> returnObject = ReturnDatas.getSuccessReturnDatas();
        // ==构造分页请求
        // Page page = newPage(request);
        // ==执行分页查询

        List<Org> datas = orgService.findTreeOrg();
        if (CollectionUtils.isNotEmpty(datas)) {
            returnObject.setResult(datas);
        } else {
            returnObject.setResult(new ArrayList<>());
        }
        return returnObject;
    }

    /**
     * 查看部门
     *
     * @param id 部门id
     */
    @RequestMapping(value = "/look", method = RequestMethod.POST)
    public ReturnDatas<Org> look(String id) throws Exception {
        ReturnDatas<Org> returnObject = ReturnDatas.getSuccessReturnDatas();

        if (StringUtils.isNotBlank(id)) {
            Org org = orgService.findOrgById(id);
            returnObject.setResult(org);
        } else {
            returnObject.setStatus(ReturnDatas.ERROR);
        }
        return returnObject;

    }

    /**
     * 保存部门
     *
     * @param org 部门对象
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReturnDatas<Org> save(@RequestBody Org org) {
        ReturnDatas<Org> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            orgService.saveOrg(org);

            returnObject.setResult(org);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }

    /**
     * 修改部门
     *
     * @param org 部门对象
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ReturnDatas<Org> update(@RequestBody Org org) {
        ReturnDatas<Org> returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        try {

            String id = org.getId();
            if (StringUtils.isBlank(id)) {
                return ReturnDatas.getErrorReturnDatas(MessageUtils.UPDATE_NULL_ERROR);
            }
            orgService.updateOrg(org);

            returnObject.setResult(org);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        return returnObject;

    }

    /**
     * 删除部门
     *
     * @param id 部门id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<Org> delete(String id) throws Exception {
        // 执行删除
        try {
            if (StringUtils.isNotBlank(id)) {
                orgService.deleteOrgById(id);

                return new ReturnDatas<>(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas<>(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

    /**
     * 查询部门树形结构
     */
    @PostMapping(value = "treeselect")
    public ReturnDatas<List<OrgTreeVO>> treeSelect() {
        ReturnDatas<List<OrgTreeVO>> returnDatas = ReturnDatas.getSuccessReturnDatas();
        try {
            List<Org> treeOrg = orgService.findTreeOrg();
            List<OrgTreeVO> orgTreeVOS = OrgTreeVO.orgTreeConvertOrgTreeVO(treeOrg);
            returnDatas.setResult(orgTreeVOS);
            returnDatas.setStatus(ReturnDatas.SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDatas.setStatus(ReturnDatas.ERROR);
            returnDatas.setMessage("查询失败");
        }
        return returnDatas;
    }

    /**
     * 部门类型数据，新增部门时选择的部门类型
     *
     * @return
     */
    @PostMapping("/orgTypeList")
    public ReturnDatas<List<DicData>> orgTypeList() {
        ReturnDatas<List<DicData>> returnDatas = new ReturnDatas<>();
        try {
            List<DicData> bumenleixing = dicDataService.findDicDataListByPid("bumenleixing");
            returnDatas.setResult(bumenleixing);
            returnDatas.setStatus(ReturnDatas.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnDatas<>(ReturnDatas.ERROR, e.getMessage());
        }
        return returnDatas;
    }
}
