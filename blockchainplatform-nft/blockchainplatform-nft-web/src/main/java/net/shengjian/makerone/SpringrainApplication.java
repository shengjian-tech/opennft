package net.shengjian.makerone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;



@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"${springrain.basepackagepath}"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class SpringrainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringrainApplication.class, args);
    }
}
