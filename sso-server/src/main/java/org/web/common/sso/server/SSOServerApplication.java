package org.web.common.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;



@SpringBootApplication()
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@MapperScan(basePackages = {"org.web.common.sso.server.dao"})
@ImportResource({"classpath:mapper/mybatis-config-all.xml"})
public class SSOServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOServerApplication.class, args);
    }

}
