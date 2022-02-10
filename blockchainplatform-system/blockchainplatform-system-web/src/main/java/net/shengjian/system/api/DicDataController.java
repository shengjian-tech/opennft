package net.shengjian.system.api;

import net.shengjian.frame.util.ExcelUtils;
import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.ReturnDatas;
import net.shengjian.frame.util.property.MessageUtils;
import net.shengjian.system.base.BaseController;
import net.shengjian.system.entity.DicData;
import net.shengjian.system.service.IDicDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典管理模块
 *
 * @author 9iu.dicData<Auto generate>
 * @version 2013-07-31 15:56:45
 */
@RestController
@RequestMapping(value = "/api/system/dicdata")
public class DicDataController extends BaseController {
    @Resource
    private IDicDataService dicDataService;

    @Value("${staticdir}")
    private String path;

    /**
     * 查询所有字典类型信息
     *
     * @param page 传入的json对象参数,可封装分页和查询条件
     * @return 根据条件查询出来的分页对象
     */
    @PostMapping("/type/list")
    public ReturnDatas<List<DicData>> list(@RequestBody Page<DicData> page) {
        ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
        if (ObjectUtils.isEmpty(page.getData())) {
            page.setData(new DicData());
        }

        List<DicData> datas = null;
        try {
            datas = dicDataService.findTree(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("查询失败");
        }
        returnObject.setResult(datas);
        returnObject.setPage(page);
        return returnObject;
    }

    /**
     * 导出字典类型（父级）
     *
     * @param page
     * @param response
     */
    @PostMapping("/type/list/export")
    public void export(@RequestBody Page<DicData> page, HttpServletResponse response) {
        try {
            List<DicData> datas = dicDataService.findAllRootList(page);
            List<String[]> title = new ArrayList<>();

            String[] name = new String[2];
            name[0] = "name";
            name[1] = "字典名称";
            String[] code = new String[2];
            code[0] = "code";
            code[1] = "编码";
            String[] pid = new String[2];
            pid[0] = "pid";
            pid[1] = "父id";
            String[] typekey = new String[2];
            typekey[0] = "typekey";
            typekey[1] = "字典类型";
            String[] remark = new String[2];
            remark[0] = "remark";
            remark[1] = "描述";
            String[] createTime = new String[2];
            createTime[0] = "createTime";
            createTime[1] = "创建时间";

            title.add(name);
            title.add(code);
            title.add(pid);
            title.add(typekey);
            title.add(remark);
            title.add(createTime);

            File file = ExcelUtils.dataToExcel(datas, title, path + "temp/");

            super.downFile(response, file, "字典类型.xlsx", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 更新操作
     *
     * @param dicData 传入的更新对象
     */
    @PostMapping(value = "/type/update")
    public ReturnDatas<String> typeUpdate(@RequestBody DicData dicData) {
        ReturnDatas<String> returnObject = ReturnDatas.getSuccessReturnDatas();

        String dicDataId = null;
        try {
            if (StringUtils.isBlank(dicData.getId())) {
                // 保存新的字典类型
                dicDataId = dicDataService.saveDicDataType(dicData);
            } else {
                // 修改字典类型
                dicDataId = dicData.getId();
                dicDataService.updateDic(dicData);
            }
            returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.UPDATE_ERROR);
        }
        returnObject.setResult(dicDataId);
        return returnObject;
    }

    /**
     * 根据字典id查询字典对象
     *
     * @param dicId 字典id
     * @return 字典数据
     */
    @PostMapping("/type/look")
    @ResponseBody
    public ReturnDatas<DicData> listjson(String dicId) {
        ReturnDatas<DicData> returnObject = ReturnDatas.getSuccessReturnDatas();
        if (StringUtils.isBlank(dicId)) {
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("参数错误");
            return returnObject;
        }
        DicData dicData = null;
        try {
            dicData = dicDataService.findDicDataById(dicId);
        } catch (Exception e) {
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("查询失败");
        }
        returnObject.setResult(dicData);
        return returnObject;
    }

    /**
     * 删除字典，
     *
     * @param idList 删除的字典id集合 [1,2,3]
     * @return 执行状态 字典中包含子元素
     */
    @PostMapping("/type/delete/more")
    @ResponseBody
    public ReturnDatas<DicData> deleteType(@RequestBody List<String> idList) {
        ReturnDatas<DicData> returnObject = ReturnDatas.getSuccessReturnDatas();
        try {
            dicDataService.deleteParentDicDataById(idList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("删除失败,请检查当前字典是否有子级元素");
        }
        return returnObject;
    }

    /**
     * 查询当前字典下的子集
     *
     * @param dicId 字典id
     * @return 列表数据
     */
    @PostMapping("/data/type")
    @ResponseBody
    public ReturnDatas<List<DicData>> dataTypelistjson(String dicId) {
        ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
        if (StringUtils.isBlank(dicId)) {
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("参数错误");
            return returnObject;
        }
        List<DicData> dicData = null;
        try {
            dicData = dicDataService.findDicDataListByPid(dicId);
        } catch (Exception e) {
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("查询失败");
        }
        returnObject.setResult(dicData);
        return returnObject;
    }

    /**
     * 查询当前字典id下的子集,分页展示
     * {
     * "pageNo": "1",
     * "pageSize": "20",
     * "data": {
     * "pid": ""必填
     * }
     * }
     *
     * @param page 分页条件
     * @return 分页数据
     */
    @PostMapping("/data/list")
    @ResponseBody
    public ReturnDatas<List<DicData>> dataList(@RequestBody Page<DicData> page) {
        ReturnDatas<List<DicData>> returnObject = ReturnDatas.getSuccessReturnDatas();
        String pid = page.getData().getPid();
        List<DicData> dicDataList = null;
        try {
            dicDataList = dicDataService.findListByPid(pid, page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage("查询失败");
        }
        returnObject.setResult(dicDataList);
        returnObject.setPage(page);
        return returnObject;
    }

    /**
     * 导出字典类型数据（子级）
     *
     * @param page
     * @param response
     */
    @PostMapping("/data/list/export")
    public void dataExport(@RequestBody Page<DicData> page, HttpServletResponse response) {
        try {
            String _pid = page.getData().getPid();
            List<DicData> datas = dicDataService.findListByPid(_pid, page);
            List<String[]> title = new ArrayList<>();

            String[] code = new String[2];
            code[0] = "code";
            code[1] = "编码";
            String[] name = new String[2];
            name[0] = "name";
            name[1] = "字典名称";
            String[] pid = new String[2];
            pid[0] = "pid";
            pid[1] = "父id";
            String[] typekey = new String[2];
            typekey[0] = "typekey";
            typekey[1] = "字典类型";
            String[] active = new String[2];
            active[0] = "active";
            active[1] = "状态";
            String[] remark = new String[2];
            remark[0] = "remark";
            remark[1] = "描述";
            String[] val = new String[2];
            val[0] = "val";
            val[1] = "字典键值";
            String[] createTime = new String[2];
            createTime[0] = "createTime";
            createTime[1] = "创建时间";

            title.add(code);
            title.add(name);
            title.add(pid);
            title.add(typekey);
            title.add(active);
            title.add(remark);
            title.add(val);
            title.add(createTime);

            File file = ExcelUtils.dataToExcel(datas, title, path + "temp/");

            super.downFile(response, file, "字典类型.xlsx", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存 操作,返回json格式数据
     */
    @RequestMapping(value = "/type/save", method = RequestMethod.POST)
    public ReturnDatas<DicData> save(@RequestBody DicData dicData) {
        ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
        returnObject.setMessage(MessageUtils.SAVE_SUCCESS);
        try {

            java.lang.String id = dicData.getId();
            if (StringUtils.isBlank(id)) {
                dicData.setId(null);
            }
            dicDataService.saveDic(dicData);

            returnObject.setResult(dicData);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnObject.setStatus(ReturnDatas.ERROR);
            returnObject.setMessage(MessageUtils.SAVE_ERROR);
        }
        return returnObject;

    }

    /**
     * 删除操作
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReturnDatas<DicData> delete(String id) throws Exception {
        // 执行删除
        try {
            if (StringUtils.isNotBlank(id)) {
                dicDataService.deleteById(id, DicData.class);
                return new ReturnDatas(ReturnDatas.SUCCESS, MessageUtils.DELETE_SUCCESS);
            } else {
                return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_NULL_ERROR);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ReturnDatas(ReturnDatas.ERROR, MessageUtils.DELETE_ERROR);
    }

}
