package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.Note;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface NoteMapper {

    public List<Note> selectNotesByUid(Integer uId);

    public int addNote(Note note);
}
