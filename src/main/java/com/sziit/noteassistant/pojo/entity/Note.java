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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Note对象", description="")
public class Note  {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "笔记id")
    private Integer nId;

    @ApiModelProperty(value = "笔记名称")
    private String nName;

    @ApiModelProperty(value = "创建时间")
    @JSONField(serializeUsing = LocalDateTimeSerializer.class,deserializeUsing = LocalDateTimeDeserializer.class)
    private LocalDateTime createtime;

    @ApiModelProperty(value = "所属用户id")
    private Integer uId;


    protected Serializable pkVal() {
        return this.nId;
    }

}
