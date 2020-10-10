package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.mapper.NoteMapper;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;


    @Override
    public List<Note> selectNotesByUid(Integer uId){
        return noteMapper.selectNotesByUid(uId);
    }

    @Override
    public void addNote(Note note) {
        noteMapper.addNote(note);
    }

}
