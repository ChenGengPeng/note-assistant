package com.sziit.noteassistant.pojo.entity;


import java.time.LocalDateTime;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.sziit.noteassistant.config.LocalDateTimeDeserializer;
import com.sziit.noteassistant.config.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="Note对象", description="")
public class Note  {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "笔记id")
    private Integer nId;

    @ApiModelProperty(value = "笔记名称")
    private String title;

    @ApiModelProperty(value = "是否收藏")
    private Boolean favorite;

    @ApiModelProperty(value = "创建时间")
    @JSONField(serializeUsing = LocalDateTimeSerializer.class,deserializeUsing = LocalDateTimeDeserializer.class)
    private LocalDateTime createtime;

    @ApiModelProperty(value = "笔记摘要")
    private String summary;

    @ApiModelProperty(value = "所属用户id")
    private Integer uId;

    public Note(String title, Boolean favorite, LocalDateTime createtime, String summary, Integer uId) {
        this.title = title;
        this.favorite = favorite;
        this.createtime = createtime;
        this.summary = summary;
        this.uId =uId;
    }


    protected Serializable pkVal() {
        return this.nId;
    }

}
