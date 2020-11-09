package com.sziit.noteassistant.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sziit.noteassistant.pojo.entity.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/3  2:13
 */
@Mapper
public interface NoteMapper {

    int add(Note note);

    int update(Note note);

    int changeFav(Boolean favorite,Integer nId);

    Note findOne(Note note);

    int delNote(Integer nId);

    int delNotes(String[] nIds);

    Note findNoteByNid(Integer nId);

    List<Note> getNotesDesc(Page<Note> page,Integer uId);

    List<Note> getNotesAsc(Page<Note> page,Integer uId);

    List<Note> getFavNotesDesc(Page<Note> page,Integer uId);

    List<Note> getFavNotesAsc(Page<Note> page,Integer uId);

    Note getNote(Integer nId,Integer uId);

    List<Note> searcheNoteByTile(Page<Note> page, String title,Integer uId);
}
