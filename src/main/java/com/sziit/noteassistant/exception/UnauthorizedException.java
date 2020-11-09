package com.sziit.noteassistant.exception;

import com.sziit.noteassistant.http.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/4  17:01
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED,reason = "未授权，请重新登录")
public class UnauthorizedException extends  RuntimeException {

    private ResultCode resultCode;

    public UnauthorizedException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
