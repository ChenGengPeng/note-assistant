package com.sziit.noteassistant.utils;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/30  3:53
 */
public class TransactionalJug {
    public static void JudgeTransaction(int result){
        if (result != 1){
            throw new RuntimeException("事务操作失败，回滚");
        }
    }
}
