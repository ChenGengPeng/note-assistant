package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.service.AudioService;
import com.sziit.noteassistant.service.PictureService;
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
@Api(tags = "图片")
@RestController
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("findPic")
    @ApiOperation(value = "查询图片")
    public Object findPicture(@RequestParam Integer pId) {
        return new ResultVo(pictureService.selectPictureByPid(pId));
    }

    @PostMapping("addPic")
    @ApiOperation(value = "添加图片")
    public Object addPicture(@RequestBody Picture picture) {
        pictureService.addPicture(picture);
        return new ResultVo(pictureService.selectPictureOne(picture));
    }


    @PutMapping("updatePic")
    @ApiOperation(value = "修改图片")
    public Object updatePicture(@RequestBody Picture picture){
        return new ResultVo(pictureService.updatePicture(picture));
    }

    @DeleteMapping("delPic")
    @ApiOperation(value = "删除多个图片")
    public Object delPictures(@RequestParam Integer[] pIds){
        boolean result = pictureService.deletePictures(pIds);
        if (result){
            return new ResultVo(ResultCode.SUCCESS);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }

}

