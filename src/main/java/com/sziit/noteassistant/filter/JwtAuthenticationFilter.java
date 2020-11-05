package com.sziit.noteassistant.filter;

import com.sziit.noteassistant.exception.UnauthorizedException;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.pojo.Jwt;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.UserService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/28  1:57
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(Jwt.authorization);
        User user = new User();
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(Jwt.token_prefix)){
            authToken = header.replace(Jwt.token_prefix,"");
            if(authToken==null || "".equals(authToken)){
                logger.warn("couldn't not find bearer string , will ignore the header");
            }else {
                try{
                    username = jwtUtils.getUsernameFromToken(authToken);
                }catch (IllegalArgumentException e){
                    logger.error("an error occured during getting username from token",e);
                }catch (ExpiredJwtException e){
                    logger.warn("the token is expired and not valid anymore",e);
                }catch (SignatureException e){
                    logger.error("Authentication Failed, Username or Password not valid.");
                }
            }
        }else {
            logger.warn("couldn't not find bearer string , will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            user = userService.findByName(username);
            if(user==null){
                throw new UnauthorizedException(ResultCode.UNAUTHORIZED);
            }
            if (jwtUtils.validateToken(authToken,user)){
                user.setPassword(null);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("authenticated user" + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }else {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            logger.info("未登录");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

    filterChain.doFilter(httpServletRequest,httpServletResponse);


    }
}
