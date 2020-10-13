package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.Picture;
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
public interface PictureMapper{

    public List<Picture> selectPictureByNid(Integer nId);

    public void addPicture(Picture picture);

    @Select("select p.p_id,p.p_url,p.sort,p.text,p.n_id from picture p where p.p_url = #{url}")
    public Picture selectPictureByUrl(String url);

    @Delete("delete from picture where p_id = #{pId}")
    int deletePicture(Integer pId);

    void updatePicture(Picture picture);
    @Select("select * from picture where p_id = #{pId}")
    Picture selectPictureByPid(Integer pId);
}
