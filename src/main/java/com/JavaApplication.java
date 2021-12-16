package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>项目名称: JavaSpring</p>
 * <p>文件名称: JavaApplication</p>
 * <p>描述: Spring Boot主启动类</p>
 * <p>创建时间: 2021/11/18</p>
 *
 * @author: zwh
 * @version: 1.0.0
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.mybatis.mapper"})
public class JavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }
}
