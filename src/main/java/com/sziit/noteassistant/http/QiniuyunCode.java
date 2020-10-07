package com.sziit.noteassistant.http;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/3  2:06
 */
public enum QiniuyunCode {

    SUCCESS(200,"上传成功！"),
    PARTIAL_SUCCESS(298,"部分操作执行成功"),
    FORMAT_ERROR(400,"请求报文格式错误"),
    AUTHENTICATION_FAILURE(401,"认证授权失败"),
    PERMISSION_DENIED(403,"权限不足，拒绝访问"),
    NO_EXIST(404,"资源不存在")
    ;

    private Integer code;
    private String msg;

    QiniuyunCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
