package com.sziit.noteassistant.service;

import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface PictureService{

    public List<Picture> selectPictureByNid(Integer nId);

    public void addPicture(Picture picture);

    public Picture updatePicture(Picture picture);

    public void deletePicture(Integer pId);

    public boolean deletePictures(Integer[] pIds);

    Picture selectPictureByPid(Integer pId);

    Picture selectPictureByUrl(String pUrl);
}
