package com.sziit.noteassistant.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sziit.noteassistant.pojo.entity.Note;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
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

    public List<Note> selectNotesByUid(Page<Note>page,Integer uId);
    List<Note> selectNotesByUidASC(Page<Note>page,Integer uId);

    public int addNote(Note note);

    @Select("select * from note where n_id = #{nId}")
    public Note selectNoteByNId(Integer nId);

    public int updateNote(Note note);

    public List<Note> getFavoriteNote(Integer uId);

    public Note findOne(Note note);

    @Delete("delete from note where n_id = #{nId}")
    public int deleteNote(Integer nId);


    List<Note> selectFavNotesByUid(Page<Note> page, Integer uId);
    List<Note> selectFavNotesByUidASC(Page<Note> page, Integer uId);
}
