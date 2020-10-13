package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
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
@RequestMapping("/text")
public class TextController {

    @Autowired
    private TextService textService;

    @GetMapping("findText")
    @ApiOperation(value = "查询文本")
    public Object findText(@RequestParam Integer tId) {
        JSONObject jsonObject = new JSONObject();
        Text textInDB = textService.selectTextByTid(tId);
        jsonObject.put("text",textInDB);
        return jsonObject;
    }

    @PostMapping("addText")
    @ApiOperation(value = "添加文本")
    public Object addText(@RequestBody Text text) {
        JSONObject jsonObject = new JSONObject();
        textService.addText(text);
        Text textInDB = textService.selectOne(text);
        jsonObject.put("text",textInDB);
        return jsonObject;
    }

    @DeleteMapping("delText")
    @ApiOperation(value = "删除文本")
    public Object delText(@RequestParam Integer tId) {
        JSONObject jsonObject = new JSONObject();
        textService.deleteText(tId);
        jsonObject.put("code",200);
        jsonObject.put("message","删除成功");
        return jsonObject;
    }

    @PutMapping("updateText")
    @ApiOperation(value = "修改文本")
    public Object updateText(@RequestBody Text text){
        JSONObject jsonObject = new JSONObject();
        Text textInDB = textService.updateText(text);
        jsonObject.put("code",200);
        jsonObject.put("text",textInDB);
        return jsonObject;
    }

    @DeleteMapping("delTexts")
    @ApiOperation(value = "删除多个文本")
    public Object delTexts(@RequestParam Integer[] tIds){
        JSONObject jsonObject = new JSONObject();
        boolean result = textService.deleteTexts(tIds);
        if (result){
            jsonObject.put("code",200);
            jsonObject.put("message","删除成功");
        }else {
            jsonObject.put("message","删除失败");
        }
        return jsonObject;
    }

}

