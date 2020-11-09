package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.mapper.NoteMapper;
import com.sziit.noteassistant.mapper.ShareMapper;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.pojo.entity.Share;
import com.sziit.noteassistant.service.ShareService;
import com.sziit.noteassistant.utils.JudgeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/9  23:13
 */
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    ShareMapper shareMapper;

    @Autowired
    NoteMapper noteMapper;
    @Override
    public Share getShareKey(Integer nId) {
        if (shareMapper.findOneByNid(nId) == null){
            UUID uuid = UUID.randomUUID();
            String shareKey = uuid.toString();
            Share share = new Share(shareKey,nId);
            JudgeUtils.JudgeTransaction(shareMapper.insertShareKey(share));
        }
        return shareMapper.findOneByNid(nId);
    }

    @Override
    public Note getNoteByShareKey(String shareKey) {
        Integer nId = shareMapper.findOneByShareId(shareKey).getNId();
        if (nId == null){
            return null;
        }
        return noteMapper.findNoteByNid(nId);
    }
}
