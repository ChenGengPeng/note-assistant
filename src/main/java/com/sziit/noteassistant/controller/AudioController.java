package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.pojo.NoteDetail;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.AudioService;
import com.sziit.noteassistant.service.NoteDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Api(tags = "音频")
@RestController
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @GetMapping("findAudio")
    @ApiOperation(value = "查询音频")
    public Object findAudio(@RequestParam Integer aId) {
        JSONObject jsonObject = new JSONObject();
        Audio audioInDB = audioService.selectAudioByAid(aId);
        jsonObject.put("audio",audioInDB);
        return jsonObject;
    }

    @PostMapping("addAudio")
    @ApiOperation(value = "添加音频")
    public Object addAudio(@RequestBody Audio audio) {
        JSONObject jsonObject = new JSONObject();
        audioService.addAudio(audio);
        Audio audioInDB = audioService.selectAudioByUrl(audio.getAUrl());
        jsonObject.put("audio",audioInDB);
        return jsonObject;
    }

    @DeleteMapping("delAudio")
    @ApiOperation(value = "删除音频")
    public Object deleteAudio(@RequestParam Integer aId) {
        JSONObject jsonObject = new JSONObject();
        audioService.deleteAudio(aId);
        jsonObject.put("code",200);
        jsonObject.put("message","删除成功");
        return jsonObject;
    }

    @PutMapping("updateAudio")
    @ApiOperation(value = "修改音频")
    public Object updateAudio(@RequestBody Audio audio){
        JSONObject jsonObject = new JSONObject();
        Audio audioInDB = audioService.updateAudio(audio);
        jsonObject.put("code",200);
        jsonObject.put("note",audioInDB);
        return jsonObject;
    }

    @DeleteMapping("delAudios")
    @ApiOperation(value = "删除多个音频")
    public Object deleteAudios(@RequestParam Integer[] aIds){
        JSONObject jsonObject = new JSONObject();
        boolean result = audioService.deleteAudios(aIds);
        if (result){
            jsonObject.put("code",200);
            jsonObject.put("message","删除成功");
        }else {
            jsonObject.put("message","删除失败");
        }
        return jsonObject;
    }
}

