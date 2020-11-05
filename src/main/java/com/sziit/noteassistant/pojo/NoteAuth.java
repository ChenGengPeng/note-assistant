package com.sziit.noteassistant.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.sziit.noteassistant.config.LocalDateTimeDeserializer;
import com.sziit.noteassistant.config.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/2  22:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="Note接收对象", description="")
public class NoteAuth {

    @ApiModelProperty(value = "笔记id")
    private Integer id;

    @ApiModelProperty(value = "笔记名称")
    private String title;

    @ApiModelProperty(value = "笔记内容")
    private JSONArray data;

    @ApiModelProperty(value = "是否收藏")
    private Boolean favorite;

    @ApiModelProperty(value = "创建时间")
    @JSONField(serializeUsing = LocalDateTimeSerializer.class,deserializeUsing = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

}
