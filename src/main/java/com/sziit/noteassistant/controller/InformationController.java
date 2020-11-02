package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.Jwt;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.InformationService;
import com.sziit.noteassistant.service.UserService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    protected Log logger = LogFactory.getLog(this.getClass());
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
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        if(JwtUtils.getUserBytoken()!=null){
            return new ResultVo(informationService.addInform(information));
        }
        return new ResultVo(ResultCode.UNAUTHORIZED);
    }

    /**
     * 查询用户信息
     * @return
     */
    @GetMapping("findInfo")
    @ApiOperation(value = "查询信息")
    public Object findInfo() {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        if(JwtUtils.getUserBytoken() != null){
            return new ResultVo(informationService.findByUid(JwtUtils.getUserBytoken().getUId()));
        }
        return new ResultVo(ResultCode.UNAUTHORIZED);
    }

    @PutMapping("updateInfo")
    @ApiOperation(value = "更新信息")
    public Object updateInfo(@RequestBody Information information) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        Information informationInDB = informationService.findByUid(user.getUId());
        if (user!= null && informationInDB!= null){
           information.setIId(informationInDB.getIId());
           informationService.updateInform(information);
           return new ResultVo(informationService.findByUid(user.getUId()));
        }
      return new ResultVo(ResultCode.UNAUTHORIZED);
    }


    @DeleteMapping("delInfo")
    @ApiOperation(value = "删除信息")
    public Object deleteInfo() {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        user = userService.findById(user.getUId());
        informationService.deleteByUid(user.getUId());
        return new ResultVo(ResultCode.SUCCESS);
    }

}

