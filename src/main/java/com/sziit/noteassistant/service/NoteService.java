package com.sziit.noteassistant.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sziit.noteassistant.pojo.entity.Note;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface NoteService  {

    public IPage<Note> selectNotesByUid(Integer uId, Integer pageId);

    public IPage<Note> selectNotesByUidASC(Integer uId,Integer pageId);

    public Note addNote(Note note);

    Note updateNote(Note note);

    void deleteNote(Integer nId);

    boolean deleteNotes(Integer[] nIds);

    public Note findOne(Note note);

    public Note changeFavState(Note note);

    IPage<Note> selectFavNotesByUid(Integer uId, Integer pageId);
    IPage<Note> selectFavNotesByUidASC(Integer uId, Integer pageId);
}
