package com.sziit.noteassistant.http;

import lombok.Getter;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/23  3:09
 */
@Getter
public enum ResultCode implements StatusCode {
    SUCCESS(200, "请求成功"),
    BAD_REQUEST(400,"请求错误"),
    UNAUTHORIZED(401,"未授权，请登录"),
    PERMISSION_DENIED(403,"拒绝访问"),
    NOT_FOUND(404, "请求的资源不存在"),
    TIMEOUT(408,"请求超时"),
    ABORTED(409,"并发冲突，尝试创建资源已存在"),
    SERVER_ERROR(500, "服务器内部错误"),
    DUPLICATE_USERNAME(203,"用户名已经被注册"),
    REQUESET_INCORRECT(204,"用户名或者密码错误"),
    WRONG_PASSWORD(205,"密码错误"),
    NOTEXIST_USERNAME(206,"用户不存在"),
    UNREALIZED(501,"服务器未实现该API方法");

    private int code;
    private String msg;

    ResultCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
