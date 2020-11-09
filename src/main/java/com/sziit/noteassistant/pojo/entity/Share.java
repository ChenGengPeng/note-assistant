package com.sziit.noteassistant.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/9  23:09
 */
@ApiModel(value="分享链接对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Share {
    @ApiModelProperty(value = "分享对象id")
    private Integer id;
    @ApiModelProperty(value = "笔记id")
    private Integer nId;
    @ApiModelProperty(value = "分享码")
    private String shareId;

    public Share(String shareKey, Integer nId) {
        this.shareId = shareKey;
        this.nId = nId;
    }
}
