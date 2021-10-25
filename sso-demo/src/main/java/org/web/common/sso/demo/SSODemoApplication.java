package org.web.common.sso.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.web.common.sso.biz.impl.DefaultSSOFilter;

/**
 * SSO demo project 启动类。
 * 引入三方组件功能，要进行手动处理，不要用扫描的方式。
 * */
@SpringBootApplication()
public class SSODemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSODemoApplication.class, args);

    }
    @Bean
    public FilterRegistrationBean getServletRegistrationBean(){
        FilterRegistrationBean bean=new FilterRegistrationBean(new DefaultSSOFilter());
        // bean.addUrlPatterns(new String[]{"*.do",".jsp"});
        bean.addUrlPatterns("/*");
        return bean;
    }

}
