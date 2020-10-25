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

    @Override
    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }

    @Override
    public void deleteNote(Integer nId) {
        noteMapper.deleteNote(nId);
    }

    @Override
    public boolean deleteNotes(Integer[] nIds) {
        int sum = 0;
        for(Integer nId : nIds){
            int result = noteMapper.deleteNote(nId);
            sum += result;
        }
        return sum == nIds.length;
    }

    public Note findOne(Note note){
        return noteMapper.findOne(note);
    }

}
