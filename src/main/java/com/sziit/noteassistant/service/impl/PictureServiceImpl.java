package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.mapper.PictureMapper;
import com.sziit.noteassistant.service.PictureService;

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
        pictureMapper.addPicture(picture);
    }

    @Override
    public Picture updatePicture(Picture picture) {
        pictureMapper.updatePicture(picture);
        return pictureMapper.selectOne(picture);
    }

    @Override
    public void deletePicture(Integer pId) {
        pictureMapper.deletePicture(pId);
    }

    @Override
    public boolean deletePictures(Integer[] pIds) {
        int sum =0;
        for (Integer pId: pIds) {
            int result = pictureMapper.deletePicture(pId);
            sum += result;
        }
        return sum == pIds.length;
    }

    @Override
    public Picture selectPictureByPid(Integer pId) {
        return pictureMapper.selectPictureByPid(pId);
    }

    @Override
    public Picture selectPictureOne(Picture picture) {
        return pictureMapper.selectOne(picture);
    }
}
