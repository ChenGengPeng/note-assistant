package com.sziit.noteassistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/6  0:06
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/**", "/*.html", "/favicon.ico", "/css/**", "/js/**", "/fonts/**", "/img/**",
                "/v2/api-docs/**", "/swagger-resources/**", "/webjars/**", "/pages/**", "/druid/**","/user/login","/user/register",
                "/statics/**")
                .permitAll().anyRequest().authenticated()
            .and()
            .cors() //支持跨域
                .and()   //添加header设置，支持跨域和ajax请求
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin","*"),
                new Header("Access-Control-Expose-Headers","Authorization"))))
                .and()
            .formLogin()
            .and()
            .httpBasic()
            .and()
            .logout();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
