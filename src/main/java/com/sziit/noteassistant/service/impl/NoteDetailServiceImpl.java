package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.mapper.AudioMapper;
import com.sziit.noteassistant.mapper.PictureMapper;
import com.sziit.noteassistant.mapper.TextMapper;
import com.sziit.noteassistant.pojo.NoteDetail;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.pojo.entity.Text;
import com.sziit.noteassistant.service.*;
import com.sziit.noteassistant.utils.TransactionalJug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/10/12  19:58
 */
@Service
public class NoteDetailServiceImpl implements NoteDetailService {

    @Autowired
    private AudioService audioService;
    @Autowired
    private TextService textService;
    @Autowired
    private PictureService pictureService;
    @Override
    public NoteDetail selectNoteDetail(Integer nId) {
        List<Audio> audios = audioService.selectAudiosByNid(nId);
        List<Picture> pictures = pictureService.selectPictureByNid(nId);
        List<Text> texts = textService.selectTextByNid(nId);
        return new NoteDetail(audios, texts, pictures);
    }

    @Override
    public NoteDetail addNoteDetail(NoteDetail noteDetail) {
        NoteDetail noteDetailInDB = new NoteDetail();
        if (noteDetail.getAudios()!=null && !noteDetail.getAudios().isEmpty()){
            List<Audio> audios = new ArrayList<Audio>();
            for (int i = 0; i < noteDetail.getAudios().size(); i++) {
                audioService.addAudio(noteDetail.getAudios().get(i));
                audios.add(audioService.selectAudioOne(noteDetail.getAudios().get(i)));
            }
            noteDetailInDB.setAudios(audios);
        }
        if (noteDetail.getPictures()!=null && !noteDetail.getPictures().isEmpty()){
            List<Picture> pictures = new ArrayList<Picture>();
            for (int i = 0; i < noteDetail.getPictures().size(); i++) {
                pictureService.addPicture(noteDetail.getPictures().get(i));
                pictures.add(pictureService.selectPictureByUrl(noteDetail.getPictures().get(i).getPUrl()));
            }
            noteDetailInDB.setPictures(pictures);
        }
        if (noteDetail.getTexts()!=null && !noteDetail.getTexts().isEmpty()){
            List<Text> texts = new ArrayList<Text>();
            for (int i = 0; i < noteDetail.getTexts().size() ; i++) {
                textService.addText(noteDetail.getTexts().get(i));
                texts.add(textService.selectOne(noteDetail.getTexts().get(i)));
            }
            noteDetailInDB.setTexts(texts);
        }
        return noteDetailInDB;
    }

    @Override
    public NoteDetail deleteNoteDetails(NoteDetail noteDetail) {
        NoteDetail noteDetailInDB = new NoteDetail();
        if (noteDetail.getAudios()!=null && !noteDetail.getAudios().isEmpty()){
            for (int i = 0; i < noteDetail.getAudios().size(); i++) {
                audioService.deleteAudio(noteDetail.getAudios().get(i).getAId());
            }
        }
        if (noteDetail.getPictures()!=null && !noteDetail.getPictures().isEmpty()){
            for (int i = 0; i < noteDetail.getPictures().size(); i++) {
                pictureService.deletePicture(noteDetail.getPictures().get(i).getPId());
            }
        }
        if (noteDetail.getTexts()!=null && !noteDetail.getTexts().isEmpty()){
            for (int i = 0; i < noteDetail.getTexts().size(); i++) {
                textService.deleteText(noteDetail.getTexts().get(i).getTId());
            }
        }
        Integer nId = null;
        if (noteDetail.getAudios().get(0).getNId()!=null){
            nId = noteDetail.getAudios().get(0).getNId();
        }else if (noteDetail.getPictures().get(0).getNId()!=null){
            nId = noteDetail.getPictures().get(0).getNId();
        }else if (noteDetail.getTexts().get(0).getNId()!=null){
            nId = noteDetail.getTexts().get(0).getNId();
        }
        return selectNoteDetail(nId);
    }

    @Override
    public NoteDetail editorNoteDetails(NoteDetail noteDetail) {
        if (noteDetail.getAudios() != null && !noteDetail.getAudios().isEmpty()){
            for (int i = 0; i < noteDetail.getAudios().size(); i++){
                audioService.updateAudio(noteDetail.getAudios().get(i));
            }
        }
        if (noteDetail.getPictures() != null && !noteDetail.getPictures().isEmpty()){
            for (int i = 0; i < noteDetail.getPictures().size(); i++){
                pictureService.updatePicture(noteDetail.getPictures().get(i));
            }
        }
        if (noteDetail.getTexts() != null && !noteDetail.getTexts().isEmpty()){
            for (int i = 0; i < noteDetail.getTexts().size(); i++){
                textService.updateText(noteDetail.getTexts().get(i));
            }
        }
        Integer nId = null;
        if (noteDetail.getAudios().get(0).getNId()!=null){
            nId = noteDetail.getAudios().get(0).getNId();
        }else if (noteDetail.getPictures().get(0).getNId()!=null){
            nId = noteDetail.getPictures().get(0).getNId();
        }else if (noteDetail.getTexts().get(0).getNId()!=null){
            nId = noteDetail.getTexts().get(0).getNId();
        }
        return selectNoteDetail(nId);
    }

}
