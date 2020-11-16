package com.sziit.noteassistant.exception;

import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.http.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/4  15:20
 */
//@RestControllerAdvice(annotations = RestController.class)
public class MyControllerAdvice<T> {

    @ResponseStatus(HttpStatus.OK)
    public ResultVo sendSuccessResponse(){
        return new ResultVo(ResultCode.SUCCESS);
    }

    @ResponseStatus(HttpStatus.OK)
    public ResultVo sendSuccessResponse(T data){
        return new ResultVo(ResultCode.SUCCESS,data);
    }

    @ExceptionHandler(BadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVo sendBadResponse(Exception exception){
        return new ResultVo(ResultCode.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVo sendUnauthorizedResponse(Exception exception){
        return new ResultVo(ResultCode.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVo sendErrorResponse_System(Exception exception){
        if (exception instanceof BadException){
            return this.sendBadResponse(exception);
        }else if (exception instanceof UnauthorizedException){
            return this.sendUnauthorizedResponse(exception);
        }
        return new ResultVo(ResultCode.BAD_REQUEST);
    }
}
