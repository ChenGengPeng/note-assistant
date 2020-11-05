package com.sziit.noteassistant.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sziit.noteassistant.mapper.NoteMapper;
import com.sziit.noteassistant.pojo.NoteAuth;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.service.NoteService;
import com.sziit.noteassistant.utils.JudgeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/3  2:48
 */
@Service
public class NoteServiceImpl implements NoteService {

    private final static Integer pageSize = 10;
    @Autowired
    private NoteMapper noteMapper;


    @Override
    public IPage<Note> getNotesDes(Integer uId, Integer pageId) {
        Page<Note> page = new Page<>(pageId,pageSize);
        page.setRecords(noteMapper.getNotesDesc(page,uId));
        return page;
    }

    @Override
    public IPage<Note> getNotesAsc(Integer uId, Integer pageId) {
        Page<Note> page = new Page<>(pageId,pageSize);
        page.setRecords(noteMapper.getNotesAsc(page,uId));
        return page;
    }

    @Override
    public IPage<Note> getFavNotesDesc(Integer uId, Integer pageId) {
        Page<Note> page = new Page<>(pageId,pageSize);
        page.setRecords(noteMapper.getFavNotesDesc(page,uId));
        return page;
    }

    @Override
    public IPage<Note> getFavNotesAsc(Integer uId, Integer pageId) {
        Page<Note> page = new Page<>(pageId,pageSize);
        page.setRecords(noteMapper.getFavNotesAsc(page,uId));
        return page;
    }

    @Override
    public Note addNote(Note note) {
        JudgeUtils.JudgeTransaction(noteMapper.add(note));
        return findOne(note);
    }

    @Override
    public Note updateNote(Note note) {
        JudgeUtils.JudgeTransaction(noteMapper.update(note));
        return noteMapper.findNoteByNid(note.getId());
    }

    @Override
    public Note changeFavState(Integer nId, Boolean favorite) {
        JudgeUtils.JudgeTransaction(noteMapper.changeFav(favorite,nId));
        return noteMapper.findNoteByNid(nId);
    }

    @Override
    public Note findOne(Note note) {
        return noteMapper.findOne(note);
    }

    @Override
    public Note addNoteAuth(Integer uId, NoteAuth noteAuth) {
        Note note = new Note(uId,noteAuth.getTitle(),noteAuth.getFavorite(),noteAuth.getData().toString(),LocalDateTime.now());
        note.setSummary(getSummary(noteAuth.getData()));
        return addNote(note);
    }

    @Override
    public String getSummary(JSONArray data){
        StringBuilder summary = new StringBuilder();
        for (int i = 0; i <data.size() ; i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            String type = String.valueOf(jsonObject.get("type"));
            String value = String.valueOf(jsonObject.get("value"));
            if ("text".equals(type)){
                summary.append(value);
                if (summary.length() >= 200){
                    break;
                }
            }
        }
        if (summary.length() > 200){
            summary.substring(0,201);
        }
        return summary.toString();
    }

    @Override
    public void delNote(Integer nId) {
        JudgeUtils.JudgeTransaction(noteMapper.delNote(nId));
    }

    @Override
    public void delNotes(String[] nIds) {
        JudgeUtils.JudgeTransaction(noteMapper.delNotes(nIds));
    }
}
