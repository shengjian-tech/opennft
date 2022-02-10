package net.shengjian;

import net.shengjian.frame.util.GlobalStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 作为单体运行时,需要注释 @SpringBootApplication 注解,会冲突,一个项目只能有一个@SpringBootApplication
 *
 * @author caomei
 */
//@SpringBootApplication
public class SystemServiceImplApplication {
    public static void main(String[] args) {
        // 因为serviceimpl有接口和实现,不能正确感知是否开启.需要在SpringBootApplication启动里设置为true
        GlobalStatic.seataEnable = true;
        SpringApplication.run(SystemServiceImplApplication.class, args);
    }
}
