package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface AudioMapper{

    public List<Audio> selectAudiosByNid(Integer nId);

    @Select("select a.a_id,a.a_url,a.sort,a.text,a.n_id from audio a where a.a_url = #{url}")
    public Audio selectAudioByUrl(String url);

    public int addAudio(Audio audio);

    public void updateAudio(Audio audio);

    @Delete("delete from audio where a_id = #{aId}")
    public int deleteAudio(Integer aId);

    public int deleteAudioOne(Audio audio);
    @Select("select * from audio where a_id = #{aId}")
    Audio selectAudioByAid(Integer aId);
}
