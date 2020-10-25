package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.AudioService;
import com.sziit.noteassistant.service.NoteService;
import com.sziit.noteassistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Api(tags = "笔记")
@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;


    @GetMapping("findNote")
    @ApiOperation(value = "查询笔记")
    public Object findNote(@RequestParam String username) {
        User user = userService.findByName(username);
        return new ResultVo(noteService.selectNotesByUid(user.getUId()));
    }

    @PostMapping("addNote")
    @ApiOperation(value = "添加笔记")
    public Object addNote(@RequestParam String noteName,@RequestParam String username){
        JSONObject jsonObject = new JSONObject();
        User user = userService.findByName(username);
        Note note = new Note();
        note.setNName(noteName);
        note.setUId(user.getUId());
        note.setCreatetime(LocalDateTime.now());
        noteService.addNote(note);
        return new ResultVo(noteService.findOne(note));
    }

    @PutMapping("updateNote")
    @ApiOperation(value = "修改笔记")
    public Object updateNote(@RequestBody Note note){
     noteService.updateNote(note);
     return new ResultVo(noteService.findOne(note));
    }


    @DeleteMapping("delNote")
    @ApiOperation(value = "删除笔记")
    public Object deleteNote(@RequestParam Integer nId){
        noteService.deleteNote(nId);
        return new ResultVo(ResultCode.SUCCESS);
    }

    @DeleteMapping("delNotes")
    @ApiOperation(value = "删除多个笔记")
    public Object deleteNotes(@RequestParam Integer[] nIds){
        JSONObject jsonObject = new JSONObject();
        boolean result = noteService.deleteNotes(nIds);
        if (result){
            return new ResultVo(ResultCode.SUCCESS);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }

}

