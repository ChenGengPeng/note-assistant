package com.sziit.noteassistant.service;

import com.sziit.noteassistant.pojo.NoteDetail;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/12  19:47
 */
public interface NoteDetailService {

    public NoteDetail selectNoteDetail(Integer nId);
    public NoteDetail addNoteDetail(NoteDetail noteDetail);
    public NoteDetail deleteNoteDetails(NoteDetail noteDetail);
    public NoteDetail editorNoteDetails(NoteDetail noteDetail);
}
