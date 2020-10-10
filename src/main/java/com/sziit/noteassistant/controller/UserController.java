package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.InformationService;
import com.sziit.noteassistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private InformationService informationService;
    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public Object add(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (userService.findByName(user.getUsername()) != null) {
            jsonObject.put("message", "用户名已被使用");
            return jsonObject;
        }
        userService.add(user);
        User userInDataBase = userService.findByName(user.getUsername());
        Information information = new Information();
        information.setUId(userInDataBase.getUid());
        informationService.addInform(information);
        jsonObject.put("code",200);
        jsonObject.put("username",user.getUsername());
        jsonObject.put("uId",user.getUid());
        return jsonObject;
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Object login(@RequestBody User user) {
        User userInDataBase = userService.findByName(user.getUsername());
        JSONObject jsonObject = new JSONObject();
        if (userInDataBase == null) {
            jsonObject.put("message", "用户不存在");
        } else if (!userService.comparePassword(user.getPassword(), userInDataBase.getPassword())) {
            jsonObject.put("message", "密码不正确");
        } else {
//            String token = userService.getToken(userInDataBase);
            jsonObject.put("code",200);
//            jsonObject.put("token", token);
            jsonObject.put("uid", userInDataBase.getUid());
            jsonObject.put("userName", userInDataBase.getUsername());
        }
        return jsonObject;
    }

    @PutMapping("change")
    @ApiOperation(value = "修改密码")
    public Object change( @RequestBody User user,
                          @RequestParam String newPassword) {
        User userInDataBase = userService.findByName(user.getUsername());
        JSONObject jsonObject = new JSONObject();
        if (userInDataBase == null) {
            jsonObject.put("message", "用户不存在");
        } else if (!userService.comparePassword(user.getPassword(), userInDataBase.getPassword())) {
            jsonObject.put("message", "密码不正确");
        }else {
            userService.changePassword(userInDataBase.getUid(),newPassword);
            jsonObject.put("code",200);
            jsonObject.put("uid",user.getUid());
            jsonObject.put("message","修改成功");
        }
        return jsonObject;
    }


}

