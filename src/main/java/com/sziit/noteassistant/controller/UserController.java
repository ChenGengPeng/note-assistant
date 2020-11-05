package com.sziit.noteassistant.controller;


import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.LoginUser;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.InformationService;
import com.sziit.noteassistant.service.UserService;
import com.sziit.noteassistant.utils.JudgeUtils;
import com.sziit.noteassistant.utils.JwtUtils;
import com.sziit.noteassistant.utils.RedisUtils;
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
    @Autowired
    private RedisUtils redisUtils;
    /**
     * 注册用户
     *
     * @param loginUser
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public Object register(@RequestBody LoginUser loginUser) {
       if (redisUtils.exists(loginUser.getPhone()) || informationService.findInformByPhone(loginUser.getPhone()) != null){
           logger.error("手机号已经被注册");
           return new ResultVo(ResultCode.BAD_REQUEST);
       }
        User addUser = userService.add(new User(loginUser.getPhone(), loginUser.getPassword()));
       redisUtils.set(loginUser.getPhone(),addUser.getUId());
       informationService.addInform(new Information(loginUser.getPhone(),addUser.getUId()));
       logger.info("用户注册成功，用户名为："+addUser.getUsername());
       addUser.setPassword(null);
        return new ResultVo(ResultCode.SUCCESS);
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Object login(@RequestBody LoginUser loginUser) {
        User userInDataBase = null;
        if (redisUtils.exists(loginUser.getPhone()) || informationService.findInformByPhone(loginUser.getPhone())!=null || userService.findByName(loginUser.getPhone())!=null){
            if(informationService.findInformByPhone(loginUser.getPhone())==null){
                userInDataBase= userService.findByName(loginUser.getPhone());
            }else {
                userInDataBase = userService.findById(informationService.findInformByPhone(loginUser.getPhone()).getUId());
            }
            redisUtils.set(loginUser.getPhone(),loginUser.getPhone());
        }else{
            return new ResultVo(ResultCode.BAD_REQUEST);
        }
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
        User user = JudgeUtils.JudgeUserExits();
        User userInDB = userService.findById(user.getUId());
        if (!userService.comparePassword(oldPassword,userInDB.getPassword())) {
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
        User user = JudgeUtils.JudgeUserExits();
       if (redisUtils.exists(newUsername) || userService.findByName(newUsername) != null){
            return new ResultVo(ResultCode.BAD_REQUEST,"用户名重复");
        } else {
            redisUtils.remove(user.getUsername());
            user.setUsername(newUsername);
            User userInDB = userService.changeUsername(user);
            redisUtils.set(userInDB.getUsername(),userInDB.getUId());
            logger.info("用户名修改成功");
            return new ResultVo(userInDB.getUsername());
        }
    }


}

