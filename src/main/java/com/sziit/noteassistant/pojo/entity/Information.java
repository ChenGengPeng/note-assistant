package com.sziit.noteassistant.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/5  2:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Information对象", description="")
public class Information {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "信息id")
    private Integer iid;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "个人简介")
    private String profile;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "用户头像url")
    private String uPhoto;

    @ApiModelProperty(value = "所属用户id")
    private Integer uId;


    protected Serializable pkVal() {
        return this.iid;
    }
}
