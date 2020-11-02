package com.sziit.noteassistant.pojo.entity;

import java.io.Serializable;
import java.util.List;

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

@ApiModel(value="User对象", description="")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    private Integer uId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer uId,String username) {
        this.uId = uId;
        this.username = username;
    }

    protected Serializable pkVal() {
        return this.uId;
    }

}
