package net.shengjian.weixin.web;

import net.shengjian.weixin.sdk.common.ApiResult;
import net.shengjian.weixin.sdk.common.wxconfig.IWxMpConfig;
import net.shengjian.weixin.sdk.open.SnsApi;
import net.shengjian.weixin.service.IWxMpConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/mp/mpautologin/{siteId}")
public class WxMpAutoLoginController {

    private static final Logger logger = LoggerFactory.getLogger(WxMpAutoLoginController.class);


    // @Resource
    // IWxMpService wxMpService;
    @Resource
    IWxMpConfigService wxMpConfigService;

    // @Resource
    // IWxMpUserService wxMpUserService;


    /**
     * 跳转到微信认证页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/oauth2")
    public String oauth2(@PathVariable String siteId, @RequestParam String url, HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(siteId)) {
            return null;
        }

        IWxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
        //String ctxpath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        String ctxpath = "https" + "://" + request.getServerName() + request.getContextPath();
        String _url = ctxpath + "/mp/mpautologin/" + siteId + "/callback?url=" + url;

        //String oauthUrl = wxMpService.oauth2buildAuthorizationUrl(wxmpconfig,_url, WxConsts.OAUTH2_SCOPE_BASE, null);

        String oauthUrl = SnsApi.getAuthorizeURL(wxmpconfig, _url, true);


        return "redirect:" + oauthUrl;
        // return "";
    }

    /**
     * 获取微信用户信息（openid）
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/callback")
    public String callback(@PathVariable String siteId, @RequestParam String url, @RequestParam String code) throws Exception {
        //WxMpUser wxMpUser = new WxMpUser();
        //String url = payRequest.getParameter("url");
        //String code = payRequest.getParameter("code");

        if (StringUtils.isBlank(url) || StringUtils.isBlank(code) || StringUtils.isBlank(siteId)) {
            return null;
        }


        IWxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(siteId);
        try {
            // 获取OpenId
            //WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(wxmpconfig, code);
//			 wxMpUser=wxMpService.oauth2getUserInfo(wxmpconfig,accessToken,"zh_CN");
//			WxMpUser wxMpUser = wxMpUserService.userInfo(wxmpconfig, accessToken.getOpenId());
            //payRequest.getSession().setAttribute("openId", accessToken.getOpenId());
            //payRequest.getSession().setAttribute("unionId", accessToken.getUnionId());

            ApiResult accessTokenResult = SnsApi.getAccessToken(wxmpconfig, code);
            String openId = accessTokenResult.getOpenId();
            url = StringUtils.replace(url, "---", "&");
            if (url.contains("?")) {
                url = url + "&openId=" + openId;
            } else {
                url = url + "?openId=" + openId;
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        //return BaseController.redirect + url;
        return "redirect:" + url;
    }

    /**
     private void autoLogin(HttpServletRequest req, HttpServletResponse rep, User user) {
     ShiroUser shiroUser = new ShiroUser();
     shiroUser.setAccount(user.getAccount());
     shiroUser.setEmail(user.getEmail());
     shiroUser.setId(user.getId());
     shiroUser.setUserName(user.getUserName());
     shiroUser.setSex(user.getSex());
     shiroUser.setUserType(user.getUserType());
     SimplePrincipalCollection principals = new SimplePrincipalCollection(
     shiroUser, GlobalStatic.authorizingRealmName);
     WebSubject.Builder builder = new WebSubject.Builder(req, rep);
     builder.principals(principals);
     builder.authenticated(true);
     WebSubject subject = builder.buildWebSubject();
     ThreadContext.bind(subject);
     }
     **/
}
