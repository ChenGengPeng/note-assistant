package com.sziit.noteassistant.service;


import com.sziit.noteassistant.pojo.entity.Audio;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface AudioService  {

    public List<Audio> selectAudioByNid(Integer nId);

    public void addAudio(Audio audio);

    public Audio updateAudio(Audio audio);

    public void deleteAudio(Integer aId);

    public boolean deleteAudios(Integer[] aIds);

    public Audio selectAudioByAid(Integer aId);

    Audio selectAudioByUrl(String aUrl);
}
