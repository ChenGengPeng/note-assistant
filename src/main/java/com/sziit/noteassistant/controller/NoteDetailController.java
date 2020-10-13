package com.sziit.noteassistant.controller;

import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.pojo.NoteDetail;
import com.sziit.noteassistant.service.NoteDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/12  20:10
 */
@Api(tags = "笔记详情")
@RestController
@RequestMapping("/noteDetail")
public class NoteDetailController {

    @Autowired
    private NoteDetailService noteDetailService;

    @GetMapping("findNoteDetail")
    @ApiOperation(value = "查询笔记详情")
    public Object findNoteDetail(@RequestParam Integer nId) {
        JSONObject jsonObject = new JSONObject();
        NoteDetail noteDetail = noteDetailService.selectNoteDetail(nId);
        jsonObject.put("noteDetail",noteDetail);
        return jsonObject;
    }

    @PostMapping("addNoteDetail")
    @ApiOperation(value = "添加笔记详情")
    public Object addNoteDetail(@RequestBody NoteDetail noteDetail) {
        JSONObject jsonObject = new JSONObject();
        NoteDetail noteDetailInDB = noteDetailService.addNoteDetail(noteDetail);
        jsonObject.put("noteDetail", noteDetailInDB);
        return jsonObject;
    }

    @DeleteMapping("delNoteDetail")
    @ApiOperation(value = "删除笔记详情")
    public Object delNoteDetail(@RequestBody NoteDetail noteDetail) {
        JSONObject jsonObject = new JSONObject();
        NoteDetail noteDetailInDB = noteDetailService.deleteNoteDetails(noteDetail);
        jsonObject.put("noteDetail", noteDetailInDB);
        return jsonObject;
    }
}
