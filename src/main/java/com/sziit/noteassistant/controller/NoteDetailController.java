package com.sziit.noteassistant.controller;

import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.NoteDetail;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.NoteDetailService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/12  20:10
 */
@Api(tags = "笔记详情")
@RestController
public class NoteDetailController {


    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private NoteDetailService noteDetailService;

    @GetMapping("findNDetail")
    @ApiOperation(value = "查询笔记详情")
    public Object findNoteDetail(@RequestParam Integer nId) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(noteDetailService.selectNoteDetail(nId));
    }

    @PostMapping("addNDetail")
    @ApiOperation(value = "添加笔记详情")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object addNoteDetail(@RequestBody NoteDetail noteDetail) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(noteDetailService.addNoteDetail(noteDetail));
    }

    @DeleteMapping("delNDetail")
    @ApiOperation(value = "删除笔记详情")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object delNoteDetail(@RequestBody NoteDetail noteDetail) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(noteDetailService.deleteNoteDetails(noteDetail));
    }

    @PutMapping("ediNDetail")
    @ApiOperation(value = "修改笔记详情")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object editorNoteDetails(@RequestBody NoteDetail noteDetail) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(noteDetailService.editorNoteDetails(noteDetail));
    }
}
