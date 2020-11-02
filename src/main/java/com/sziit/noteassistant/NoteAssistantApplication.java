package com.sziit.noteassistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author CGP 1577992659@qq.com
 */
@SpringBootApplication
@MapperScan("com.sziit.noteassistant.mapper")
@EnableTransactionManagement  //开启事务管理
public class NoteAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteAssistantApplication.class, args);
    }

}
