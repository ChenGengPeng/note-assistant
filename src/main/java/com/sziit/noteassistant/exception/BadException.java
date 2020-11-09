package com.sziit.noteassistant.exception;

import com.sziit.noteassistant.http.ResultCode;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/4  15:15
 */
public class BadException extends RuntimeException{

    private ResultCode resultCode;

    public BadException(ResultCode resultCode){
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
