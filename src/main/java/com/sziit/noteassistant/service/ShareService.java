package com.sziit.noteassistant.service;

import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.pojo.entity.Share;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/9  23:13
 */
public interface ShareService {

    Share getShareKey(Integer nId);

    Note getNoteByShareKey(String shareKey);
}
