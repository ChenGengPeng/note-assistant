package com.sziit.noteassistant.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sziit.noteassistant.pojo.NoteAuth;
import com.sziit.noteassistant.pojo.entity.Note;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/3  2:44
 */
public interface NoteService {

    public IPage<Note> getNotesDes(Integer uId, Integer pageId);

    public IPage<Note> getNotesAsc(Integer uId, Integer pageId);

    public IPage<Note> getFavNotesDesc(Integer uId,Integer pageId);

    public IPage<Note> getFavNotesAsc(Integer uId,Integer pageId);

    public Note addNote(Note note);

    public Note updateNote(Note note);

    public Note changeFavState(Integer nId,Boolean favorite);

    public Note findOne(Note note);

    public Note addNoteAuth(Integer uId, NoteAuth noteAuth);

    public String getSummary(JSONArray data);

    public void delNote(Integer nId);

    public void delNotes(String[] nIds);
}
