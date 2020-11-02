package com.sziit.noteassistant.controller;


import com.alibaba.fastjson.JSONObject;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.pojo.entity.Text;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.PictureService;
import com.sziit.noteassistant.service.TextService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private TextService textService;

    @GetMapping("findText")
    @ApiOperation(value = "查询文本")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object findText(@RequestParam Integer tId) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(textService.selectTextByTid(tId));
    }

    @PostMapping("addText")
    @ApiOperation(value = "添加文本")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object addText(@RequestBody Text text) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        textService.addText(text);
        return new ResultVo(textService.selectOne(text));
    }

    @DeleteMapping("delText")
    @ApiOperation(value = "删除文本")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object delText(@RequestParam Integer tId) {
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        textService.deleteText(tId);
        return new ResultVo(ResultCode.SUCCESS);
    }

    @PutMapping("updateText")
    @ApiOperation(value = "修改文本")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object updateText(@RequestBody Text text){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        return new ResultVo(textService.updateText(text));
    }

    @DeleteMapping("delTexts")
    @ApiOperation(value = "删除多个文本")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object delTexts(@RequestParam Integer[] tIds){
        User user = JwtUtils.getUserBytoken();
        if(user.getUsername()==null){
            logger.error("未登录");
            return new ResultVo(ResultCode.UNAUTHORIZED);
        }
        boolean result = textService.deleteTexts(tIds);
        if (result){
           return new ResultVo(ResultCode.SUCCESS);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }

}

