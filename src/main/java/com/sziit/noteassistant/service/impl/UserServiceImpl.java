package com.sziit.noteassistant.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sziit.noteassistant.mapper.TextMapper;
import com.sziit.noteassistant.mapper.UserMapper;
import com.sziit.noteassistant.pojo.entity.Text;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.TextService;
import com.sziit.noteassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

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
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.add(user);
    }

    @Override
    public String getToken(User user){
        String token = "";
        token= JWT.create().withAudience(user.getUid().toString()).sign(Algorithm.HMAC256(user.getPassword()));
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
