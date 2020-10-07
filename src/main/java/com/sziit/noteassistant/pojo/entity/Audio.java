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
@ApiModel(value="Audio对象", description="")
public class Audio {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "音频id")
    private Integer aid;

    @ApiModelProperty(value = "音频地址")
    private String url;

    @ApiModelProperty(value = "音频排序")
    private Integer sort;

    @ApiModelProperty(value = "所属笔记id")
    private Integer nId;


    protected Serializable pkVal() {
        return this.aid;
    }

}
