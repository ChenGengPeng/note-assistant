package com.sziit.noteassistant.pojo;

import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.pojo.entity.Text;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/12  19:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="笔记详情", description="")
@NoArgsConstructor
@AllArgsConstructor
public class NoteDetail {
    @ApiModelProperty(value = "音频集合")
    private List<Audio> audios;

    @ApiModelProperty(value = "文本集合")
    private List<Text> texts;

    @ApiModelProperty(value = "图片集合")
    private List<Picture> pictures;
}
