package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.InformationService;
import com.sziit.noteassistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
@Api(tags = "用户信息")
@RestController
@RequestMapping("/infor")
public class InformationController {

    @Autowired
    private InformationService informationService;
    @Autowired
    private UserService userService;

    /**
     * 添加用户信息
     * @param information
     * @return
     */
    @PostMapping("addInfo")
    @ApiOperation(value = "添加信息")
    public Object add(@RequestBody Information information) {
        JSONObject jsonObject = new JSONObject();
        informationService.addInform(information);
        User user = userService.findById(information.getUId());
        jsonObject.put("code",200);
        jsonObject.put("information",information);
        jsonObject.put("username",user.getUsername());
        return jsonObject;
    }

    /**
     * 查询用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping("findInfo")
    @ApiOperation(value = "查询信息")
    public Object findInfo(@RequestParam("username")String username) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.findByName(username);
        Information information = informationService.findByUid(user.getUid());
        jsonObject.put("code",200);
        jsonObject.put("information",information);
        return jsonObject;
    }

    @PutMapping("updateInfo")
    @ApiOperation(value = "更新信息")
    public Object findInfo(@RequestBody Information information) {
        JSONObject jsonObject = new JSONObject();
        informationService.updateInform(information);
        jsonObject.put("code",200);
        jsonObject.put("information",information);
        return jsonObject;
    }

}

