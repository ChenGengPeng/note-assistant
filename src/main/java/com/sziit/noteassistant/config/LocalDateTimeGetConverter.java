package com.sziit.noteassistant.config;

import com.google.common.base.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/11  3:37
 */
@Configuration
public class LocalDateTimeGetConverter {
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime doForward(String s) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(s)),ZoneId.systemDefault());
            }

            @Override
            protected String doBackward(LocalDateTime localDateTime) {
                return null;
            }
        };
    }
}
