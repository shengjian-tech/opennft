package net.shengjian.system.service.impl;

import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.system.service.IStaticHtmlService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * TODO 在此加入类描述
 *
 * @author jiagou.com<Auto generate>
 * @version 2013-07-29 11:36:44
 */
//@Service("staticHtmlService")
@Deprecated
public class StaticHtmlServiceImpl extends BaseSpringrainServiceImpl implements IStaticHtmlService {

    @Override
    public String findHtmlPathByURI(String siteId, String uri) throws Exception {

        if (StringUtils.isBlank(siteId) || StringUtils.isBlank(uri)) {
            return null;
        }
        String cacheKey = siteId + "_" + uri;
        String byCache = super.getByCache(siteId, cacheKey, String.class);
        if (StringUtils.isNotBlank(byCache)) {
            return byCache;
        }

		/*
		Finder finder = Finder.getSelectFinder(CmsLink.class, "id")
				.append(" WHERE statichtml=1 and siteId=:siteId and link=:link ");
		finder.setParam("siteId", siteId).setParam("link", uri);

		String linkId = super.queryForObject(finder, String.class);

		if (StringUtils.isBlank(linkId)) {
			return null;
		}
		String filepath = siteId + "/" + linkId + GlobalStatic.suffix;
       */
        String filepath = siteId + "/" + GlobalStatic.suffix;

        super.putByCache(siteId, cacheKey, filepath);

        File htmlFile = new File(GlobalStatic.staticHtmlDir + filepath);
        if (htmlFile.exists()) {
            htmlFile.delete();
        }

        return filepath;
    }

}
