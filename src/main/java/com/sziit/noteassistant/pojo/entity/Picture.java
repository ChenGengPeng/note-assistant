package com.sziit.noteassistant.pojo.entity;


import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="Picture对象", description="")
public class Picture {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "图片id")
    private Integer pId;

    @ApiModelProperty(value = "图片的url")
    private String pUrl;

    @ApiModelProperty(value = "图片的排序")
    private Integer sort;

    @ApiModelProperty(value = "图片文本")
    private String pText;

    @ApiModelProperty(value = "图片所属笔记id")
    private Integer nId;



    protected Serializable pkVal() {
        return this.pId;
    }

}
