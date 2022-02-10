package net.shengjian.system.interceptor;

import net.shengjian.frame.util.*;
import net.shengjian.rpc.sessionuser.SessionUser;
import net.shengjian.rpc.sessionuser.UserVO;
import net.shengjian.system.entity.Menu;
import net.shengjian.system.entity.Role;
import net.shengjian.system.service.IRoleService;
import net.shengjian.system.service.IUserRoleMenuService;
import net.shengjian.system.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


@Component("jwtInterceptor")
public class JwtInterceptor implements HandlerInterceptor {
    PathMatcher matcher = new AntPathMatcher();
    @Resource
    private IUserRoleMenuService userRoleMenuService;
    @Resource
    private IUserService userService;
    // @Resource
    //private IMenuService menuService;
    @Resource
    private IRoleService roleService;

    //允许跨域,目前拦截 /*
    @Bean("corsFilter")
    public CorsFilter corsFilter() {
        //跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 允许任何头
        corsConfiguration.addAllowedMethod("*"); // 允许任何方法（post、get等）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 对接口配置跨域设置
        return new CorsFilter(source);
    }

    /**
     * 预处理回调方法，实现处理器的预处理（如检查登陆），第三个参数为响应的处理器，自定义Controller
     * 返回值：true表示继续流程（如调用下一个拦截器或处理器）；false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        response.setStatus(HttpStatus.FORBIDDEN.value());
        // 获取jwtToken
        String jwtToken = request.getHeader(GlobalStatic.jwtTokenKey);

        // RSA 公钥解密
        jwtToken = SecUtils.decoderByRSAPublicKey(jwtToken);

        //超时判断
        Date expireTime = JwtUtils.getExpireDate(jwtToken);
        if (expireTime == null || expireTime.getTime() < System.currentTimeMillis()) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().print(JsonUtils.writeValueAsString(new ReturnDatas(ReturnDatas.TOKEN_TIME_OUT, "用戶信息已过期！请重新登录！")));
            return false;
        }

        //用户信息判断
        String userId = JwtUtils.getUserId(jwtToken);
        Integer userType = JwtUtils.getUserType(jwtToken);
        if (StringUtils.isBlank(userId)) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().print(JsonUtils.writeValueAsString(ReturnDatas.getErrorReturnDatas("用户ID过期！")));
            return false;
        }

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        int i = uri.indexOf(contextPath);
        if (i > -1) {
            uri = uri.substring(i + contextPath.length());
        }
        if (StringUtils.isBlank(uri)) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().print(JsonUtils.writeValueAsString(ReturnDatas.getErrorReturnDatas("非法请求！")));
            return false;
        }


        boolean isUserDefaultUrl = false;

        for (String patternPath : GlobalStatic.userDefaultUrl) {
            if (matcher.match(patternPath, uri) || userType.intValue() == 0) { //符合正则表达式
                isUserDefaultUrl = true;
                break;
            }

        }

        if (isUserDefaultUrl) {//登录用户 有权限
            UserVO userVO = userService.findUserVOByUserId(userId);
            SessionUser.sessionUserLocal.set(userVO);
            response.setStatus(HttpStatus.OK.value());
            return true;
        }


        // 建议不再传递menuId和roleId,权限的URL不能重复即可,通过访问的url,查到menuId和roleId.
        // 前后端分离之后,后台的菜单实际只管理了数据,并不管理前端的菜单层次结构.

        // 请求的菜单Id,可以通过url地址反查menuId或者根据/api/user/menu返回的数据再遍历一次,获取menuId
        // String menuId = request.getHeader("menuId");
        // 请求的角色Id,可以通过url地址反查menuId或者根据/api/user/menu返回的数据再遍历一次,获取roleId
        // String roleId = request.getHeader("roleId");


        if (StringUtils.isBlank(jwtToken)) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().print(JsonUtils.writeValueAsString(ReturnDatas.getErrorReturnDatas("请求头中请携带jwtToken！")));
            return false;
        }

        // Menu menu = menuService.findMenuById(menuId);

        /*
        if(menu == null||(!uri.equalsIgnoreCase(menu.getPageurl()))){
            return false;
        }


        Role role = roleService.findRoleById(roleId);
        if(role==null||(!(roleId.equalsIgnoreCase(role.getId())))){
            return false;
        }
        */


        // 业务数据表 必须要有 orgId 和 createUserId 这两个字段,用于标识归属部门和创建人,用于权限过滤!!!!

        // 角色关联部门实现数据权限,角色指定 roleOrgType <0自己的数据,1所在部门,2所在部门及子部门数据,3.自定义部门数据,4.全部数据>.
        // 0就是类似员工,1和2 是根据使用者自身数据进行数据授权,适合公用全局属性,例如专员只能查看他所在的部门的数据,虽然他不是部门主管.

        // 角色指定 privateOrg 角色的部门是否私有,0否,1是,默认0.当角色私有时,菜单只使用此角色的部门权限,不再扩散到全局角色权限,用于设置特殊的菜单权限.
        // 公共权限时部门主管有所管理部门的数据全权限,无论角色是否分配.私有部门权限时,严格按照配置的数据执行,部门主管可能没有部门权限.
        // privateOrg私有权限和公共权限分别处理,不能交叉.处理公共权限时会跳过私有权限
        //  privateOrg 和 roleOrgType 交叉情况,比较复杂,场景也很少,暂时未细测,如果是私有的所在部门权限,应该只能查看所在部门的数据,也不会扩散权限.

        // 角色关联人员,部门,菜单,作为整个权限设计的中心枢纽.
        // 角色都有归属部门,其部门主管或上级主管才可以修改角色属性,其他使用人员只能往角色里添加人员,
        // 不能选择部门或则其他操作,只能添加人员,不然存在提权风险,例如 员工 角色下有1000人, 如果给 员工 角色设置了部门权限,那这1000人都能看到部门权限了.
        // 角色 shareRole 设置共享的角色可以被下级部门直接查看到,并添加人员.同样 也是只能添加人员.

        // 1.根据访问的url,通过userRoleMenuService.findMenuByUserId(userId)查询对应的menuId和roleId.需要确保url地址唯一,多个菜单url相同可以使用软跳转,暂时不处理.
        // 2.根据userId查询缓存的List<Role>,验证是否包含这个roleId
        // 3.根据roleId查询缓存的List<Menu>,验证是否包含这个menuId.
        // 4.查看roleId如果是私有权限,UserVo 就设置 privateOrgRoleId,业务调用SessionUser.getPrivateOrgRoleId获取私有的roleId,
        // 然后再调用IUserRoleOrgService.wrapOrgIdFinderByPrivateOrgRoleId(String roleId,String userId) 获取权限的 Finder
        // 5.如果是公共权限,这里不做处理,业务方法调用 IUserRoleOrgService.wrapOrgIdFinderByUserId(String userId) 获取权限的Finder
        // 6.建议直接使用IUserRoleOrgService.wrapOrgIdFinderByFinder(Finder finder,String orgIdColumn,String createUserIdColumn)方法,获取当前登陆人的权限Finder

        // 注意:在返回前端菜单权限时,要包含menuId和roleId,私有privateOrg的roleId优先,如果同一个menuId存在多个定制roleId冲突,按照role的排序,同一个menuId只保留一个roleId.

        // 注意:缓存的清理,使用缓存java组装用户权限的树形结构.


        List<Menu> menus = userRoleMenuService.findMenuByUserId(userId);

        if (CollectionUtils.isEmpty(menus)) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().print(JsonUtils.writeValueAsString(ReturnDatas.getErrorReturnDatas("该用户无操作权限！")));
            return false;
        }

        //用户是否有uri的权限.循环遍历用户有权限的菜单URL,因为pageUrl是唯一的,可以取出menuId和roleId

        boolean qx = false;
        String roleId = null;
        for (Menu m : menus) {
            //如果有访问菜单的权限,赋值roleId
            if (uri.equalsIgnoreCase(m.getPageurl()) || userType.intValue() == 0) {
                roleId = m.getRoleId();
                qx = true;
                break;
            }

        }

        if (!qx) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(JsonUtils.writeValueAsString(ReturnDatas.getErrorReturnDatas("该用户无操作权限！")));
            return false;
        }
        Role role = roleService.findRoleById(roleId);
        if (role == null) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().print(JsonUtils.writeValueAsString(ReturnDatas.getErrorReturnDatas("该用户无角色！")));
            return false;
        }
        //设置userVO
        UserVO userVO = userService.findUserVOByUserId(userId);
        // 如果是私有的部门权限,setPrivateOrgRoleId,业务调用SessionUser.getPrivateOrgRoleId,如果不是NULL,就调用IUserRoleOrgService.wrapOrgIdFinderByPrivateOrgRoleId(String roleId,String userId) 获取权限的 Finder
        if (role.getPrivateOrg() == 1) {
            userVO.setPrivateOrgRoleId(role.getId());
        }

        SessionUser.sessionUserLocal.set(userVO);


        // UserVO userVO = userService.findUserVOByUserId(userId);

        //  SessionUser.sessionUserLocal.set(userVO);

        response.setStatus(HttpStatus.OK.value());
        return true;

    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SessionUser.sessionUserLocal.remove();
    }
}
