package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.mapper.AudioMapper;
import com.sziit.noteassistant.service.AudioService;

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
public class AudioServiceImpl implements AudioService{

    @Autowired
    private AudioMapper audioMapper;

    @Override
    public List<Audio> selectAudioByNid(Integer nId) {
        return audioMapper.selectAudiosByNid(nId);
    }

    @Override
    public void addAudio(Audio audio) {
        audioMapper.addAudio(audio);
    }

    @Override
    public Audio updateAudio(Audio audio) {
        audioMapper.updateAudio(audio);
        return audioMapper.selectAudioByUrl(audio.getAUrl());
    }

    @Override
    public void deleteAudio(Integer aId) {
        audioMapper.deleteAudio(aId);
    }

    @Override
    public boolean deleteAudios(Integer[] aIds) {
        int sum = 0;
        for (Integer aId: aIds) {
            int result = audioMapper.deleteAudio(aId);
            sum += result;
        }
        return sum == aIds.length;
    }

    @Override
    public Audio selectAudioByAid(Integer aId) {
        return audioMapper.selectAudioByAid(aId);
    }

    @Override
    public Audio selectAudioByUrl(String aUrl) {
        return audioMapper.selectAudioByUrl(aUrl);
    }

}
