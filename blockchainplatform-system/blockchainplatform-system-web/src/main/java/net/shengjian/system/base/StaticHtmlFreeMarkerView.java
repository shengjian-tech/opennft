package net.shengjian.system.base;

import net.shengjian.frame.util.FileUtils;
import net.shengjian.frame.util.GlobalStatic;
import net.shengjian.frame.util.InputSafeUtils;
import net.shengjian.frame.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

//@Component("staticHtmlFreeMarkerView")
@Deprecated
public class StaticHtmlFreeMarkerView extends FreeMarkerView {

    private CacheManager cacheManager = null;

    /*
     * public StaticHtmlFreeMarkerView(){ if(cacheManager==null){
     * cacheManager=(CacheManager) SpringUtils.getBean("cacheManager"); } }
     */

    @PostConstruct
    public void initCacheManager() {
        cacheManager = (CacheManager) SpringUtils.getBean("cacheManager");
    }

    /**
     * Process the model map by merging it with the FreeMarker template. Output is
     * directed to the servlet response.
     * <p>
     * This method can be overridden if custom behavior is needed.
     */
    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpServletRequest req = request;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        int i = uri.indexOf(contextPath);
        if (i > -1) {
            uri = uri.substring(i + contextPath.length());
        }
        String siteId = InputSafeUtils.substringByURI(req.getRequestURI(), "/s_");
        if (StringUtils.isBlank(siteId)) {// URL中没有siteId,从cookie中取值
            //siteId = CookieUtils.getCookieValue(req, GlobalStatic.springraindefaultSiteId);
        }

        Cache cache = null;
        if (cacheManager == null) {
            cacheManager = (CacheManager) SpringUtils.getBean("cacheManager");

        }

        // Expose model to JSP tags (as request attributes).
        exposeModelAsRequestAttributes(model, request);
        // Expose all standard FreeMarker hash models.
        //SimpleHash fmModel = buildTemplateModel(model, request, response);

        if (logger.isDebugEnabled()) {
            logger.debug("Rendering FreeMarker template [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");
        }
        if (StringUtils.isBlank(siteId) || StringUtils.isBlank(uri)) {
            //processTemplate(template, fmModel, response);
            return;
        }

        cache = cacheManager.getCache(siteId);

        // cache key,可以根据URI从数据库进行查询资源Id
        String htmlCacheKey = siteId + "_" + uri;

        String htmlPath = cache.get(htmlCacheKey, String.class);

        if (StringUtils.isBlank(htmlPath) || htmlPath.equals("error")) {// 缓存中不存在
            //processTemplate(template, fmModel, response);
            return;
        }

        File htmlFile = new File(GlobalStatic.staticHtmlDir + htmlPath);
        if (htmlFile.exists()) {
            response.setContentType("text/html;charset=" + GlobalStatic.defaultCharset);
            response.setCharacterEncoding(GlobalStatic.defaultCharset);
            PrintWriter writer = response.getWriter();
            FileUtils.readIOFromFile(writer, htmlFile);
        } else {
            //createHtml(cache, htmlCacheKey, htmlFile, fmModel, model, template, response);
        }

    }

    /**
     * 生成静态文件
     *
     * @param htmlFile
     * @param htmlCacheKey
     * @param fmModel
     * @param model
     * @param template
     * @param response
     * @throws Exception
     */
    //
//	private void createHtml(Cache cache, String htmlCacheKey, File htmlFile, SimpleHash fmModel,
//			Map<String, Object> model, Template template, HttpServletResponse response) throws Exception {
//		String htmlPath = cache.get(htmlCacheKey, String.class);
//		if (StringUtils.isBlank(htmlPath) || "error".equals(htmlPath)) {// 缓存中不存在
//			processTemplate(template, fmModel, response);
//			return;
//		}
//		// 生成文件 开始期间
//		cache.put(htmlCacheKey, "error");
//		if (htmlFile.exists()) {
//			htmlFile.delete();
//		} else {
//			File parent = htmlFile.getParentFile();
//			if (!parent.exists()) {
//				parent.mkdirs();
//			}
//		}
//
//		boolean createNewFile = htmlFile.createNewFile();
//		if (!createNewFile) {
//			return;
//		}
//
//		Writer out = new BufferedWriter(
//				new OutputStreamWriter(new FileOutputStream(htmlFile), GlobalStatic.defaultCharset));
//		template.process(model, out);
//		out.flush();
//		out.close();
//		// 生成文件 结束
//		cache.put(htmlCacheKey, htmlPath);
//		// 输出文件
//		response.setContentType("text/html;charset=" + GlobalStatic.defaultCharset);
//		response.setCharacterEncoding(GlobalStatic.defaultCharset);
//
//		PrintWriter writer = response.getWriter();
//		FileUtils.readIOFromFile(writer, htmlFile);
//
//	}
}