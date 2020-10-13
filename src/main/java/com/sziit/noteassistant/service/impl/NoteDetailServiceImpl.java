package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.mapper.AudioMapper;
import com.sziit.noteassistant.mapper.PictureMapper;
import com.sziit.noteassistant.mapper.TextMapper;
import com.sziit.noteassistant.pojo.NoteDetail;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.pojo.entity.Text;
import com.sziit.noteassistant.service.NoteDetailService;
import com.sziit.noteassistant.service.NoteService;
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
    private AudioMapper audioMapper;
    @Autowired
    private TextMapper textMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Override
    public NoteDetail selectNoteDetail(Integer nId) {
        List<Audio> audios = audioMapper.selectAudiosByNid(nId);
        List<Picture> pictures = pictureMapper.selectPictureByNid(nId);
        List<Text> texts = textMapper.selectTextByNid(nId);
        NoteDetail noteDetail = new NoteDetail();
        noteDetail.setAudios(audios);
        noteDetail.setPictures(pictures);
        noteDetail.setTexts(texts);
        return noteDetail;
    }

    @Override
    public NoteDetail addNoteDetail(NoteDetail noteDetail) {
        NoteDetail noteDetailInDB = new NoteDetail();
        if (noteDetail.getAudios()!=null && !noteDetail.getAudios().isEmpty()){
            List<Audio> audios = new ArrayList<Audio>();
            for (int i = 0; i < noteDetail.getAudios().size(); i++) {
                audioMapper.addAudio(noteDetail.getAudios().get(i));
                audios.add(audioMapper.selectAudioByUrl(noteDetail.getAudios().get(i).getAUrl()));
            }
            noteDetailInDB.setAudios(audios);
        }else if (noteDetail.getPictures()!=null && !noteDetail.getPictures().isEmpty()){
            List<Picture> pictures = new ArrayList<Picture>();
            for (int i = 0; i < noteDetail.getPictures().size(); i++) {
                pictureMapper.addPicture(noteDetail.getPictures().get(i));
                pictures.add(pictureMapper.selectPictureByUrl(noteDetail.getPictures().get(i).getPUrl()));
            }
            noteDetailInDB.setPictures(pictures);
        }else if (noteDetail.getTexts()!=null && !noteDetail.getTexts().isEmpty()){
            List<Text> texts = new ArrayList<Text>();
            for (int i = 0; i < noteDetail.getTexts().size() ; i++) {
                textMapper.addText(noteDetail.getTexts().get(i));
                texts.add(textMapper.selectOne(noteDetail.getTexts().get(i)));
            }
            noteDetailInDB.setTexts(texts);
        }
        return noteDetailInDB;
    }

    @Override
    public NoteDetail deleteNoteDetails(NoteDetail noteDetail) {
        NoteDetail noteDetailInDB = new NoteDetail();
        if (noteDetail.getAudios()!=null && !noteDetail.getAudios().isEmpty()){
            int sum = 0;
            for (int i = 0; i < noteDetail.getAudios().size(); i++) {
                int result = audioMapper.deleteAudio(noteDetail.getAudios().get(i).getAId());
                sum += result;
            }
            if (sum != noteDetail.getAudios().size()){
                throw new RuntimeException("音频删除失败");
            }
            noteDetailInDB.setAudios(audioMapper.selectAudiosByNid(noteDetail.getAudios().get(0).getNId()));
        }else if (noteDetail.getPictures()!=null && !noteDetail.getPictures().isEmpty()){
            int sum = 0;
            for (int i = 0; i < noteDetail.getPictures().size(); i++) {
                int result = pictureMapper.deletePicture(noteDetail.getPictures().get(i).getPId());
                sum += result;
            }
            if (sum != noteDetail.getPictures().size()){
                throw new RuntimeException("图片删除失败");
            }
            noteDetailInDB.setPictures(pictureMapper.selectPictureByNid(noteDetail.getPictures().get(0).getNId()));
        }else if (noteDetail.getTexts()!=null && !noteDetail.getTexts().isEmpty()){
            int sum = 0;
            for (int i = 0; i < noteDetail.getTexts().size(); i++) {
                int result = textMapper.deleteText(noteDetail.getTexts().get(i).getTId());
                sum += result;
            }
            if (sum != noteDetail.getTexts().size()){
                throw new RuntimeException("文本删除失败");
            }
            noteDetailInDB.setTexts(textMapper.selectTextByNid(noteDetail.getTexts().get(0).getNId()));
        }
        return noteDetailInDB;
    }

}
