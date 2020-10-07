package com.sziit.noteassistant.pojo.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Text对象", description="")
public class Text {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "文本id")
    private Integer tid;

    @ApiModelProperty(value = "文本的url")
    private String url;

    @ApiModelProperty(value = "文本的内容")
    private Integer text;

    @ApiModelProperty(value = "文本所属笔记id")
    private Integer nId;



    protected Serializable pkVal() {
        return this.tid;
    }

}
