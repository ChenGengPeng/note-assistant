package com.sziit.noteassistant.http;

import lombok.Data;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/23  3:24
 */
@Data
public class ResultVo {

    //状态码
    private int status;

    //状态信息
    private String msg;

    //返回对象
    private Object data;

    //手动设置返回vo
    public ResultVo(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //默认返回成功状态码，数据对象
    public ResultVo(Object data) {
        this.status = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    // 返回指定状态码，数据对象
    public ResultVo(StatusCode statusCode, Object data) {
        this.status = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    //只返回状态码
    public ResultVo(StatusCode statusCode) {
        this.status = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }
}
