package com.sziit.noteassistant.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sziit.noteassistant.mapper.UserMapper;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
@Service
public class UserServiceImpl implements UserService {

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
        userMapper.add(user);
        User user1 = new User();
        user1.setUsername(user.getUsername());
        return userMapper.findOne(user1);
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
        User user = new User();
        user.setUsername(username);
        return userMapper.findOne(user);
    }

    @Override
    public void changePassword(Integer id,String newPassword) {
        newPassword = passwordEncoder.encode(newPassword);
        userMapper.changePassword(id,newPassword);
    }

    @Override
    public boolean comparePassword(String newPassword,String oldPassword) {
        return passwordEncoder.matches(newPassword,oldPassword);
    }


}
