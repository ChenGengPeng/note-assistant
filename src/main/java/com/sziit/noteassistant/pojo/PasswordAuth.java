package com.sziit.noteassistant.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/8  22:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="修改密码接受", description="")
public class PasswordAuth {
    @ApiModelProperty(value = "新密码")
    private String newPassword;
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;

}
