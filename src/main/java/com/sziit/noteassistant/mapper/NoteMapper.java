package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.Note;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

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

    public void updateNote(Note note);

    @Delete("delete from note where n_id = #{nId}")
    public int deleteNote(Integer nId);
}
