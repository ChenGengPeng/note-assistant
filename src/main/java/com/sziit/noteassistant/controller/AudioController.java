package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.NoteDetail;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.AudioService;
import com.sziit.noteassistant.service.NoteDetailService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
public class AudioController {

    protected Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private AudioService audioService;

    @GetMapping("findAudio")
    @ApiOperation(value = "查询音频")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object findAudio(@RequestParam Integer aId) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(audioService.selectAudioByAid(aId));
    }

    @PostMapping("addAudio")
    @ApiOperation(value = "添加音频")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object addAudio(@RequestBody Audio audio) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        audioService.addAudio(audio);
        return new ResultVo(audioService.selectAudioOne(audio));
    }

    @DeleteMapping("delAudio")
    @ApiOperation(value = "删除音频")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object deleteAudio(@RequestParam Integer aId) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        audioService.deleteAudio(aId);
        return new ResultVo(ResultCode.SUCCESS);
    }

    @PutMapping("updateAudio")
    @ApiOperation(value = "修改音频")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object updateAudio(@RequestBody Audio audio){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(audioService.updateAudio(audio));
    }

    @DeleteMapping("delAudios")
    @ApiOperation(value = "删除多个音频")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object deleteAudios(@RequestParam Integer[] aIds) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        boolean result = audioService.deleteAudios(aIds);
        if (result) {
            return new ResultVo(ResultCode.SUCCESS);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }
}

