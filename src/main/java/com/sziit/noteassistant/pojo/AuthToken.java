package com.sziit.noteassistant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/29  16:50
 */
@Data
@AllArgsConstructor
public class AuthToken {
    private String token;
    private String username;

}
