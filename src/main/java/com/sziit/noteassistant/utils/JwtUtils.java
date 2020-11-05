package com.sziit.noteassistant.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.sziit.noteassistant.pojo.Jwt;
import com.sziit.noteassistant.pojo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/28  0:04
 */
@Component
public class JwtUtils implements Serializable {

    private static final String SECRET = Jwt.secret;
    private static final long EXPIRATION_TIME = Jwt.expiration_time;
    private static final String TOKEN_PREFIX = Jwt.token_prefix;
    private static final String AUTHORIZATION = Jwt.authorization;

    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, Math.toIntExact(EXPIRATION_TIME));
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SECRET)).toString();
    }

    public static User  getUserBytoken(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    /**
     * retrieve username from jwt token
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * for retrieveing any information from token we will need the secret key
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Jwt.secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException e){
            claims = e.getClaims();
        }
        return claims;

    }

    /**
     * retrieve expiration date from jwt token
     * @param token
     * @return
     */
    private Date getExpirationDataFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    /**
     * check if the token has expired
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token){
        Claims claims = this.getAllClaimsFromToken(token);
        final Date expiration = claims.getExpiration();
        return new Date(System.currentTimeMillis()).after(expiration);
    }

    /**
     * generate token for user
     * @param user
     * @return
     */
    public String generateToken(User user){
        return doGenerateToken(user.getUsername());
    }

    /**
     *      //while creating the token -
     *     //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
     *     //2. Sign the JWT using the HS512 algorithm and secret key.
     *     //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
     *     //   compaction of the JWT to a URL-safe string
     *     iss(签发者), exp(过期时间), sub(面向用户), aud(接收方), iat(签发时间)等。
     * @param subject
     * @return
     */
    private String doGenerateToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Jwt.expiration_time *1000))
                .signWith(SignatureAlgorithm.HS256,Jwt.secret)
                .compact();
    }

    /**
     * validate token
     * @param token
     * @param user
     * @return
     */
    public Boolean validateToken(String token, User user){
        final String username = getUsernameFromToken(token);
        if (username.equals(user.getUsername())
                && !isTokenExpired(token)){
            return true;
        }
        return (
                username.equals(user.getUsername())
                && !isTokenExpired(token)
                );
    }
}
