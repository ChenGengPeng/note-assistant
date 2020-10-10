package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.NoteService;
import com.sziit.noteassistant.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tools.ant.taskdefs.condition.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
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
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @GetMapping("findnote")
    @ApiOperation(value = "查询笔记")
    public Object findInfo(@RequestParam String username) {
        JSONObject jsonObject = new JSONObject();
        User user = userService.findByName(username);
        List<Note> notes = noteService.selectNotesByUid(user.getUid());
        jsonObject.put("code",200);
        jsonObject.put("username",username);
        jsonObject.put("notes",notes);
        return jsonObject;
    }

    @PostMapping("addnote")
    @ApiOperation(value = "添加笔记")
    public Object addNote(@RequestParam String noteName,@RequestParam String username){
        JSONObject jsonObject = new JSONObject();
        User user = userService.findByName(username);
        Note note = new Note();
        note.setNName(noteName);
        note.setUId(user.getUid());
        note.setCreatetime(LocalDateTime.now());
        noteService.addNote(note);
        jsonObject.put("code",200);
        jsonObject.put("note",note);
        return jsonObject;
    }



}

