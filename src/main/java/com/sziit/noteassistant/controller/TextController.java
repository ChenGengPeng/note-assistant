package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.pojo.entity.Text;
import com.sziit.noteassistant.service.PictureService;
import com.sziit.noteassistant.service.TextService;
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
@Api(tags = "文本")
@RestController
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping("findText")
    @ApiOperation(value = "查询文本")
    public Object findText(@RequestParam Integer tId) {
        return new ResultVo(textService.selectTextByTid(tId));
    }

    @PostMapping("addText")
    @ApiOperation(value = "添加文本")
    public Object addText(@RequestBody Text text) {
        textService.addText(text);
        return new ResultVo(textService.selectOne(text));
    }

    @DeleteMapping("delText")
    @ApiOperation(value = "删除文本")
    public Object delText(@RequestParam Integer tId) {
        textService.deleteText(tId);
        return new ResultVo(ResultCode.SUCCESS);
    }

    @PutMapping("updateText")
    @ApiOperation(value = "修改文本")
    public Object updateText(@RequestBody Text text){
        return new ResultVo(textService.updateText(text));
    }

    @DeleteMapping("delTexts")
    @ApiOperation(value = "删除多个文本")
    public Object delTexts(@RequestParam Integer[] tIds){
        boolean result = textService.deleteTexts(tIds);
        if (result){
           return new ResultVo(ResultCode.SUCCESS);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }

}

