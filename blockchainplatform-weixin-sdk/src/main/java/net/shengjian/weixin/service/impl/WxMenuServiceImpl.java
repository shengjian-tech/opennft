package net.shengjian.weixin.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.frame.util.Finder;
import net.shengjian.weixin.entity.WxMenu;
import net.shengjian.weixin.service.IWxMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2017-02-06 17:23:12
 */
@Service("wxMenuService")
public class WxMenuServiceImpl extends BaseSpringrainWeiXinServiceImpl implements IWxMenuService {


    @Override
    public String save(IBaseEntity entity) throws Exception {
        WxMenu cmsWxMenu = (WxMenu) entity;
        return super.save(cmsWxMenu).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        WxMenu cmsWxMenu = (WxMenu) entity;
        return super.update(cmsWxMenu);
    }

    @Override
    public WxMenu findWxMenuById(String id) throws Exception {
        return super.findById(id, WxMenu.class);
    }


    @Override
    public List<WxMenu> findParentMenuList(String siteId) throws Exception {
        Finder finder = Finder.getSelectFinder(WxMenu.class);
        finder.append(" where siteId=:siteId and pid is '' ");
        finder.setParam("siteId", siteId);
        return super.queryForList(finder, WxMenu.class);
    }

    @Override
    public List<WxMenu> findChildMenuListByPid(String pid) throws Exception {
        Finder finder = Finder.getSelectFinder(WxMenu.class);
        finder.append(" where 1=1");
        if (StringUtils.isNotBlank(pid)) {
            finder.append(" and pid=:pid").setParam("pid", pid);
        }
        List<WxMenu> list = super.queryForList(finder, WxMenu.class);
        return list;
    }


}
