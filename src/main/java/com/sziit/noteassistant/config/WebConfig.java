package com.sziit.noteassistant.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/11  3:24
 * springboot 默认使用的是 jackson 进行 requestBody 请求的封装，该项目切换为使用 fastJson 进行请求封装和响应
 *  * 配置 springboot 使用 fastJson 进行数据的请求接受和响应
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public HttpMessageConverter<String> stringConverter(){
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    public FastJsonHttpMessageConverter fastConverter(){
        //1.定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        //2-1 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }


}
