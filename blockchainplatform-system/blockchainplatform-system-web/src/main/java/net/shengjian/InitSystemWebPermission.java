package net.shengjian;

import net.shengjian.frame.util.GlobalStatic;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 初始化System系统的权限菜单
 * 每个系统的初始化类名称都要保持唯一
 */
@Component
public class InitSystemWebPermission implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //添加用户默认有的路径权限
        // GlobalStatic.userDefaultUrl.add("/upload/**");
        //添加不拦截的URL地址
        GlobalStatic.excludePathPatterns.add("/api/sendCode");//发送手机验证码
        GlobalStatic.excludePathPatterns.add("/api/phoneLogin");//手机号登录

        GlobalStatic.excludePathPatterns.add("/upload/**");
        GlobalStatic.excludePathPatterns.add("/avatar/**");//用户头像静态资源
        GlobalStatic.excludePathPatterns.add("/temp/**");//临时资源文件夹
        GlobalStatic.excludePathPatterns.add("/template/**");//导入模板
        GlobalStatic.excludePathPatterns.add("/attachmentFile/**");//上传的附件静态资源
        GlobalStatic.excludePathPatterns.add("/attachment/formEleUpload");//表单组件文件上传

        GlobalStatic.userDefaultUrl.add("/attachment/upload");//文件上传
        GlobalStatic.userDefaultUrl.add("/api/system/user/updateSelfPassword");
        GlobalStatic.userDefaultUrl.add("/api/system/user/updateSelf");//用户修改个人信息
        GlobalStatic.userDefaultUrl.add("/api/system/user/updateAvatar");//用户修改个人信息
        GlobalStatic.userDefaultUrl.add("/api/system/notify/**");//用户修改个人信息

    }
}
