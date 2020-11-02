package com.sziit.noteassistant.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.NoteDetailService;
import com.sziit.noteassistant.service.NoteService;
import com.sziit.noteassistant.service.UserService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @Autowired
    private NoteDetailService noteDetailService;

    @GetMapping("getNoteList")
    @ApiOperation(value = "查询笔记")
    public Object getNoteList(@RequestParam Integer page) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        IPage<Note> iPage = noteService.selectNotesByUid(user.getUId(), page);
        return new ResultVo(iPage.getRecords());
    }

    @GetMapping("getNoteListDesc")
    @ApiOperation(value = "查询笔记(从旧到新)")
    public Object getNoteListDesc(@RequestParam Integer page) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }

        IPage<Note> iPage = noteService.selectNotesByUidASC(user.getUId(),page);
        return new ResultVo(iPage.getRecords());
    }
    @PostMapping("addNote")
    @ApiOperation(value = "添加笔记")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object addNote(@RequestBody Note note){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        note.setUId(user.getUId());
        note.setCreatetime(LocalDateTime.now());
        Note noteInDB = noteService.addNote(new Note(note.getTitle(),
                note.getFavorite(),
                note.getCreatetime(),
                note.getSummary(),
                note.getUId()));
       return new ResultVo(noteInDB);
    }

    @PutMapping("editorNote")
    @ApiOperation(value = "修改笔记")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object updateNote(@RequestBody Note note){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        Note noteInDB = noteService.updateNote(new Note(note.getNId(),note.getTitle(), note.getFavorite(), note.getCreatetime(),note.getSummary(), user.getUId()));
     return new ResultVo(noteInDB);
    }


    @DeleteMapping("delNote")
    @ApiOperation(value = "删除笔记")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object deleteNote(@RequestParam Integer nId){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        noteService.deleteNote(nId);
        return new ResultVo(ResultCode.SUCCESS);
    }

    @DeleteMapping("delNotes")
    @ApiOperation(value = "删除多个笔记")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object deleteNotes(@RequestParam Integer[] nIds){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        boolean result = noteService.deleteNotes(nIds);
        if (result){
            return new ResultVo(ResultCode.SUCCESS);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }

    @PutMapping("changeFavState")
    @ApiOperation(value = "改变笔记收藏状态")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object changeFavState(@RequestBody Note note){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        note.setUId(user.getUId());
        return new ResultVo(noteService.changeFavState(note));
    }
    @PutMapping("getFavoriteNote")
    @ApiOperation(value = "获取收藏笔记")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object getFavoriteNote(Integer page){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        IPage<Note> iPage = noteService.selectFavNotesByUid(user.getUId(),page);
        return new ResultVo(iPage.getRecords());
    }
    @PutMapping("getFavoriteNoteDesc")
    @ApiOperation(value = "获取收藏笔记(从旧到新)")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object getFavoriteNoteDesc(Integer page){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        IPage<Note> iPage = noteService.selectFavNotesByUidASC(user.getUId(),page);
        return new ResultVo(iPage.getRecords());
    }
}

