package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
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
    public Object addInfor(@RequestBody Information information) {
        return new ResultVo(informationService.addInform(information));
    }

    /**
     * 查询用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping("findInfo")
    @ApiOperation(value = "查询信息")
    public Object findInfo(@RequestParam("username")String username) {
        User user = userService.findByName(username);
        return new ResultVo(informationService.findByUid(user.getUId()));
    }

    @PutMapping("updateInfo")
    @ApiOperation(value = "更新信息")
    public Object updateInfo(@RequestBody Information information) {
        informationService.updateInform(information);
        return new ResultVo(informationService.findByUid(information.getUId()));
    }


    @DeleteMapping("delInfo")
    @ApiOperation(value = "删除信息")
    public Object deleteInfo(@RequestParam  String username) {
        User user = userService.findByName(username);
        informationService.deleteByUid(user.getUId());
        return new ResultVo(ResultCode.SUCCESS);
    }

}

