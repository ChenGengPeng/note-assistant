package com.sziit.noteassistant.utils;

import com.sziit.noteassistant.exception.UnauthorizedException;
import com.sziit.noteassistant.http.ResultCode;
import com.sziit.noteassistant.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/30  3:53
 */
public class JudgeUtils {



    public static void JudgeTransaction(int result){
        if (result < 1){
            throw new RuntimeException("事务操作失败，回滚");
        }
    }

}
