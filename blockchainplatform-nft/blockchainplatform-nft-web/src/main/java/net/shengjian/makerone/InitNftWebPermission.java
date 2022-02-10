package net.shengjian.makerone;

import net.shengjian.frame.util.GlobalStatic;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

/**
 * 初始化System系统的权限菜单
 * 每个系统的初始化类名称都要保持唯一
 */
@Component
public class InitNftWebPermission implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //添加用户默认有的路径权限
        // GlobalStatic.userDefaultUrl.add("");
        GlobalStatic.userDefaultUrl.add("/api/nft/login/findUserPermissions");
        GlobalStatic.userDefaultUrl.add("/api/nft/userchainplat/createChainPlatAccount");
        GlobalStatic.userDefaultUrl.add("/api/nft/login/getUserPhone"); //用户手机号
        GlobalStatic.userDefaultUrl.add("/api/nft/wechat/sign/url");

        GlobalStatic.userDefaultUrl.add("/api/nft/userassets/upload"); //文件上传返回资源访问链接接口

        GlobalStatic.excludePathPatterns.add("/api/nft/login/wechat");
        GlobalStatic.excludePathPatterns.add("/artwork/**");//艺术作品静态资源文件夹

        //添加不拦截的URL地址
        //GlobalStatic.excludePathPatterns.add("");
    }
}
