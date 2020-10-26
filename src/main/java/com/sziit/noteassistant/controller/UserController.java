package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.LoginUser;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.InformationService;
import com.sziit.noteassistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private InformationService informationService;
    /**
     * 注册用户
     *
     * @param loginUser
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public Object register(@RequestBody LoginUser loginUser) {
        if (informationService.findInformByPhone(loginUser.getPhone()) != null && userService.findByName(loginUser.getPhone()) != null){
            return new ResultVo(ResultCode.DUPLICATE_USERNAME);
        }
        User user = new User();
        user.setUsername(loginUser.getPhone());
        user.setPassword(loginUser.getPassword());
        userService.add(user);
        User userInDataBase = userService.findByName(user.getUsername());
        Information information = new Information();
        information.setUId(userInDataBase.getUId());
        information.setPhone(user.getUsername());
        informationService.addInform(information);
        User user1 = new User();
        user1.setUsername(userInDataBase.getUsername());
        user1.setUId(userInDataBase.getUId());
        return new ResultVo(user1);
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Object login(@RequestBody User user) {
        User userInDataBase = userService.findByName(user.getUsername());
        Information inform = informationService.findInformByPhone(user.getUsername());
        User user1 = userService.findById(inform.getUId());
        HashMap<String,String> loginMap = new HashMap<>();
        if (userInDataBase == null || !userService.comparePassword(user.getPassword(), userInDataBase.getPassword())) {
            return new ResultVo(ResultCode.REQUESET_INCORRECT);
        }else if (user1 == null || !userService.comparePassword(user.getPassword(),user1.getPassword())){
            return new ResultVo(ResultCode.REQUESET_INCORRECT);
        } else {
            loginMap.put("uid", String.valueOf(userInDataBase.getUId()));
            loginMap.put("username",userInDataBase.getUsername());
//            loginMap.put("token", userService.getToken(user));
            return new ResultVo(loginMap);
        }
    }


    @PutMapping("changePwd")
    @ApiOperation(value = "修改密码")
    public Object changePwd( @RequestBody User user,
                          @RequestParam String newPassword) {
        User userInDataBase = userService.findByName(user.getUsername());
        if (userInDataBase == null) {
            return new ResultVo(ResultCode.NOTEXIST_USERNAME);
        } else if (!userService.comparePassword(user.getPassword(), userInDataBase.getPassword())) {
            return new ResultVo(ResultCode.REQUESET_INCORRECT);
        }else {
            userService.changePassword(userInDataBase.getUId(),newPassword);
            return new ResultVo(ResultCode.SUCCESS);
        }
    }

    @PutMapping("changeName")
    @ApiOperation(value = "修改用户名")
    public Object changeName(@RequestBody User user,
                             @RequestParam String newUsername){
        User userInDataBase = userService.findByName(user.getUsername());
        if (userInDataBase == null){
            return new ResultVo(ResultCode.NOTEXIST_USERNAME);
        }else if (userService.findByName(newUsername) != null){
            return new ResultVo(ResultCode.DUPLICATE_USERNAME);
        }else {
            User newUser = new User();
            newUser.setUId(userInDataBase.getUId());
            newUser.setUsername(newUsername);
            User changeUsername = userService.changeUsername(newUser);
            changeUsername.setPassword(null);
            return new ResultVo(changeUsername);

        }
    }


}

