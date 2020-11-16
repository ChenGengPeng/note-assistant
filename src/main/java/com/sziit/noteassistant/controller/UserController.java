package com.sziit.noteassistant.controller;


import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.LoginUser;
import com.sziit.noteassistant.pojo.PasswordAuth;
import com.sziit.noteassistant.pojo.RegisterUser;
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
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 注册用户
     *
     * @param registerUser
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public Object register(@RequestBody RegisterUser registerUser) {
       if (redisTemplate.hasKey(registerUser.getPhone()) || informationService.findInformByPhone(registerUser.getPhone()) != null){
           logger.error("手机号已经被注册");
           return new ResultVo(ResultCode.BAD_REQUEST,"手机号已被注册");
       }
        if (redisTemplate.hasKey(registerUser.getUsername()) || userService.findByName(registerUser.getUsername()) != null){
            logger.error("用户名已经被注册");
            return new ResultVo(ResultCode.BAD_REQUEST,"用户名已被注册");
        }
       User addUser = userService.add(new User(registerUser.getUsername(), registerUser.getPassword()));
       redisTemplate.opsForValue().set(registerUser.getPhone(),addUser.getUId());
       informationService.addInform(new Information(registerUser.getPhone(),addUser.getUId()));
       logger.info("用户注册成功，用户名为："+addUser.getUsername());
       addUser.setPassword(null);
       return new ResultVo(addUser);
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Object login(@RequestBody LoginUser loginUser) {
        User userInDataBase = null;
        if (redisTemplate.hasKey(loginUser.getPhone())){
            userInDataBase = userService.findById((Integer) redisTemplate.opsForValue().get(loginUser.getPhone()));
        }else if (informationService.findInformByPhone(loginUser.getPhone())!=null){
            userInDataBase = userService.findById(informationService.findInformByPhone(loginUser.getPhone()).getUId());
            redisTemplate.opsForValue().set(loginUser.getPhone(),userInDataBase.getUId());
        } else{
            return new ResultVo(ResultCode.BAD_REQUEST,"用户没有注册");
        }
        if (userInDataBase == null || !userService.comparePassword(loginUser.getPassword(),userInDataBase.getPassword())){
            logger.error("用户名或者密码错误");
            return new ResultVo(ResultCode.BAD_REQUEST,"用户名或者密码错误");
        }else {
            String token = jwtUtils.generateToken(userInDataBase.getUsername(),loginUser.getPhone());
            logger.info("登录成功，获取token："+token);
            Map<String,String> authToken = new HashMap<>();
            authToken.put("token",token);
            return new ResultVo(authToken);
        }
    }


    @PutMapping("changePwd")
    @ApiOperation(value = "修改密码")
    public Object changePwd(@RequestBody PasswordAuth passwordAuth) {
        User user = JwtUtils.getUserBytoken();
        User userInDB = userService.findById(user.getUId());
        if (!userService.comparePassword(passwordAuth.getOldPassword(), userInDB.getPassword())) {
            logger.error("密码错误");
            return new ResultVo(ResultCode.BAD_REQUEST,"密码错误");
        }else {
            userInDB.setPassword(passwordAuth.getNewPassword());
            userService.changePassword(userInDB);
            logger.info("密码修改成功");
            return new ResultVo(ResultCode.SUCCESS);
        }
    }

    @PutMapping("changeName")
    @ApiOperation(value = "修改用户名")
    public Object changeName(@RequestParam String newUsername){
        User user = JwtUtils.getUserBytoken();
       if (redisTemplate.hasKey(newUsername) || userService.findByName(newUsername) != null){
           return new ResultVo(ResultCode.BAD_REQUEST,"用户名已被注册");
        } else {
            redisTemplate.delete(user.getUsername());
            user.setUsername(newUsername);
            User userInDB = userService.changeUsername(user);
            redisTemplate.opsForValue().set(userInDB.getUsername(),userInDB.getUId());
            logger.info("用户名修改成功");
            return new ResultVo(userInDB.getUsername());
        }
    }


}

