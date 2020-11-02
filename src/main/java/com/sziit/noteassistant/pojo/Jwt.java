package com.sziit.noteassistant.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/28  0:08
 */
@Component
public class Jwt {
   public static String secret;
   public static long expiration_time;
   public static String token_prefix;
   public static String authorization;

   @Value("${jwt.SECRET}")
   private String tempSecret;
   @Value("${jwt.EXPIRATION_TIME}")
   private long tempExpiration_time;
   @Value("${jwt.TOKEN_PREFIX}")
   private String tempToken_Prefix;
   @Value("${jwt.AUTHORIZATION}")
   private String tempAuthorization;

   @PostConstruct
   public void setSecret() {
      secret = this.tempSecret;
   }
   @PostConstruct
   public void setExpiration_time() {
      expiration_time = this.tempExpiration_time;
   }
   @PostConstruct
   public void setToken_prefix() {
      token_prefix = this.tempToken_Prefix;
   }
   @PostConstruct
   public void setAuthorization() {
      authorization = this.tempAuthorization;
   }
}
