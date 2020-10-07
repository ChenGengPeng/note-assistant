package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.mapper.UserMapper;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public Object add(@RequestBody User user) {
        if (userService.findByName(user.getUsername()) != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message","用户名已被使用");
            return jsonObject;
        }
        return userService.add(user);
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Object login(@RequestBody User user) {
        User userInDataBase = userService.findByName(user.getUsername());
        JSONObject jsonObject = new JSONObject();
        if (userInDataBase == null){
            jsonObject.put("message","用户不存在");
        } else if (!userService.comparePassword(user,userInDataBase)) {
            jsonObject.put("message","密码不正确");
        }else {
            String token = userService.getToken(userInDataBase);
            jsonObject.put("token",token);
            jsonObject.put("user",userInDataBase);
        }
        return jsonObject;

    }

}

