package com.sziit.noteassistant.utils;

import com.sziit.noteassistant.qiniuyun.QiniuyunUpload;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

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
       QiniuyunUtil.uploadPic(filePath);
    }
}
