package com.sziit.noteassistant.controller;


import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.LoginUser;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.InformationService;
import com.sziit.noteassistant.service.UserService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private InformationService informationService;
    @Autowired
    private JwtUtils jwtUtils;
    /**
     * 注册用户
     *
     * @param loginUser
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public Object register(@RequestBody LoginUser loginUser) {
       if (informationService.findInformByPhone(loginUser.getPhone()) != null){
           logger.error("手机号已经被注册");
           return new ResultVo(ResultCode.BAD_REQUEST);
       }
        User addUser = userService.add(new User(loginUser.getPhone(), loginUser.getPassword()));
       informationService.addInform(new Information(loginUser.getPhone(),addUser.getUId()));
       logger.info("用户注册成功，用户名为："+addUser.getUsername());
       addUser.setPassword(null);
        return new ResultVo(addUser);
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Object login(@RequestBody LoginUser loginUser) {
        User userInDataBase = userService.findById(informationService.findInformByPhone(loginUser.getPhone()).getUId());
        if (userInDataBase == null || !userService.comparePassword(loginUser.getPassword(),userInDataBase.getPassword())){
            logger.error("用户名或者密码错误");
            return new ResultVo(ResultCode.BAD_REQUEST);
        }else {
            String token = jwtUtils.generateToken(userInDataBase);
            logger.info("登录成功，获取token："+token);
            Map<String,String> authToken = new HashMap<>();
            authToken.put("token",token);
            return new ResultVo(authToken);
        }
    }


    @PutMapping("changePwd")
    @ApiOperation(value = "修改密码")
    public Object changePwd(@RequestParam String oldPassword,@RequestParam String newPassword) {
        User user = jwtUtils.getUserBytoken();
        User userInDB = userService.findById(user.getUId());
        if (user.getUsername() == null || userInDB == null) {
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        } else if (!userService.comparePassword(oldPassword,userInDB.getPassword())) {
            logger.error("密码错误");
            return new ResultVo(ResultCode.SUCCESS,"密码错误");
        }else {
            userInDB.setPassword(newPassword);
            userService.changePassword(userInDB);
            logger.info("密码修改成功");
            return new ResultVo(ResultCode.SUCCESS);
        }
    }

    @PutMapping("changeName")
    @ApiOperation(value = "修改用户名")
    public Object changeName(@RequestParam String newUsername){
        User user = jwtUtils.getUserBytoken();
        User userInDataBase = userService.findById(user.getUId());
        if (userInDataBase == null || user.getUsername() == null){
            logger.error("未登录或者用户不存在");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }else {
            userInDataBase.setUsername(newUsername);
            logger.info("用户名修改成功");
            return new ResultVo(userService.changeUsername(userInDataBase).getUsername());
        }
    }


}

