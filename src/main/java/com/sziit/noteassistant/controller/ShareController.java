package com.sziit.noteassistant.controller;

import com.sziit.noteassistant.http.ResultVo;
import com.sziit.noteassistant.pojo.entity.Share;
import com.sziit.noteassistant.pojo.entity.User;
import com.sziit.noteassistant.service.NoteService;
import com.sziit.noteassistant.service.ShareService;
import com.sziit.noteassistant.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/9  23:12
 */
@Api(tags = "分享")
@RestController
public class ShareController {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private NoteService noteService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ShareService shareService;

    @PostMapping("getShareKey")
    @ApiOperation(value = "生成分享码")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object getShareKey(@RequestParam Integer nId) {
        User user = JwtUtils.getUserBytoken();
        Share share = null;
        String redisKey = nId+"Key";
       if (redisTemplate.hasKey(redisKey)){
           String shareKey = (String) redisTemplate.opsForValue().get(redisKey);
           share.setShareId(shareKey);
           share.setNId(nId);
           return new ResultVo(share);
       }
        share = shareService.getShareKey(nId);
        redisTemplate.opsForValue().set(redisKey,share.getShareId());
        return new ResultVo(share);
    }

    @GetMapping("getNoteByShare")
    @ApiOperation(value = "获取分享的笔记")
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    public Object getNoteByShare(@RequestParam String shareKey) {
        return new ResultVo(shareService.getNoteByShareKey(shareKey));
    }


}
