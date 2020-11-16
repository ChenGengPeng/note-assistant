package com.sziit.noteassistant.utils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/3  2:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@PropertySource("classpath:application.yml")
public class QiniuyunUtilTest {

    @Test
    public void upload(){
        String filePath = "src/main/resources/static/3.jpg";
       QiniuyunUtils.uploadPic(filePath);
    }
}
