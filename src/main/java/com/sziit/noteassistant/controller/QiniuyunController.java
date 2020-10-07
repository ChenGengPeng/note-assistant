package com.sziit.noteassistant.controller;

import com.sziit.noteassistant.utils.QiniuyunUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/3  16:03
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "七牛云上传接口")
public class QiniuyunController {

    @ApiOperation(value = "请求token")
    @PostMapping(value="/getToken")
    public String getToken() {
        return QiniuyunUtil.getToken();
    }

    @ApiOperation(value = "上传图片到七牛云")
    @PostMapping(value="/uploadToQiNiu")
    public String uploadImg(@RequestParam("file") MultipartFile image) {
        Map<String, Object> result = new HashMap<String, Object>();
        if(!image.isEmpty()) {
            try {
                byte[] bytes = image.getBytes();
                // 用随机字符串当做图片名存储
//                String imageName = UUID.randomUUID().toString();
                try {
                    //使用base64方式上传到七牛云
                    result = QiniuyunUtil.uploadPicByBase64(bytes);
                    return String.valueOf(result.get("result"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                //上传失败
                return String.valueOf(result.get("result"));
            }
        }
        // 图片为空
        return String.valueOf(result.get("result"));
    }



}
