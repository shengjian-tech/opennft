package net.shengjian.system.api.vo;

import net.shengjian.system.entity.Org;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @descriptions: 查询用户界面的部门树
 * @author: YSK
 * @date: 2021/5/17 15:24
 * @version: 1.0
 */
public class OrgTreeVO {
    private String title;
    private String key;
    private List<OrgTreeVO> children;

    public OrgTreeVO() {
    }

    public OrgTreeVO(Org org) {
        this.title = org.getName();
        this.key = org.getId();
    }

    /**
     * 对象类型转换orgTree转orgTreeVo
     *
     * @param orgList
     * @return
     */
    public static List<OrgTreeVO> orgTreeConvertOrgTreeVO(List<Org> orgList) {
        List<OrgTreeVO> orgTreeVOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(orgList)) {
            return orgTreeVOS;
        }
        for (Org org : orgList) {
            OrgTreeVO orgTreeVO = new OrgTreeVO(org);
            List<Org> children = org.getChildren();
            if (CollectionUtils.isNotEmpty(children)) {
                List<OrgTreeVO> childrenVO = orgTreeConvertOrgTreeVO(children);
                orgTreeVO.setChildren(childrenVO);
            }
            orgTreeVOS.add(orgTreeVO);
        }
        return orgTreeVOS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<OrgTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<OrgTreeVO> children) {
        this.children = children;
    }
}
