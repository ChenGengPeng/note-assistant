package com.sziit.noteassistant.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sziit.noteassistant.mapper.UserMapper;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.UserService;
import com.sziit.noteassistant.utils.TransactionalJug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserMapper userMapper;
    private  BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        TransactionalJug.JudgeTransaction(userMapper.add(user));
        return userMapper.findByUsername(user.getUsername());
    }

    @Override
    public String getToken(User user){
        String token = "";
        token= JWT.create().withAudience(user.getUId().toString()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    @Override
    public User findById(Integer uid) {
        return userMapper.getById(uid);
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void changePassword(User user) {
        TransactionalJug.JudgeTransaction(userMapper.changePassword(user.getUId(),
                passwordEncoder.encode(user.getPassword())));
    }

    @Override
    public boolean comparePassword(String newPassword,String oldPassword) {
        return passwordEncoder.matches(newPassword,oldPassword);
    }

    @Override
    public User changeUsername(User user) {
        TransactionalJug.JudgeTransaction(userMapper.updateUser(user));
        return userMapper.findOne(user);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名无效" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
