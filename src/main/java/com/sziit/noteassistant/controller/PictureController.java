package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
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
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("findPicture")
    @ApiOperation(value = "查询音频")
    public Object findPicture(@RequestParam Integer pId) {
        JSONObject jsonObject = new JSONObject();
        Picture pictureInDB = pictureService.selectPictureByPid(pId);
        jsonObject.put("picture",pictureInDB);
        return jsonObject;
    }

    @PostMapping("addPicture")
    @ApiOperation(value = "添加图片")
    public Object addPicture(@RequestBody Picture picture) {
        JSONObject jsonObject = new JSONObject();
        pictureService.addPicture(picture);
        Picture pictureInDB = pictureService.selectPictureByUrl(picture.getPUrl());
        jsonObject.put("audio",pictureInDB);
        return jsonObject;
    }

    @DeleteMapping("delPicture")
    @ApiOperation(value = "删除音频")
    public Object delPicture(@RequestParam Integer pId) {
        JSONObject jsonObject = new JSONObject();
        pictureService.deletePicture(pId);
        jsonObject.put("code",200);
        jsonObject.put("message","删除成功");
        return jsonObject;
    }

    @PutMapping("updatePicture")
    @ApiOperation(value = "修改图片")
    public Object updatePicture(@RequestBody Picture picture){
        JSONObject jsonObject = new JSONObject();
        Picture pictureInDB = pictureService.updatePicture(picture);
        jsonObject.put("code",200);
        jsonObject.put("note",pictureInDB);
        return jsonObject;
    }

    @DeleteMapping("delPictures")
    @ApiOperation(value = "删除多个图片")
    public Object delPictures(@RequestParam Integer[] pIds){
        JSONObject jsonObject = new JSONObject();
        boolean result = pictureService.deletePictures(pIds);
        if (result){
            jsonObject.put("code",200);
            jsonObject.put("message","删除成功");
        }else {
            jsonObject.put("message","删除失败");
        }
        return jsonObject;
    }

}

