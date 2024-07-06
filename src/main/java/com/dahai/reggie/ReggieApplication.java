package com.dahai.reggie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("项目启动成功");
        log.info("项目地址:{}","http://localhost:8080/backend/page/login/login.html");
        log.info("接口文档地址:{}","http://localhost:8080/doc.html");
    }
}
