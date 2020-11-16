package com.sziit.noteassistant.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/9  17:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="模糊查询接收对象", description="")
public class FuzzyTitle {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "页面id")
    private Integer page;
}
