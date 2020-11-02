package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.mapper.PictureMapper;
import com.sziit.noteassistant.service.PictureService;

import com.sziit.noteassistant.utils.TransactionalJug;
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
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> selectPictureByNid(Integer nId) {
        return pictureMapper.selectPictureByNid(nId);
    }

    @Override
    public void addPicture(Picture picture) {
        TransactionalJug.JudgeTransaction( pictureMapper.addPicture(picture));
    }

    @Override
    public Picture updatePicture(Picture picture) {
        TransactionalJug.JudgeTransaction(pictureMapper.updatePicture(picture));
        return pictureMapper.selectOne(picture);
    }

    @Override
    public void deletePicture(Integer pId) {
        TransactionalJug.JudgeTransaction(pictureMapper.deletePicture(pId));
    }

    @Override
    public boolean deletePictures(Integer[] pIds) {
        for (Integer pId: pIds) {
           deletePicture(pId);
        }
        return true;
    }

    @Override
    public Picture selectPictureByPid(Integer pId) {
        return pictureMapper.selectPictureByPid(pId);
    }

    @Override
    public Picture selectPictureOne(Picture picture) {
        return pictureMapper.selectOne(picture);
    }

    @Override
    public Picture selectPictureByUrl(String pUrl) {
        return pictureMapper.selectPictureByUrl(pUrl);
    }
}
