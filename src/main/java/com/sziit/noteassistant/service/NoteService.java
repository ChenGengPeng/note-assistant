package com.sziit.noteassistant.service;


import com.sziit.noteassistant.pojo.entity.Note;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface NoteService  {

    public List<Note> selectNotesByUid(Integer uId);

    public void addNote(Note note);

    void updateNote(Note note);

    void deleteNote(Integer nId);

    boolean deleteNotes(Integer[] nIds);

    public Note findOne(Note note);
}
