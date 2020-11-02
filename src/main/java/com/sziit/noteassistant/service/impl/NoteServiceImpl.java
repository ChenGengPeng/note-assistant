package com.sziit.noteassistant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sziit.noteassistant.mapper.NoteMapper;
import com.sziit.noteassistant.pojo.NoteDetail;
import com.sziit.noteassistant.pojo.entity.Note;
import com.sziit.noteassistant.service.NoteDetailService;
import com.sziit.noteassistant.service.NoteService;

import com.sziit.noteassistant.utils.TransactionalJug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final static Integer pageSize = 10;

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private NoteDetailService noteDetailService;

    @Override
    public IPage<Note> selectNotesByUid(Integer uId,Integer pageId){
        Page<Note> page = new Page(pageId,pageSize);
        page.setRecords(noteMapper.selectNotesByUid(page,uId));
        return page;
    }

    @Override
    public IPage<Note> selectNotesByUidASC(Integer uId,Integer pageId) {
        Page<Note> page = new Page(pageId,pageSize);
        page.setRecords(noteMapper.selectNotesByUidASC(page,uId));
        return page;
    }

    @Override
    public Note addNote(Note note) {
        TransactionalJug.JudgeTransaction(noteMapper.addNote(note));
        return findOne(note);
    }

    @Override
    public Note updateNote(Note note) {
        TransactionalJug.JudgeTransaction(noteMapper.updateNote(note));
        return noteMapper.selectNoteByNId(note.getNId());
    }

    @Override
    public void deleteNote(Integer nId) {
        TransactionalJug.JudgeTransaction(noteMapper.deleteNote(nId));
    }

    @Override
    public boolean deleteNotes(Integer[] nIds) {
        for(Integer nId : nIds){
            deleteNote(nId);
        }
        return true;
    }

    @Override
    public Note findOne(Note note){
        return noteMapper.findOne(note);
    }


    @Override
    public Note changeFavState(Note note) {
        Note noteInDB = noteMapper.selectNoteByNId(note.getNId());
        noteInDB.setFavorite(note.getFavorite());
        return updateNote(noteInDB);
    }

    @Override
    public IPage<Note> selectFavNotesByUid(Integer uId, Integer pageId) {
        Page<Note> page = new Page<>(pageId,pageSize);
        page.setRecords(noteMapper.selectFavNotesByUid(page,uId));
        return page;
    }

    @Override
    public IPage<Note> selectFavNotesByUidASC(Integer uId, Integer pageId) {
        Page<Note> page = new Page<>(pageId,pageSize);
        page.setRecords(noteMapper.selectNotesByUidASC(page,uId));
        return page;
    }

}
