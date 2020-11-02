package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.AudioService;
import com.sziit.noteassistant.service.PictureService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Api(tags = "图片")
@RestController
public class PictureController {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private PictureService pictureService;

    @GetMapping("findPic")
    @ApiOperation(value = "查询图片")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object findPicture(@RequestParam Integer pId) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(pictureService.selectPictureByPid(pId));
    }

    @PostMapping("addPic")
    @ApiOperation(value = "添加图片")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object addPicture(@RequestBody Picture picture) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        pictureService.addPicture(picture);
        return new ResultVo(pictureService.selectPictureOne(picture));
    }


    @PutMapping("updatePic")
    @ApiOperation(value = "修改图片")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object updatePicture(@RequestBody Picture picture){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(pictureService.updatePicture(picture));
    }

    @DeleteMapping("delPic")
    @ApiOperation(value = "删除多个图片")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object delPictures(@RequestParam Integer[] pIds){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        boolean result = pictureService.deletePictures(pIds);
        if (result){
            return new ResultVo(ResultCode.SUCCESS);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }

}

